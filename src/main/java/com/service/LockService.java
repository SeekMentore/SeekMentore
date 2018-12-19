package com.service;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.constants.BeanConstants;
import com.constants.LockConstants;
import com.exception.ApplicationException;
import com.utils.LoggerUtils;

@Service(BeanConstants.BEAN_NAME_LOCK_SERVICE)
public class LockService implements LockConstants {
	
	private Map<String, Lock> objectLockStatus;
	
	class Lock {
		private String key = null;
		private Boolean present = false;
		
		public Boolean lockPresent() {
			return this.present;
		}
		
		public String requestLock() {
			if (!this.present) {
				this.key = String.valueOf(Math.random() + Math.random() + Math.random());
				this.present = true;
				return this.key;
			}
			throw new ApplicationException("Cannot Lock an already Locked Object.");
		}
		
		public void releaseLock(final String key) {
			if (this.key.equals(key)) {
				this.key = null;
				this.present = false;
			} else {
				throw new ApplicationException("Cannot Un-Lock Object, Invalid Key passed.");
			}
		}
	}
	
	@PostConstruct
	public void init() {
		objectLockStatus = new HashMap<String, Lock>();
	}
	
	public synchronized String lockObject(final String object) {
		Lock lock = objectLockStatus.get(object);
		if (null == lock) {
			// If this is the first time Lock is Invoked for Object
			// Create a Lock object and place it in the map
			lock = new Lock();
			objectLockStatus.put(object, lock);
		} 
		if (lock.lockPresent()) {
			LoggerUtils.logOnConsole("Already Lock present");
			return null;
		}
		return lock.requestLock();
	}
	
	public synchronized void releaseLock(final String object, final String key) {
		Lock lock = objectLockStatus.get(object);
		if (null == lock || !lock.lockPresent()) {
			throw new ApplicationException("Lock does not exist.");
		}
		lock.releaseLock(key);
	}
}

package com.service;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.annotation.PostConstruct;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.constants.BeanConstants;
import com.constants.CipherConstants;

@Service(BeanConstants.BEAN_NAME_CIPHER_SERVICE)
public class CipherService implements CipherConstants {
	
	@Autowired
	private JNDIandControlConfigurationLoadService jndiAndControlConfigurationLoadService;
	
	private Cipher cipherInEncyptionMode;
	private Cipher cipherInDecyptionMode;
	
	@PostConstruct
	public void init() throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException {
		this.cipherInEncyptionMode = getCipher(Cipher.ENCRYPT_MODE);
		this.cipherInDecyptionMode = getCipher(Cipher.DECRYPT_MODE);
	}
	
	private Cipher getCipher(int mode) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException {
		final Cipher cipher = Cipher.getInstance(jndiAndControlConfigurationLoadService.getControlConfiguration().getAppEncyptionAlgorithm());
        cipher.init(mode, new SecretKeySpec(jndiAndControlConfigurationLoadService.getEncyptionKey().getBytes(), jndiAndControlConfigurationLoadService.getControlConfiguration().getAppEncyptionAlgorithm()));
        return cipher;
	}
	
	public Cipher getCipherInEncyptionMode() {
		return cipherInEncyptionMode;
	}

	public Cipher getCipherInDecyptionMode() {
		return cipherInDecyptionMode;
	}
}

package com.service;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.crypto.NoSuchPaddingException;
import javax.xml.bind.JAXBException;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.stereotype.Service;

import com.constants.BeanConstants;
import com.constants.VelocityConstants;

@Service(BeanConstants.BEAN_NAME_VELOCITY_ENGINE_SERVICE)
public class VelocityEngineService implements VelocityConstants {
	
	private VelocityEngine velocityEngine;
	
	@PostConstruct
	public void configureAndInitializeVelocityEngine()  throws JAXBException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IOException {
		if (null == velocityEngine) {
			final Properties velocityProperties = new Properties();
			velocityProperties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(VELOCITY_PROPERTIES_FILE));
			velocityEngine = new VelocityEngine();
			velocityEngine.init(velocityProperties);
		}
	}

	public VelocityEngine getVelocityEngine() {
		return velocityEngine;
	}

	public void setVelocityEngine(VelocityEngine velocityEngine) {
		this.velocityEngine = velocityEngine;
	}
}

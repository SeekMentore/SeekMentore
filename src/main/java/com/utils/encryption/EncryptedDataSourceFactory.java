package com.utils.encryption;

import java.util.Hashtable;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.Name;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbcp2.BasicDataSourceFactory;

import com.constants.EncyptionConstants;
import com.constants.JNDIandControlConfigurationConstants;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class EncryptedDataSourceFactory extends BasicDataSourceFactory implements EncyptionConstants {
	
	private static final String DATSOURCE_PASSWORD_ENCYPTION_ALGORITHM = "AES";
	private static final String LOOKUP_PATH = "java:comp/env";
	private static final String DATASOURCE_ENCODING_STANDARD = "UTF-8";
	
	@Override
	public Object getObjectInstance(
			final Object obj, 
			final Name name, 
			final Context nameCtx, 
			final Hashtable<?, ?> environment
	) throws Exception {
		final Context initCtx = new InitialContext();
    	final Context context = (Context) initCtx.lookup(LOOKUP_PATH);
    	final String encryptionkey = (String) context.lookup(JNDIandControlConfigurationConstants.ENVIRONMENT_VARIABLE_ENCRYPTION_KEY);
	    final Object instance = super.getObjectInstance(obj, name, nameCtx, environment);
	    if (instance != null && instance instanceof BasicDataSource) {
	        final BasicDataSource basicDataSource = (BasicDataSource) instance;
	        if (basicDataSource.getPassword() != null && basicDataSource.getPassword().length() > 0) {
	        	basicDataSource.setPassword(decrypt(basicDataSource.getPassword(), encryptionkey));
	        }
	        return basicDataSource;
	    }
	    return null;
	}
	
	private String decrypt(final String value, final String keyString) throws Exception {
        Cipher cipher = Cipher.getInstance(DATSOURCE_PASSWORD_ENCYPTION_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(keyString.getBytes(), DATSOURCE_PASSWORD_ENCYPTION_ALGORITHM));
        return new String(cipher.doFinal(new BASE64Decoder().decodeBuffer(value)), DATASOURCE_ENCODING_STANDARD);
    }
	
	@SuppressWarnings("unused")
	private static String encrypt (final String value, final String keyString) throws Exception {
		Cipher cipher = Cipher.getInstance(DATSOURCE_PASSWORD_ENCYPTION_ALGORITHM);
		cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(keyString.getBytes(), DATSOURCE_PASSWORD_ENCYPTION_ALGORITHM));
        return new BASE64Encoder().encode(cipher.doFinal(value.getBytes(DATASOURCE_ENCODING_STANDARD)));
    }
}

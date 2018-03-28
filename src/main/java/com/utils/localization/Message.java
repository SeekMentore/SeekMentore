package com.utils.localization;

import java.text.MessageFormat;
import java.util.ResourceBundle;

import com.constants.MessageConstants;

public class Message implements MessageConstants {

    private static volatile ResourceBundle resource;
    
    public static String getMessage(String key) {
        resource = ResourceBundle.getBundle(MESG_PROPERTY_FILE_NAME);        
        return resource.getString(key);
    }    
    
    public static String getMessage(String key, Object[] parameters){
        resource = ResourceBundle.getBundle(MESG_PROPERTY_FILE_NAME);        
        return MessageFormat.format(resource.getString(key), parameters);
    }    
}

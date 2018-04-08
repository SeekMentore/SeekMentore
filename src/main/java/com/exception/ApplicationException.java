package  com.exception;

import com.utils.LoggerUtils;

public class ApplicationException extends RuntimeException {
	
	private static final long serialVersionUID = -6809455143331509953L;
	
	private String errorCode;
	
	public ApplicationException(final String message) {
        super(message);
        LoggerUtils.logErrorFromApplicationExceptionConstructor(this);
    }

    public ApplicationException(final Throwable t) {
        super(t);
        if (t != null) {
        	this.setStackTrace(t.getStackTrace());
        }
        LoggerUtils.logErrorFromApplicationExceptionConstructor(this);
    }

    public ApplicationException(final String message, final Throwable t) {
        super(message, t);
        if (t != null) {
        	this.setStackTrace(t.getStackTrace());
        }
        LoggerUtils.logErrorFromApplicationExceptionConstructor(this);
    }

    public ApplicationException(final String message, final String errorCode){
    	super(message);
    	this.errorCode = errorCode;
    	LoggerUtils.logErrorFromApplicationExceptionConstructor(this);
    }

    public String getErrorCode() {
		return errorCode;
	}


}

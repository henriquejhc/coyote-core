package org.coyote.core.web.jsf;

/**
 * 
 * @author Jose Henrique Cardoso
 *
 */
public class ManagedBeanException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public ManagedBeanException() {
		super();
	}
	
	public ManagedBeanException(String message) {
		super(message);
	}

	public ManagedBeanException(Throwable throwable) {
		super(throwable);
	}

	public ManagedBeanException(String message, Throwable throwable) {
		super(message, throwable);
	}
	
	public static void handleManagedBeanException() {
		throw new ManagedBeanException();		
	}
	
	public static void handleManagedBeanException(String message) {
		throw new ManagedBeanException(message);		
	}

	public static void handleManagedBeanException(Exception exception) {
		throw new ManagedBeanException(exception);		
	}
	
	public static void handleManagedBeanException(String message, Exception exception) {
		throw new ManagedBeanException(message, exception);		
	}
	
}
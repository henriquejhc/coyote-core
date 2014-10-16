package org.coyote.core.persistence.query.filter;

/**
 * 
 * @author Jose Henrique Cardoso
 *
 */
public class QueryFilterException extends Exception {

	private static final long serialVersionUID = 1L;

	public static void handlerException(Exception exception) throws Exception {
		throw new Exception(exception);
	}
	
	public QueryFilterException() {
		super();
	}

	public QueryFilterException(String message, Throwable throwable) {
		super(message, throwable);
	}

	public QueryFilterException(String message) {
		super(message);
	}

	public QueryFilterException(Throwable throwable) {
		super(throwable);
	}
	
}
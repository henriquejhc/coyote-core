package org.coyote.core.persistence;

/**
 * 
 * @author Jose Henrique Cardoso
 *
 */
public class PersistenceException extends Exception {

	private static final long serialVersionUID = 4511180810738778334L;

	public PersistenceException() {
		super();
	}

	public PersistenceException(String message, Throwable throwable) {
		super(message, throwable);
	}

	public PersistenceException(String message) {
		super(message);
	}

	public PersistenceException(Throwable throwable) {
		super(throwable);
	}

}
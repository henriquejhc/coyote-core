package org.coyote.core.web.jsf;

/**
 * 
 * @author Jose Henrique Cardoso
 *
 */
public interface IManagedBean {

	public static final int SEVERITY_INFO = 1;
	public static final int SEVERITY_WARN = 2;
	public static final int SEVERITY_ERROR = 3;
	public static final int SEVERITY_FATAL = 4;

	public static final String ONCOMPLETE_SAVE_SUCESS = "modal.hide()";
	public static final String ONCOMPLETE_SAVE_FAIL = "";

	public static final String ONCOMPLETE_REMOVE_SUCESS = "confirmationRemove.hide()";
	public static final String ONCOMPLETE_REMOVE_FAIL = "";

}
package org.coyote.core.web.jsf.util;

import javax.faces.application.FacesMessage;


public class JSFMessageUtils {

	public static final int SEVERITY_INFO = 1;
	public static final int SEVERITY_WARN = 2;
	public static final int SEVERITY_ERROR = 3;
	public static final int SEVERITY_FATAL = 4;

	protected void addMessage(String clienteId, String msg) {
		FacesMessage message = new FacesMessage(msg);
		JSFUtils.getFacesContext().addMessage(clienteId, message);
	}

	protected void addMessage(String message, int type) {
		switch (type) {
		case SEVERITY_INFO:
			JSFUtils.getFacesContext().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, message, null));
			break;
		case SEVERITY_WARN:
			JSFUtils.getFacesContext().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, message, null));
			break;
		case SEVERITY_ERROR:
			JSFUtils.getFacesContext().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
			break;
		case SEVERITY_FATAL:
			JSFUtils.getFacesContext().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, message, null));
			break;
		default:
			break;
		}
	}

	protected void addInfoMessage(String title, String message) {
		JSFUtils.getFacesContext().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, title, message));
	}

	protected void addWarnMessage(String title, String message) {
		JSFUtils.getFacesContext().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, title, message));
	}

	protected void addErrorMessage(String title, String message) {
		JSFUtils.getFacesContext().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, title, message));
	}

}

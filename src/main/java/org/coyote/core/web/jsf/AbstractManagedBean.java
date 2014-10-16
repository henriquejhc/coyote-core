package org.coyote.core.web.jsf;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.Locale;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.coyote.core.service.Service;
import org.primefaces.context.RequestContext;

/**
 * 
 * @author Jose Henrique Cardoso
 *
 */
public abstract class AbstractManagedBean implements IManagedBean, Serializable {

	private static final long serialVersionUID = -1L;
	
	public abstract void initComponents();
	
	@SuppressWarnings("rawtypes")
	protected abstract Service getService();
	
	public AbstractManagedBean() {
		System.out.println("###- " + new Date() + " -> " + getClass().getName() + " <- created -###");
	}
	
	protected FacesContext getFacesContext() {
		return FacesContext.getCurrentInstance();
	}

	protected ExternalContext getExternalContext() {
		return getFacesContext().getExternalContext();
	}
	
	protected RequestContext getRequestContext() {
		return RequestContext.getCurrentInstance();
	}

	protected HttpServletRequest getRequest() {
		return (HttpServletRequest) getExternalContext().getRequest();
	}

	protected HttpServletResponse getResponse() {
		return (HttpServletResponse) getExternalContext().getResponse();
	}

	protected void sendRedirect(String url) throws IOException {
		getResponse().sendRedirect(url);
	}

	protected Locale getRequestLocale() {
		return getRequest().getLocale();
	}

	protected Flash getFlashScope() {
		return FacesContext.getCurrentInstance().getExternalContext().getFlash();
	}
	
	protected String getRequestParameter(String name) {
		return getExternalContext().getRequestParameterMap().get(name);
	}

	protected Object getRequestAttribute(String name) {
		return getExternalContext().getRequestMap().get(name);
	}

	protected void setRequestAttribute(String name, Object value) {
		getRequest().setAttribute(name, value);
	}
	
	protected HttpSession getSession() {
		return (HttpSession) getExternalContext().getSession(false);
	}

	protected void setSessionAttribute(String name, Object value) {
		getSession().setAttribute(name, value);
	}

	protected Object getSessionAttribute(String name) {
		return getSession().getAttribute(name);
	}

	protected void removeSessionAttribute(String nome) {
		getSession().removeAttribute(nome);
	}
	
	protected void addMessage(String clienteId, String msg) {
		FacesMessage message = new FacesMessage(msg);
		getFacesContext().addMessage(clienteId, message);
	}
	
	protected void addMessage(int type, String message) {
		switch (type) {
			case SEVERITY_INFO:
				getFacesContext().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, message, null));
				break;
			case SEVERITY_WARN:
				getFacesContext().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, message, null));
				break;
			case SEVERITY_ERROR:
				getFacesContext().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
				break;
			case SEVERITY_FATAL:
				getFacesContext().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, message, null));
				break;	
			default:
				break;
		}	
	}
	
	protected void addInfoMessage(String title, String message) {
		getFacesContext().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, title, message));
	}
	
	protected void addWarnMessage(String title, String message) {
		getFacesContext().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, title, message));
	}
	
	protected void addErrorMessage(String title, String message) {
		getFacesContext().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, title, message));
	}
	
	protected void addCallbackParam(String name, Object value) {
		getRequestContext().addCallbackParam(name, value);
	}
	
	protected void execute(String action) {
		getRequestContext().execute(action);
	}
	
}
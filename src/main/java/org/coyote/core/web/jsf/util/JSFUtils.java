package org.coyote.core.web.jsf.util;

import java.io.IOException;
import java.util.Locale;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class JSFUtils {

	public static FacesContext getFacesContext() {
		return FacesContext.getCurrentInstance();
	}

	public static ExternalContext getExternalContext() {
		return getFacesContext().getExternalContext();
	}

	public static HttpServletRequest getRequest() {
		return (HttpServletRequest) getExternalContext().getRequest();
	}

	public static HttpServletResponse getResponse() {
		return (HttpServletResponse) getExternalContext().getResponse();
	}

	public static void sendRedirect(String url) throws IOException {
		getResponse().sendRedirect(url);
	}

	public static Locale getRequestLocale() {
		return getRequest().getLocale();
	}

	public static String getRequestParameter(String name) {
		return getExternalContext().getRequestParameterMap().get(name);
	}

	public static Object getRequestAttribute(String name) {
		return getExternalContext().getRequestMap().get(name);
	}

	public static void setRequestAttribute(String name, Object value) {
		getRequest().setAttribute(name, value);
	}

	public static HttpSession getSession() {
		return (HttpSession) getExternalContext().getSession(false);
	}

	public static void setSessionAttribute(String name, Object value) {
		getSession().setAttribute(name, value);
	}

	public static Object getSessionAttribute(String name) {
		return getSession().getAttribute(name);
	}

	public static void removeSessionAttribute(String nome) {
		getSession().removeAttribute(nome);
	}

}

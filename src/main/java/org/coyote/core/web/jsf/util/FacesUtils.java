package org.coyote.core.web.jsf.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.PropertyUtils;

public class FacesUtils {

	@SuppressWarnings("rawtypes")
	public static List toSelectItemList(List beanList, String valueProperty,
			String labelProperty) {
		try {
			List<SelectItem> list = new ArrayList<SelectItem>();
			for (Object object : beanList)
				list.add(new SelectItem(PropertyUtils.getProperty(object,
						valueProperty), PropertyUtils.getProperty(object,
						labelProperty).toString()));
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String getResourceBundleString(String key) {
		return ResourceBundle
				.getBundle("LocationResources", getRequestLocale()).getString(
						key);
	}

	public static boolean isMessagesEmpty() {
		return !getFacesContext().getMessages().hasNext();
	}

	public static void addErrorMessage() {
		addMessage(null, FacesMessage.SEVERITY_ERROR,
				getResourceBundleString("execucao.falha"), null);
	}

	public static void addSuccessMessage() {
		addMessage(null, FacesMessage.SEVERITY_INFO,
				getResourceBundleString("execucao.sucesso"), null);
	}

	// TODO Para utilizar este método, deve ser adicionada a biblioteca
	// hibernate-validator-xxx.jar no classpath
	/*
	 * public static void addMessage(InvalidValue... invalidValues){ for
	 * (InvalidValue invalidValue : invalidValues) addMessage(null,
	 * FacesMessage.SEVERITY_WARN, invalidValue.getMessage(), null); }
	 */

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

	public static void setRequestAttribute(String key, Object value) {
		getExternalContext().getRequestMap().put(key, value);
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

	public static <T> T evaluateExpressionGet(String elExpression,
			Class<T> clazz) {
		// return (T)
		// getFacesContext().getApplication().evaluateExpressionGet(getFacesContext(),
		// elExpression, clazz);
		return null;
	}

	public static void addMessage(String clientId, Severity severity,
			String summary, String detail) {
		getFacesContext().addMessage(clientId,
				new FacesMessage(severity, summary, detail));
	}

}
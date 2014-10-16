package org.coyote.core.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.coyote.core.application.ApplicationConstants;
import org.coyote.core.persistence.model.EntityBean;

/**
 * 
 * @author Jose Henrique Cardoso
 *
 */
public class ApplicationFilter implements Filter {

	protected String getApplicationContext(ServletRequest servletRequest) {
		return ((HttpServletRequest)servletRequest).getContextPath();
	}
	
	protected EntityBean getUserSession(ServletRequest servletRequest) {
		return (EntityBean) ((HttpServletRequest)servletRequest).getSession().getAttribute(ApplicationConstants.USER_SESSION);
	}
	
	protected boolean userSessionIsNull(EntityBean userSession) {
		return userSession == null;
	}
	
	protected String getURL(ServletRequest servletRequest) {
		return ((HttpServletRequest)servletRequest).getRequestURL().toString();
	}
	
	protected String getURI(ServletRequest servletRequest) {
		return ((HttpServletRequest)servletRequest).getRequestURI();
	}
	
	protected void redirect(ServletResponse servletResponse, String redirect) {
		try {
			((HttpServletResponse)servletResponse).sendRedirect(redirect);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}
	
}
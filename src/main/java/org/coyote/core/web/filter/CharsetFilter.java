package org.coyote.core.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class CharsetFilter implements Filter {

	private static final String CONTENT_TYPE = "text/html; charset=UTF-8";
	private static final String ENCODING = "UTF-8";

	public void init(FilterConfig config) throws ServletException {

	}

	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, 
			FilterChain filterChain) throws IOException, ServletException {

		servletRequest.setCharacterEncoding(ENCODING);

		servletResponse.setContentType(CONTENT_TYPE);

		servletResponse.setCharacterEncoding(ENCODING);

		filterChain.doFilter(servletRequest, servletResponse);
	}

	public void destroy() {

	}

}
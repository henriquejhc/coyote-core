package org.coyote.core.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

public class NoCacheFilter implements Filter {

	private static final String HEADER_CACHE_CONTROL = "Cache-Control";
	private static final String DATE_HEADER_EXPIRES = "Expires";
	private static final String HEADER_PRAGMA = "Pragma";

	private static final String PRAGMA_NO_CACHE = "No-cache";
	private static final int EXPIRES_VALUE = 0;
	private static final String CACHE_CONTROL_NO_CACHE = "no-cache";

	public void init(FilterConfig filterConfig) {

	}

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		HttpServletResponse httpResponse = (HttpServletResponse) response;

		httpResponse.setHeader(HEADER_CACHE_CONTROL, CACHE_CONTROL_NO_CACHE);
		httpResponse.setDateHeader(DATE_HEADER_EXPIRES, EXPIRES_VALUE);
		httpResponse.setHeader(HEADER_PRAGMA, PRAGMA_NO_CACHE);
		chain.doFilter(request, response);

	}
}
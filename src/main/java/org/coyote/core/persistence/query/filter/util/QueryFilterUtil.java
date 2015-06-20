package org.coyote.core.persistence.query.filter.util;

import java.lang.reflect.Method;

public class QueryFilterUtil {
	
	private static final String EMPTY_VALUE = "";
	private static final String PREFIX_GET = "get";

	public static String formatName(String name) {

		if (name.contains(PREFIX_GET)) {
			name = name.replace(PREFIX_GET, EMPTY_VALUE);
			name = name.substring(0, 1).toLowerCase().concat(name.substring(1));
		}

		return name;
	}

	public static String formatMethod(String methodName) {
		methodName = methodName.substring(0, 1).toUpperCase().concat(methodName.substring(1));
		return PREFIX_GET + methodName;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Method getMethodByName(Class entityClass, String methodName) throws NoSuchMethodException, SecurityException {
		return entityClass.getDeclaredMethod(formatMethod(methodName), null);
	}

}

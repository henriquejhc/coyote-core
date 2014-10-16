package org.coyote.core.persistence.query.filter.util;

import java.lang.annotation.Annotation;

import javax.persistence.Entity;

public class QueryFilterValidator {

	public static boolean entityIsNull(Object entity) {
		return entity == null;
	}
	
	@SuppressWarnings("rawtypes")
	public static boolean isEntity(Class entityClass) {

		Annotation[] annotations = entityClass.getAnnotations();
		
		for (Annotation annotation : annotations) {
			if (annotation instanceof Entity)
				return true;
		}
		
		return false;
	}
	
}
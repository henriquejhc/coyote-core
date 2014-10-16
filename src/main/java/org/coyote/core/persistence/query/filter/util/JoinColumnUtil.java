package org.coyote.core.persistence.query.filter.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.JoinColumn;

import org.coyote.core.persistence.query.filter.model.JoinColumnBean;

public class JoinColumnUtil {

	@SuppressWarnings("rawtypes")
	public static List<JoinColumnBean> getAttributesJoinColumnBean(Class entityClass) {
		List<JoinColumnBean> fieldsJoinColumn = loadFieldsJoinColumn(entityClass.getDeclaredFields());
		List<JoinColumnBean> methodsJoinColumn = loadMethodsJoinColumn(entityClass.getDeclaredMethods());
		return getAttributesJoinColumn(fieldsJoinColumn, methodsJoinColumn);
	}

	private static List<JoinColumnBean> getAttributesJoinColumn(List<JoinColumnBean> fieldsJoinColumn, List<JoinColumnBean> methodsJoinColumn) {
		for (JoinColumnBean joinColumnBean : methodsJoinColumn) {
			if (!fieldsJoinColumn.equals(joinColumnBean)) {
				fieldsJoinColumn.add(joinColumnBean);
			}
		}
		return fieldsJoinColumn;
	}

	public static List<JoinColumnBean> loadFieldsJoinColumn(Field[] fields) {

		List<JoinColumnBean> fieldList = new ArrayList<JoinColumnBean>();

		for (Field field : fields) {
			Annotation[] annotations = field.getDeclaredAnnotations();
			for (Annotation annotation : annotations) {
				if (annotation instanceof JoinColumn) {
					fieldList.add(new JoinColumnBean(field.getName(), field.getType().getSimpleName()));
					continue;
				}
			}
		}

		return fieldList;
	}

	public static List<JoinColumnBean> loadMethodsJoinColumn(Method[] methods) {

		List<JoinColumnBean> fieldList = new ArrayList<JoinColumnBean>();

		for (Method method : methods) {
			Annotation[] annotations = method.getDeclaredAnnotations();
			for (Annotation annotation : annotations) {
				if (annotation instanceof JoinColumn) {
					fieldList.add(new JoinColumnBean(QueryFilterUtil.formatName(method.getName()), method.getReturnType().getSimpleName()));
					continue;
				}
			}
		}

		return fieldList;
	}
	
	/**
	 * Pega os valores dos atributos mapeados com @JoinColumn da entity principal.
	 * 
	 * @param entity
	 * @param attributesJoinColumnBean
	 * @return
	 */
	public static Collection<JoinColumnBean> getValuesJoinColumn(Object entity, Collection<JoinColumnBean> attributesJoinColumnBean)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {

		for (JoinColumnBean joinColumnBean : attributesJoinColumnBean) {
			Method method = QueryFilterUtil.getMethodByName(entity.getClass(), joinColumnBean.getName());
			joinColumnBean.setValue(method.invoke(entity, null));
		}

		return attributesJoinColumnBean;
	}
}
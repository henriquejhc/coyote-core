package org.coyote.core.persistence.query.filter.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;

import org.coyote.core.persistence.query.filter.model.ColumnBean;

public class ColumnUtil {

	@SuppressWarnings("rawtypes")
	public static List<ColumnBean> getAttributesColumnBean(Class entityClass) {
		List<ColumnBean> fieldsColumn = loadFieldsColumn(entityClass.getDeclaredFields());
		List<ColumnBean> methodsColumn = loadMethodsColumn(entityClass.getDeclaredMethods());
		return getAttributesColumn(fieldsColumn, methodsColumn);
	}

	private static List<ColumnBean> getAttributesColumn(List<ColumnBean> fieldsColumn, List<ColumnBean> methodsColumn) {
		for (ColumnBean columnBean : methodsColumn) {
			if (!fieldsColumn.equals(columnBean)) {
				fieldsColumn.add(columnBean);
			}
		}
		return fieldsColumn;
	}

	public static List<ColumnBean> loadFieldsColumn(Field[] fields) {

		List<ColumnBean> fieldList = new ArrayList<ColumnBean>();

		for (Field field : fields) {
			Annotation[] annotations = field.getDeclaredAnnotations();
			for (Annotation annotation : annotations) {
				if (annotation instanceof Column) {
					fieldList.add(new ColumnBean(field.getName(), field.getType().getSimpleName()));
					continue;
				}
			}
		}

		return fieldList;
	}

	public static List<ColumnBean> loadMethodsColumn(Method[] methods) {

		List<ColumnBean> fieldList = new ArrayList<ColumnBean>();

		for (Method method : methods) {
			Annotation[] annotations = method.getDeclaredAnnotations();
			for (Annotation annotation : annotations) {
				if (annotation instanceof Column) {
					fieldList.add(new ColumnBean(QueryFilterUtil.formatName(method.getName()), method.getReturnType().getSimpleName()));
					continue;
				}
			}
		}

		return fieldList;
	}

}
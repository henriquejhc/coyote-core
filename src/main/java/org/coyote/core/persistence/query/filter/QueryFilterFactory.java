package org.coyote.core.persistence.query.filter;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;

import org.coyote.core.persistence.model.EntityBean;
import org.coyote.core.persistence.query.filter.model.ColumnBean;
import org.coyote.core.persistence.query.filter.model.JoinColumnBean;
import org.coyote.core.persistence.query.filter.util.ColumnUtil;
import org.coyote.core.persistence.query.filter.util.JoinColumnUtil;
import org.coyote.core.persistence.query.filter.util.QueryFilterUtil;
import org.coyote.core.persistence.query.filter.util.QueryFilterValidator;

/**
 * 
 * @author Jose Henrique Cardoso
 *
 */
public class QueryFilterFactory {

	public static QueryFilter create(Object entity) {
		return createFilter(entity);
	}

	private static QueryFilter createFilter(Object entity) {
		try {
			return makeFilter(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private static QueryFilter makeFilter(Object entity) throws IllegalAccessException, IllegalArgumentException,
																InvocationTargetException, NoSuchMethodException, 
																SecurityException, QueryFilterException {

		if (QueryFilterValidator.entityIsNull(entity)) {
			throw new QueryFilterException("Object is null.");
		}

		Class<?> entityClass = entity.getClass();

		if (!QueryFilterValidator.isEntity(entityClass)) {
			throw new QueryFilterException("The object is not an entity.");
		}

		// Carrega todos os atributos anotados com @Column da entidade principal
		Collection<ColumnBean> attributesColumnBean = getAttributesColumn(entityClass);

		// Carrega todos os atributos anotados com @JoinColumn da entidade principal
		Collection<JoinColumnBean> attributesJoinColumnBean = getAttributesJoinColumn(entityClass);

		makeChildJoinColumns(entity, attributesJoinColumnBean);

		return makeFilters(entity, attributesColumnBean, attributesJoinColumnBean);
	}

	/**
	 * Percorre toda a hierarquia dos JOINCOLUMN existente na Entity principal
	 * montando também os COLUMNs. Caso encontre algum atributo JOINCOLUMN
	 * dentro de outro JOINCOLUMN entrará em recursividade navegando até o
	 * último filho.
	 * 
	 * @param entity
	 * @param attributesJoinColumnBean
	 */
	private static void makeChildJoinColumns(Object entity, Collection<JoinColumnBean> attributesJoinColumnBean) 
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, 
				   NoSuchMethodException, SecurityException {

		JoinColumnUtil.getValuesJoinColumn(entity, attributesJoinColumnBean);

		for (JoinColumnBean joinColumnBean : attributesJoinColumnBean) {
			if (joinColumnBean.getValue() != null) {
				joinColumnBean.setColumns(getAttributesColumn(joinColumnBean.getValue().getClass()));
				joinColumnBean.setJoinColumns(getAttributesJoinColumn(joinColumnBean.getValue().getClass()));
				makeChildJoinColumns(joinColumnBean.getValue(), joinColumnBean.getJoinColumns());
			}
		}

	}

	private static QueryFilter makeFilters(Object entity, Collection<ColumnBean> attributesColumnBean,
										   Collection<JoinColumnBean> attributesJoinColumnBean) throws NoSuchMethodException, 
										   															   IllegalAccessException, 
										   														       InvocationTargetException {

		QueryFilter queryFilter = new QueryFilter();

		queryFilter.setRootClass(((EntityBean) entity).getClass());
		
		Collection<ColumnBean> columnBeansList = makeListColumnBean(entity, attributesColumnBean);
		Collection<JoinColumnBean> joinColumnBeansList = makeValuesColumnInJoinColumn(attributesJoinColumnBean);

		for (ColumnBean columnBean : columnBeansList) {

			if (columnBean.getType().equals(String.class.getSimpleName())) {
				queryFilter.addFilter(new Filter(columnBean.getName(), columnBean.getValue(), OperatorType.LIKE));

			} else if (columnBean.getType().equals(Long.class.getSimpleName()) && (columnBean.getValue() != null && Long.parseLong(columnBean.getValue().toString()) > 0)) {
				queryFilter.addFilter(new Filter(columnBean.getName(), columnBean.getValue(), OperatorType.EQUALS));
			
			} else if (columnBean.getType().equals(Double.class.getSimpleName()) && (columnBean.getValue() != null)) {
				queryFilter.addFilter(new Filter(columnBean.getName(), columnBean.getValue(), OperatorType.EQUALS));
			}
			
			
		}

		for (JoinColumnBean joinColumnBean : joinColumnBeansList) {
			makeQueryFilterJoinColumn(queryFilter, joinColumnBean, "");
		}

		return queryFilter;
	}

	private static void makeQueryFilterJoinColumn(QueryFilter queryFilter, JoinColumnBean joinColumnBeansParam, String nameParent) {

		if (joinColumnBeansParam.getColumns() == null) {
			return;
		}

		for (ColumnBean columnBean : joinColumnBeansParam.getColumns()) {

			Filter filter = null;
			String nameFilter = (nameParent != null && nameParent.isEmpty()) ? "" : nameParent + ".";

			if (columnBean.getType().equals(String.class.getSimpleName())) {
				filter = new Filter(nameFilter + joinColumnBeansParam.getName(), columnBean.getValue(), OperatorType.LIKE);
			} else if (columnBean.getType().equals(Long.class.getSimpleName()) && 
					  (columnBean.getValue() != null && Long.parseLong(columnBean.getValue().toString()) > 0)) {
				filter = new Filter(nameFilter + joinColumnBeansParam.getName() + "." + columnBean.getName(), columnBean.getValue(), OperatorType.EQUALS);
			} else {
				filter = new Filter(nameFilter + joinColumnBeansParam.getName() + "." + columnBean.getName(), columnBean.getValue(), OperatorType.EQUALS);
			}

			if (!queryFilter.getFilters().contains(filter)) {
				queryFilter.addFilter(filter);
			}

			if (joinColumnBeansParam.getJoinColumns() != null && !joinColumnBeansParam.getJoinColumns().isEmpty()) {
				for (JoinColumnBean joinColumnBean : joinColumnBeansParam.getJoinColumns()) {
					String name = (nameParent != null && nameParent.isEmpty()) ? "" : nameParent + ".";
					makeQueryFilterJoinColumn(queryFilter, joinColumnBean, name + joinColumnBeansParam.getName());
				}
			}
		}
	}

	private static Collection<JoinColumnBean> makeValuesColumnInJoinColumn(Collection<JoinColumnBean> attributesJoinColumnBean)
			throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {

		for (JoinColumnBean joinColumnBean : attributesJoinColumnBean) {

			if (joinColumnBean.getValue() != null) {
				joinColumnBean.setColumns(makeListColumnBean(joinColumnBean.getValue(), joinColumnBean.getColumns()));
				if (joinColumnBean.getJoinColumns() != null && !joinColumnBean.getJoinColumns().isEmpty()) {
					makeValuesColumnInJoinColumn(joinColumnBean.getJoinColumns());
				}
			}
		}

		return attributesJoinColumnBean;
	}

	private static Collection<ColumnBean> makeListColumnBean(Object entity, Collection<ColumnBean> attributesColumnBean)
			throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {

		Collection<ColumnBean> columnBeansList = new ArrayList<ColumnBean>();

		for (ColumnBean columnBean : attributesColumnBean) {
			Method method = QueryFilterUtil.getMethodByName(entity.getClass(), columnBean.getName());
			columnBean.setValue(method.invoke(entity, null));
			columnBeansList.add(columnBean);
		}

		return columnBeansList;
	}

	@SuppressWarnings("rawtypes")
	private static Collection<ColumnBean> getAttributesColumn(Class entityClass) {
		return ColumnUtil.getAttributesColumnBean(entityClass);
	}

	@SuppressWarnings("rawtypes")
	private static Collection<JoinColumnBean> getAttributesJoinColumn(Class entityClass) {
		return JoinColumnUtil.getAttributesJoinColumnBean(entityClass);
	}

}
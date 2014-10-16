package org.coyote.core.persistence.query.filter;

import java.util.ArrayList;
import java.util.List;

import org.coyote.core.persistence.model.EntityBean;

/**
 * 
 * @author Jose Henrique Cardoso
 * 
 */
public class QueryFilter {

	public static final String SELECT_COUNT = " select count(entity) from class as entity ";
	public static final String SELECT = " from class as entity ";
	public static final String WHERE = " where ";
	public static final String ORDER_BY = " order by orderBy ";
	public static final Integer NO_PAGINATOR = -1;
	public static final String DIFFERENT = " <> ";
	public static final String LIKE = ") like ";
	public static final String OR = " or ";
	public static final String OPEN = "open";
	public static final String CLOSE = "close";
	public static final String MAIOR = " > ";
	public static final String MENOR = " < ";
	public static final String MAIOR_OU_IGUAL = " >= ";
	public static final String MENOR_OU_IGUAL = " <= ";
	public static final String IS_NOT_NULL = " is not null ";
	public static final String IS_NULL = " is null ";
	public static final String VALUE_NULL = " valueNull ";

	private List<Filter> filters;
	private String orderBy;
	private Integer firstResult;
	private Integer maxResults;

	public Class<? extends EntityBean> rootClass;
	
	public QueryFilter() {
		super();
		this.filters = new ArrayList<Filter>();
	}

	public void addFilter(Filter filter) {
		this.filters.add(filter);
	}

	public void addFilter(String field, Object value) {
		this.filters.add(new Filter(field, value));
	}

	public void addArgument(String argument) {
		this.filters.add(new Filter(argument, VALUE_NULL));
	}

	public void removeInvalidFilters() {
		List<Filter> filterCopy = copyFilterList();
		filters = new ArrayList<Filter>();
		filters.addAll(filterCopy);
	}

	private List<Filter> copyFilterList() {

		List<Filter> filterCopy = new ArrayList<Filter>();

		for (Filter filter : filters) {
			if ((filter.getOperatorType().equals(OperatorType.LIKE) && filter.getValue() != null && !filter.getValue().equals(""))
					|| (filter.getOperatorType().equals(OperatorType.EQUALS) && filter.getValue() != null && !filter.getValue().equals(""))
					//|| (!filter.getOperatorType().equals(OperatorType.LIKE) && filter.getValue() != new Long(-1))
					|| (filter.getOperatorType().equals(OperatorType.GREATER) && filter.getValue() != null)
					|| (filter.getOperatorType().equals(OperatorType.LESS) && filter.getValue() != null)
					|| (filter.getOperatorType().equals(OperatorType.GREATER_OR_EQUAL) && filter.getValue() != null)
					|| (filter.getOperatorType().equals(OperatorType.LESS_OR_EQUAL) && filter.getValue() != null)
					|| (filter.getOperatorType().equals(OperatorType.DIFFERENT) && filter.getValue() != null)
					|| filter.getOperatorType().equals(OperatorType.AND) //
					|| filter.getOperatorType().equals(OperatorType.OR) //
					|| filter.getOperatorType().equals(OperatorType.OPEN) //
					|| filter.getOperatorType().equals(OperatorType.CLOSE) //
					|| filter.getOperatorType().equals(OperatorType.OPEN)) {

				filterCopy.add(filter);
			}
		}

		return filterCopy;
	}

	public List<Filter> getFilters() {
		return this.filters;
	}

	public QueryFilter setFilters(List<Filter> filters) {
		this.filters = filters;
		return this;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public QueryFilter setOrderBy(String orderBy) {
		this.orderBy = orderBy;
		return this;
	}

	public Integer getFirstResult() {
		return firstResult;
	}

	public QueryFilter setFirstResult(Integer firstResult) {
		this.firstResult = firstResult;
		return this;
	}

	public Integer getMaxResults() {
		return maxResults;
	}

	public QueryFilter setMaxResults(Integer maxResults) {
		this.maxResults = maxResults;
		return this;
	}

	public Class<? extends EntityBean> getRootClass() {
		return rootClass;
	}

	public QueryFilter setRootClass(Class<? extends EntityBean> rootClass) {
		this.rootClass = rootClass;
		return this;
	}

	@Override
	public String toString() {
		return "QueryFilter [rootClass=" + rootClass.getName() + ", filters=" + filters.toString() + ", orderBy=" + orderBy + ", firstResult=" + firstResult + ", maxResults=" + maxResults + "]";
	}
	
	public void printFilters() {
		for (Filter filter : this.filters) {
			System.out.println(filter.toString());
		}
	}

}
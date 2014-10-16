package org.coyote.core.persistence.query;

import org.coyote.core.persistence.query.filter.Filter;
import org.coyote.core.persistence.query.filter.OperatorType;
import org.coyote.core.persistence.query.filter.QueryFilter;

public class QueryBuilder {

//	public static void create(QueryFilter queryFilter, StringBuilder hql) {
//
//		if (queryFilter.getFilters() != null && !queryFilter.getFilters().isEmpty()) {
//
//			hql.append(QueryFilter.WHERE);
//
//			int sizeListFilters = queryFilter.getFilters().size();
//
//			for (int indexFilter = 0; indexFilter < sizeListFilters; indexFilter++) {
//
//				if (queryFilter.getFilters().get(indexFilter).getOperatorType().equals(OperatorType.OPEN)) {
//					hql.append(" ( ");
//				} else if (queryFilter.getFilters().get(indexFilter).getOperatorType().equals(OperatorType.CLOSE)) {
//					hql.append(" ) ");
//				} else if (queryFilter.getFilters().get(indexFilter).getName().equals(OperatorType.OR)) {
//					hql.append(QueryFilter.OR);
//				} else if (queryFilter.getFilters().get(indexFilter).getOperatorType().equals(OperatorType.IS_NOT_NULL)
//						|| queryFilter.getFilters().get(indexFilter).getOperatorType().equals(OperatorType.IS_NULL)) {
//					hql.append(queryFilter.getFilters().get(indexFilter));
//				} else if (queryFilter.getFilters().get(indexFilter).getOperatorType().equals(OperatorType.DIFFERENT)
//						|| queryFilter.getFilters().get(indexFilter).getOperatorType().equals(OperatorType.GREATER)
//						|| queryFilter.getFilters().get(indexFilter).getOperatorType().equals(OperatorType.LESS)
//						|| queryFilter.getFilters().get(indexFilter).getOperatorType().equals(OperatorType.GREATER_OR_EQUAL)
//						|| queryFilter.getFilters().get(indexFilter).getOperatorType().equals(OperatorType.LESS_OR_EQUAL)) {
//					hql.append(queryFilter.getFilters().get(indexFilter).getName() + " :parameter" + indexFilter);
//				} else if (queryFilter.getFilters().get(indexFilter).getOperatorType().equals(OperatorType.LIKE)) {
//					hql.append("upper(" + queryFilter.getFilters().get(indexFilter).getName() + ") like" + " upper(:parameter"
//							+ indexFilter + ")");
//				} else {
//					hql.append(queryFilter.getFilters().get(indexFilter).getName() + " = :parameter" + indexFilter);
//				}
//
//				if (indexFilter < (sizeListFilters - 1)) {
//					if (!queryFilter.getFilters().get(indexFilter).getOperatorType().equals(OperatorType.OPEN)
//							&& !queryFilter.getFilters().get(indexFilter + 1).getOperatorType().equals(OperatorType.CLOSE)
//							&& !queryFilter.getFilters().get(indexFilter).getOperatorType().equals(OperatorType.OR)
//							&& !queryFilter.getFilters().get(indexFilter + 1).getOperatorType().equals(OperatorType.OR)) {
//						hql.append(" and ");
//					}
//				}
//			}
//		}
//
//		if (queryFilter != null && (queryFilter.getOrderBy() != null && !queryFilter.getOrderBy().trim().equals(""))) {
//			hql.append(" " + QueryFilter.ORDER_BY.replaceAll("orderBy", queryFilter.getOrderBy()));
//		}
//	}
	
	public static void create(QueryFilter queryFilter, StringBuilder hql) {

		if (queryFilter.getFilters() != null && !queryFilter.getFilters().isEmpty()) {

			hql.append(QueryFilter.WHERE);

			int sizeListFilters = queryFilter.getFilters().size();

			for (int indexFilter = 0; indexFilter < sizeListFilters; indexFilter++) {

				if (isOpen(queryFilter.getFilters().get(indexFilter))) {
					hql.append(" ( ");
				} 
				
				else if (isClose(queryFilter.getFilters().get(indexFilter))) {
					hql.append(" ) ");
				} 
				
				else if (isOr(queryFilter.getFilters().get(indexFilter))) {
					hql.append(QueryFilter.OR);
				} 
				
				else if (isNotNull(queryFilter.getFilters().get(indexFilter)) || isNull(queryFilter.getFilters().get(indexFilter))) {
					hql.append(queryFilter.getFilters().get(indexFilter));
				} 
				
				else if (isDifferent(queryFilter.getFilters().get(indexFilter)) || isGreater(queryFilter.getFilters().get(indexFilter))
						|| isLess(queryFilter.getFilters().get(indexFilter)) || isGreaterOrEqual(queryFilter.getFilters().get(indexFilter))
						|| isLessOrEqual(queryFilter.getFilters().get(indexFilter))) {
					hql.append(queryFilter.getFilters().get(indexFilter).getName() + " :parameter" + indexFilter);
				} 
				
				else if (isLike(queryFilter.getFilters().get(indexFilter))) {
					hql.append("upper(" + queryFilter.getFilters().get(indexFilter).getName() + ") like" + " upper(:parameter" + indexFilter + ")");
				} 
				
				else {
					hql.append(queryFilter.getFilters().get(indexFilter).getName() + " = :parameter" + indexFilter);
				}

				if (indexFilter < (sizeListFilters - 1)) {
					if (!isOpen(queryFilter.getFilters().get(indexFilter)) && !isClose(queryFilter.getFilters().get(indexFilter + 1))
							&& !isOr(queryFilter.getFilters().get(indexFilter)) && !isOr(queryFilter.getFilters().get(indexFilter + 1))) {
						hql.append(" and ");
					}
				}
			}
		}

		if (queryFilter != null && (queryFilter.getOrderBy() != null && !queryFilter.getOrderBy().trim().equals(""))) {
			hql.append(" " + QueryFilter.ORDER_BY.replaceAll("orderBy", queryFilter.getOrderBy()));
		}
	}
	
	private static boolean isOpen(Filter filter) {
		return filter.getOperatorType().equals(OperatorType.OPEN);
	}

	private static boolean isClose(Filter filter) {
		return filter.getOperatorType().equals(OperatorType.CLOSE);
	}

	private static boolean isOr(Filter filter) {
		return filter.getOperatorType().equals(OperatorType.OR);
	}

	private static boolean isLike(Filter filter) {
		return filter.getOperatorType().equals(OperatorType.LIKE);
	}

	private static boolean isNotNull(Filter filter) {
		return filter.getOperatorType().equals(OperatorType.IS_NOT_NULL);
	}

	private static boolean isNull(Filter filter) {
		return filter.getOperatorType().equals(OperatorType.IS_NULL);
	}

	private static boolean isDifferent(Filter filter) {
		return filter.getOperatorType().equals(OperatorType.DIFFERENT);
	}

	private static boolean isGreater(Filter filter) {
		return filter.getOperatorType().equals(OperatorType.GREATER);
	}

	private static boolean isLess(Filter filter) {
		return filter.getOperatorType().equals(OperatorType.LESS);
	}

	private static boolean isGreaterOrEqual(Filter filter) {
		return filter.getOperatorType().equals(OperatorType.GREATER_OR_EQUAL);
	}

	private static boolean isLessOrEqual(Filter filter) {
		return filter.getOperatorType().equals(OperatorType.LESS_OR_EQUAL);
	}

}

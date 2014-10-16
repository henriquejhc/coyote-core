package org.coyote.core.persistence.query;

import javax.persistence.Query;

import org.coyote.core.persistence.query.filter.OperatorType;
import org.coyote.core.persistence.query.filter.QueryFilter;


public class QueryParameter {

	public static void set(QueryFilter queryFilter, Query query) {

		if (queryFilter.getFilters() != null && !queryFilter.getFilters().isEmpty()) {

			int sizeListfilters = queryFilter.getFilters().size();

			for (int indexFilter = 0; indexFilter < sizeListfilters; indexFilter++) {

				if (!queryFilter.getFilters().get(indexFilter).getOperatorType().equals(OperatorType.OPEN)
						&& !queryFilter.getFilters().get(indexFilter).getOperatorType().equals(OperatorType.CLOSE)
						&& !queryFilter.getFilters().get(indexFilter).getOperatorType().equals(OperatorType.OR)
						&& !queryFilter.getFilters().get(indexFilter).getOperatorType().equals(OperatorType.IS_NULL)
						&& !queryFilter.getFilters().get(indexFilter).getOperatorType().equals(OperatorType.IS_NOT_NULL)) {

					if (queryFilter.getFilters().get(indexFilter).getOperatorType().equals(OperatorType.LIKE))
						query.setParameter("parameter" + indexFilter, "%" + queryFilter.getFilters().get(indexFilter).getValue() + "%");
					else
						query.setParameter("parameter" + indexFilter, queryFilter.getFilters().get(indexFilter).getValue());
				}
			}
		}
	}

}

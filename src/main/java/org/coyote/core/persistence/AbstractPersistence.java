package org.coyote.core.persistence;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.coyote.core.persistence.model.EntityBean;
import org.coyote.core.persistence.query.QueryBuilder;
import org.coyote.core.persistence.query.QueryParameter;
import org.coyote.core.persistence.query.filter.QueryFilter;

/**
 * 
 * @author Jose Henrique Cardoso
 *
 */
public abstract class AbstractPersistence<T extends EntityBean> implements Serializable, Persistence<T> {

	private static final long serialVersionUID = 1L;

	protected EntityManager entityManager;
	protected Class<T> entityClass;
	
	@SuppressWarnings("unchecked")
	public AbstractPersistence() {
		super();
		this.entityClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public AbstractPersistence(Class entityClass) {
		super();
		this.entityClass = entityClass;
	}
	
	@Override
	public T getEntity(Long id) {
		return (T) this.entityManager.find(this.entityClass, id);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public T getEntity(Class entityClass, Long id) {
		return (T) this.entityManager.find(entityClass, id);
	}

	@Override
	public Long count() {
		QueryFilter queryFilter = new QueryFilter();
		queryFilter.setRootClass(this.entityClass);
		return count(queryFilter);
	}

	@Override
	public Collection<T> find() {
		QueryFilter queryFilter = new QueryFilter();
		queryFilter.setRootClass(this.entityClass);
		return find(queryFilter);
	}

	@Override
	public Long count(QueryFilter queryFilter) {

		try {

			queryFilter.removeInvalidFilters();
			
			StringBuilder hql = new StringBuilder(QueryFilter.SELECT_COUNT.replaceAll("class", entityClass.getName()));

			QueryBuilder.create(queryFilter, hql);
			
			Query query = entityManager.createQuery(hql.toString());

			QueryParameter.set(queryFilter, query);

			return (Long) query.getSingleResult();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> find(QueryFilter queryFilter) {

		try {

			queryFilter.removeInvalidFilters();

			StringBuilder hql = new StringBuilder(QueryFilter.SELECT.replaceAll("class", entityClass.getName()));
			
			QueryBuilder.create(queryFilter, hql);
			
			Query query = entityManager.createQuery(hql.toString());

			QueryParameter.set(queryFilter, query);

			if (queryFilter != null && 
					((queryFilter.getFirstResult() != null && queryFilter.getFirstResult() != QueryFilter.NO_PAGINATOR) && 
					 (queryFilter.getFirstResult() != null && queryFilter.getMaxResults() != QueryFilter.NO_PAGINATOR))) {
				query.setFirstResult(queryFilter.getFirstResult());
				query.setMaxResults(queryFilter.getMaxResults());
			}

			return query.getResultList();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
}
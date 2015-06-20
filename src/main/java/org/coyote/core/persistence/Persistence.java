package org.coyote.core.persistence;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;

import org.coyote.core.persistence.query.filter.QueryFilter;

/**
 * 
 * @author Jose Henrique Cardoso
 *
 */
@SuppressWarnings("rawtypes")
public interface Persistence<T> {

	public void setEntityManager(EntityManager entityManager);

	public void save(T entity);
	
	public T merge(T entity);
	
	public void remove(T entity);
	
	public T getEntity(Long id);
	
	public T getEntity(Class entityClass, Long id);
	
	public Long count();
	
	public Collection<T> find();
	
	public Long count(QueryFilter queryFilter);
	
	public List<T> find(QueryFilter queryFilter);
	
}
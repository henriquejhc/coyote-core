package org.coyote.core.service;

import java.util.Collection;
import java.util.List;

import org.coyote.core.persistence.Persistence;
import org.coyote.core.persistence.query.filter.QueryFilter;

@SuppressWarnings("rawtypes")
public interface Service<T> {
	
	public Persistence getPersistence();

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
package org.coyote.core.service;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.List;

import org.coyote.core.persistence.Persistence;
import org.coyote.core.persistence.model.EntityBean;
import org.coyote.core.persistence.query.filter.QueryFilter;


public abstract class AbstractService<T extends EntityBean> implements Serializable, Service<T> {

	private static final long serialVersionUID = 1L;

	protected Class<T> entityClass;

	@SuppressWarnings("rawtypes")
	public abstract Persistence getPersistence();
	
	@SuppressWarnings("unchecked")
	public AbstractService() {
		super();
		this.entityClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public AbstractService(Class entityClass) {
		super();
		this.entityClass = entityClass;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void save(T entity) {
		getPersistence().save(entity);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public T merge(T entity) {
		return (T) getPersistence().merge(entity);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void remove(T entity) {
		getPersistence().remove(entity);
	}

	@SuppressWarnings("unchecked")
	@Override
	public T getEntity(Long id) {
		return (T) getPersistence().getEntity(id);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public T getEntity(Class entityClass, Long id) {
		return (T) getPersistence().getEntity(entityClass, id);
	}

	@Override
	public Long count() {
		return getPersistence().count();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Collection find() {
		return getPersistence().find();
	}

	@Override
	public Long count(QueryFilter queryFilter) {
		return getPersistence().count(queryFilter);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List find(QueryFilter queryFilter) {
		return getPersistence().find(queryFilter);
	}

}

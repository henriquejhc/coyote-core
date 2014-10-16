package org.coyote.core.web.jsf;

import java.io.Serializable;
import java.util.List;

import javax.faces.event.ActionEvent;

import org.coyote.core.persistence.model.EntityBean;
import org.coyote.core.persistence.query.filter.QueryFilterFactory;
import org.primefaces.model.LazyDataModel;

public abstract class AbstractManagedBeanBasic<T extends EntityBean> extends AbstractManagedBeanBase<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	private Class<T> entityClass;

	private EntityBean entity;
	private EntityBean entitySearch;

	private LazyDataModel<EntityBean> entityList;

	public AbstractManagedBeanBasic() {
		super();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public AbstractManagedBeanBasic(Class entityClass) {
		super();
		this.entityClass = entityClass;
	}

	@Override
	public void initComponents() {

	}

	public void initializeDataModel() {
		this.entityList = new LazyDataModelBasic(this);
	}
	
	public void posActionSave() {
		execute("PF('formDialogVar').hide();");
	}
	
	public void posActionRemove() {
		execute("PF('confirmationRemove').hide();");
	}
	
	protected boolean validate() {
		return true;
	}

	public void newEntity() {
		initializeEntity();
	}

	public void initializeEntity() {
		try {
			this.entity = this.entityClass.newInstance();
		} catch (Exception e) {
			ManagedBeanException.handleManagedBeanException("Ocorreu um erro ao inicializar a [entity]: " + this.entityClass, e);
		} 
	}

	public void initializeEntitySearch() {
		try {
			this.entitySearch = this.entityClass.newInstance();
		} catch (Exception e) {
			ManagedBeanException.handleManagedBeanException("Ocorreu um erro ao inicializar a [entitySearch]: " + this.entityClass, e);
		} 
	}

	@Override
	public void save(ActionEvent event) {
		saveEntity(event);
	}

	public void saveRefreshGrid(ActionEvent event) {
		saveEntity(event);
		refreshGrid();
	}

	@SuppressWarnings("unchecked")
	private void saveEntity(ActionEvent event) {
		try {
			if (validate()) {
				getService().save((T) entity);
				initializeEntity();
				posActionSave();
			}						
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public void remove(ActionEvent event) {
		try {
			getService().remove((T) entity);
			posActionRemove();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void entitySelected() {
		System.out.println("---------> EntitySelected: [id = " + getEntity().getId() + "]");
	}

	@Override
	public void refreshGrid() {

	}

	@Override
	public Long count() {
		return getService().count(QueryFilterFactory.create(getEntitySearch()));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> find(Integer firstResult, Integer maxResult) {
		return (List<T>) getService().find(QueryFilterFactory.create(getEntitySearch()).setFirstResult(firstResult).setMaxResults(maxResult));
	}

	public EntityBean getEntity() {
		return entity;
	}

	public void setEntity(EntityBean entity) {
		this.entity = entity;
	}

	public EntityBean getEntitySearch() {
		return entitySearch;
	}

	public void setEntitySearch(EntityBean entitySearch) {
		this.entitySearch = entitySearch;
	}

	public LazyDataModel<EntityBean> getEntityList() {
		return entityList;
	}

	public void setEntityList(LazyDataModel<EntityBean> entityList) {
		this.entityList = entityList;
	}

}
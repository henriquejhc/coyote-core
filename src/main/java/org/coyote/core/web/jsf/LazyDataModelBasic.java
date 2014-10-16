package org.coyote.core.web.jsf;

import java.util.List;
import java.util.Map;

import org.coyote.core.persistence.model.EntityBean;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

/**
 * 
 * @author Jose Henrique Cardoso
 * 
 */
public class LazyDataModelBasic extends LazyDataModel<EntityBean> {

	private static final long serialVersionUID = 1L;

	private List<EntityBean> entityBeanList;

	@SuppressWarnings("rawtypes")
	private AbstractManagedBeanBase managedBean;

	@SuppressWarnings("rawtypes")
	public LazyDataModelBasic(AbstractManagedBeanBase managedBean) {
		this.managedBean = managedBean;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EntityBean> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
		try {
			
			entityBeanList = managedBean.find(first, pageSize);
			if (getRowCount() <= 0) {
				setRowCount(managedBean.count().intValue());
			}
			setPageSize(pageSize);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return entityBeanList;
	}

	// @SuppressWarnings("unchecked")
	// public List<EntityBean> load(int first, int pageSize, String sortField,
	// SortOrder sortOrder, Map<String, String> filters) {
	// try {
	//
	// entityBeanList = managedBean.find(first, pageSize);
	//
	// if (getRowCount() <= 0) {
	// setRowCount(managedBean.count().intValue());
	// }
	//
	// setPageSize(pageSize);
	//
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	//
	// return entityBeanList;
	// }

	@Override
	public void setRowIndex(int rowIndex) {
		if (rowIndex == -1 || getPageSize() == 0) {
			super.setRowIndex(-1);
		} else
			super.setRowIndex(rowIndex % getPageSize());
	}

	@Override
	public Object getRowKey(EntityBean entityBean) {
		return entityBean.getId();
	}

	@Override
	public EntityBean getRowData(String entityBeanId) {

		Long id = Long.valueOf(entityBeanId);

		for (EntityBean entityBean : entityBeanList) {
			if (id.equals(entityBean.getId())) {
				return entityBean;
			}
		}

		return null;
	}
}
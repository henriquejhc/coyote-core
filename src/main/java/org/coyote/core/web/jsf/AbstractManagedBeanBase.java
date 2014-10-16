package org.coyote.core.web.jsf;

import java.io.Serializable;
import java.util.List;

import javax.faces.event.ActionEvent;

public abstract class AbstractManagedBeanBase<T> extends AbstractManagedBean implements Serializable {

	private static final long serialVersionUID = -1L;

	public AbstractManagedBeanBase() {
		super();
	}
	
	public void save(ActionEvent event) {}	
	
	public void remove(ActionEvent event){}
	
	public void refreshGrid(){}
	
	public Long count(){
		return null;
	}
	
	public List<T> find(Integer firstResult, Integer maxResult){
		return null;
	}

}

package org.coyote.core.persistence.criteria;

import javax.persistence.metamodel.SingularAttribute;

public class CriteriaModel {

	private Object propertie;
	private Object value;

	public Object getPropertie() {
		return propertie;
	}

	public void setPropertie(Object propertie) {
		this.propertie = propertie;
	}

	public void setPropertie(SingularAttribute<?, ?> propertie) {
		this.propertie = propertie;
	}

	public void setPropertie(String propertie) {
		this.propertie = propertie;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

}
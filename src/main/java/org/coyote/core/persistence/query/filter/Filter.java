package org.coyote.core.persistence.query.filter;

import java.io.Serializable;

/**
 * 
 * @author Jose Henrique Cardoso
 *
 */
public class Filter implements Serializable, Comparable<Filter> {

	private static final long serialVersionUID = 1L;

	private String name;
	private Object value;
	private OperatorType operatorType;

	public Filter() {
		super();
	}

	public Filter(OperatorType operatorType) {
		this(null, null, operatorType);
	}

	public Filter(String name, Object value) {
		this(name, value, null);
	}

	public Filter(String name, Object value, OperatorType operatorType) {
		super();
		this.name = name;
		this.value = value;
		this.operatorType = operatorType;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Object getValue() {
		return this.value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public OperatorType getOperatorType() {
		return operatorType;
	}

	public void setOperatorType(OperatorType operatorType) {
		this.operatorType = operatorType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((operatorType == null) ? 0 : operatorType.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Filter other = (Filter) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (operatorType != other.operatorType)
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}

	@Override
	public int compareTo(Filter otherFilter) {
		return this.name.compareToIgnoreCase(otherFilter.getName());
	}

	@Override
	public String toString() {
		return "Filter [name=" + name + ", value=" + value + ", operatorType=" + operatorType + "]";
	}

}
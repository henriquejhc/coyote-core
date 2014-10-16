package org.coyote.core.persistence.query.filter.model;

import java.util.Collection;

/**
 * 
 * @author Jose Henrique Cardoso
 *
 */
public class JoinColumnBean {

	private String name;
	private String type;
	private Object value;
	private Collection<ColumnBean> columns;
	private Collection<JoinColumnBean> joinColumns;

	public JoinColumnBean() {
		super();
	}

	public JoinColumnBean(String name, String type) {
		super();
		this.name = name;
		this.type = type;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Object getValue() {
		return this.value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public Collection<ColumnBean> getColumns() {
		return this.columns;
	}

	public void setColumns(Collection<ColumnBean> columns) {
		this.columns = columns;
	}

	public Collection<JoinColumnBean> getJoinColumns() {
		return this.joinColumns;
	}

	public void setJoinColumns(Collection<JoinColumnBean> joinColumns) {
		this.joinColumns = joinColumns;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((this.name == null) ? 0 : this.name.hashCode());
		result = prime * result
				+ ((this.type == null) ? 0 : this.type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		JoinColumnBean other = (JoinColumnBean) obj;
		if (this.name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!this.name.equals(other.name)) {
			return false;
		}
		if (this.type == null) {
			if (other.type != null) {
				return false;
			}
		} else if (!this.type.equals(other.type)) {
			return false;
		}
		return true;
	}

}
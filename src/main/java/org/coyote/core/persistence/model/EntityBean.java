package org.coyote.core.persistence.model;

import java.io.Serializable;

/**
 * 
 * @author Jose Henrique Cardoso
 *
 */
public abstract class EntityBean implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final Long UNSAVED_VALUE = new Long(-1);

	protected Long id = new Long(UNSAVED_VALUE);

	public EntityBean() {
	}

	public abstract Long getId();

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof EntityBean))
			return false;
		final EntityBean other = (EntityBean) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
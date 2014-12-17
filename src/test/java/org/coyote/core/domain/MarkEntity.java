package org.coyote.core.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.coyote.core.persistence.model.EntityBean;

@Entity
@Table(name = "mark")
public class MarkEntity extends EntityBean implements Serializable {
 
	private static final long serialVersionUID = 1L;

	private String name;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public Long getId() {
		return super.id;
	}

	@Column(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}

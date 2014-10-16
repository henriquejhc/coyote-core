package org.coyote.core.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.coyote.core.persistence.model.EntityBean;

// Teste
public class PersonEntity extends EntityBean implements Serializable {
 
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public Long getId() {
		return id;
	}
	
	public PersonEntity() {
		super();
	}
}

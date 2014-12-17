package org.coyote.core.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.coyote.core.persistence.model.EntityBean;

@Entity
@Table(name = "model_car")
public class ModelEntity extends EntityBean implements Serializable {
 
	private static final long serialVersionUID = 1L;
	
	private String name;
	private Integer year;
	private MarkEntity mark;

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
	
	@Column(name = "year")
	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}
	
	@JoinColumn(name="mark_id", nullable=false, referencedColumnName="id")
	@ManyToOne(fetch=FetchType.EAGER)
	public MarkEntity getMark() {
		return mark;
	}	

	public void setMark(MarkEntity mark) {
		this.mark = mark;
	}

}
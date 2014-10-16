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
@Table(name = "car")
public class CarEntity extends EntityBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Double price;
	private ColorEntity color;
	private ModelEntity model;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name = "price")
	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@JoinColumn(name="color_id", nullable=false, referencedColumnName="id")
	@ManyToOne(fetch=FetchType.EAGER)
	public ColorEntity getColor() {
		return color;
	}

	public void setColor(ColorEntity color) {
		this.color = color;
	}

	@JoinColumn(name="model_id", nullable=false, referencedColumnName="id")
	@ManyToOne(fetch=FetchType.EAGER)
	public ModelEntity getModel() {
		return model;
	}

	public void setModel(ModelEntity model) {
		this.model = model;
	}

}
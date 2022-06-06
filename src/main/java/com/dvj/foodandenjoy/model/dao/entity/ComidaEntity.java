package com.dvj.foodandenjoy.model.dao.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
@Table(name="T_COMIDA")
public class ComidaEntity {

	@Id
	private int Comida_id;
	
	@ManyToOne
	@JoinColumn(name="Restaurante_id", nullable=false)
	private RestauranteEntity restaurante;
	
	private String nombreComida;
	
	private double precio;
	
	private String ingredientes;
	
	private String imagen;
	
}

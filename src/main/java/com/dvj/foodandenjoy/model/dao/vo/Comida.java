package com.dvj.foodandenjoy.model.dao.vo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
@Table(name="T_COMIDA")
public class Comida {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Comida_id;
	
	@JsonProperty("nombreComida")
	private String nombreComida;
	
	@JsonProperty("precio")
	private double precio;
	
	@JsonProperty("ingredientes")
	private String ingredientes;
	
	@JsonProperty("tipo")
	private String tipo;
	
	
	
}

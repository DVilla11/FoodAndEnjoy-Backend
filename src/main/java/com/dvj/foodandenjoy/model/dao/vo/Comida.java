package com.dvj.foodandenjoy.model.dao.vo;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Comida {

	private int id;
	
	private Restaurante restaurante;
	
	private String nombreComida;
	
	private double precio;
	
	private String ingredientes;
	
	private String imagen;
	
}

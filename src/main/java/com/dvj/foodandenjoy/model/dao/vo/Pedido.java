package com.dvj.foodandenjoy.model.dao.vo;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Pedido {
	
	private int id;
	
	private String fechaPedido;
	
	private Usuario usuario;
	
	private Restaurante restaurante;
	
	private Comida comida;
	
	private Repartidor repartidor;
	
	

}

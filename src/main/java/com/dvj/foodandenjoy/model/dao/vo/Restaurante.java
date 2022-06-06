package com.dvj.foodandenjoy.model.dao.vo;

import java.util.LinkedList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Restaurante {

	private int id;
	
	private String nombreRestaurante;
	
	private String nombre;
	
	private String apellido;
	
	private String direccion;
	
	private String email;
	
	private int telefono;
	
	private String imagen;
	
	private String nombreUsuario;
	
	private String contraseña;
	
	private List<Comida> comida = new LinkedList<Comida>();	
	
}

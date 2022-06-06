package com.dvj.foodandenjoy.model.dao.entity;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
@Table(name="T_RESTAURANTE")
public class RestauranteEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false)
	private String nombreRestaurante;
	
	@Column(nullable = false)
	private String nombre;
	
	@Column(nullable = false)
	private String apellido;
	
	@Column(nullable = false)
	private String direccion;
	
	@Column(nullable = false)
	private String localidad;
	
	@Column(nullable = false)
	private String email;
	
	private int telefono;
	
	private String imagen;
	
	@Column(nullable = false)
	private String nombreUsuario;
	
	@Column(nullable = false)
	private String contraseña;
	
	@Transient
	private String TokenJWT;
	
	//private List<ComidaEntity> comida = new LinkedList<ComidaEntity>();	
	
}

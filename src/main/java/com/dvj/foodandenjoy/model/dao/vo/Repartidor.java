package com.dvj.foodandenjoy.model.dao.vo;

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
@Table(name="T_REPARTIDOR")
public class Repartidor {
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private int repartidor_Id;
	
	@Column(nullable = false)
	private String nombre;
	
	@Column(nullable = false)
	private String apellido;
	
	@Column(nullable = false)
	private String direccion;
	
	@Column(nullable = false)
	private String email;
	
	
	private Long telefono;
	
	@Column(nullable = false)
	private String dni;
	
	@Column(nullable = false)
	private String localidad;
	
	@Column(nullable = false)
	private String nombreUsuario;
	
	@Column(nullable = false)
	private String contraseña;
	
	@Column(nullable = false)
	private String vehiculo;
	
	@Transient
	private String TokenJWT;


}

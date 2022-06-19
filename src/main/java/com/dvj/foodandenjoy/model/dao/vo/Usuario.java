package com.dvj.foodandenjoy.model.dao.vo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter
@Entity
@Table(name="T_USUARIO")
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false)
	private String DNI;

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
	
	@Column(nullable = false)
	private String nombreUsuario;
	
	@Column(nullable = false)
	private String contraseña;
	
	@Transient
	private String tokenJWT;
	
	@OneToMany(mappedBy = "usuario")
	@JsonProperty("pedido")
	private Set<Pedido> pedido = new HashSet<Pedido>();

}

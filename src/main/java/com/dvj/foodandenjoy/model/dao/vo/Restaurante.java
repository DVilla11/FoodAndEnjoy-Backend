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
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
@Table(name="T_RESTAURANTE")
public class Restaurante {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("Restaurante_id")
	private int Restaurante_id;
	
	@Column(nullable = true)
	@JsonProperty("nombreRestaurante")
	private String nombreRestaurante;
	
	@Column(nullable = false)
	@JsonProperty("nombre")
	private String nombre;
	
	@Column(nullable = false)
	@JsonProperty("apellido")
	private String apellido;
	
	@Column(nullable = false)
	@JsonProperty("direccion")
	private String direccion;
	
	@Column(nullable = false)
	@JsonProperty("localidad")
	private String localidad;
	
	@Column(nullable = false)
	@JsonProperty("email")
	private String email;
	
	@JsonProperty("telefono")
	private int telefono;
	
	@JsonProperty("imagen")
	private String imagen;
	
	@Lob
	@JsonProperty("imagenByte")
	private byte[] imagenByte;
	
	@Column(nullable = false)
	@JsonProperty("nombreUsuario")
	private String nombreUsuario;
	
	@Column(nullable = false)
	@JsonProperty("contraseña")
	private String contraseña;
	
	@OneToMany(targetEntity = Comida.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "r_fk", referencedColumnName = "Restaurante_id")
	@JsonProperty("comida")
	private Set<Comida> comida = new HashSet<Comida>();
	
	@Transient
	private String TokenJWT;
			
	
	
}

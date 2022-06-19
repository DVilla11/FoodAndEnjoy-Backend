package com.dvj.foodandenjoy.model.dao.dto;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.OneToMany;

import com.dvj.foodandenjoy.model.dao.vo.Comida;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter 
public class RestauranteDTO {


	@JsonProperty("Restaurante_id")
	private int Restaurante_id;

	@JsonProperty("nombreRestaurante")
	private String nombreRestaurante;
	
	@JsonProperty("nombre")
	private String nombre;

	@JsonProperty("apellido")
	private String apellido;
	
	@JsonProperty("direccion")
	private String direccion;
	
	@JsonProperty("localidad")
	private String localidad;
	
	@JsonProperty("email")
	private String email;
	
	@JsonProperty("telefono")
	private int telefono;
	
	@JsonProperty("imagen")
	private String imagen;
	
	@JsonProperty("imagenByte")
	private byte[] imagenByte;
	
	@Column(nullable = false)
	@JsonProperty("nombreUsuario")
	private String nombreUsuario;
	
	@OneToMany(mappedBy = "restaurante")
	@JsonManagedReference("comida")
	private Set<Comida> comida = new HashSet<Comida>();
	
}

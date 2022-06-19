package com.dvj.foodandenjoy.model.dao.vo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
@Table(name="T_PEDIDOs")
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("pedido_id")
	private int pedido_id;
	
	private Date fechaPedido;
	
	@ManyToOne
	@JoinColumn(name= "id")
	@JsonProperty("usuario")
	private Usuario usuario;
	
	@ManyToOne
	@JoinColumn(name= "repartidor_id")
	@JsonProperty("repartidor")
	private Repartidor repartidor;
		

	
}

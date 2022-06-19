package com.dvj.foodandenjoy.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dvj.foodandenjoy.model.dao.IPedido;
import com.dvj.foodandenjoy.model.dao.vo.Pedido;

@CrossOrigin(origins = "*")
@RestController
public class PedidoController {
	
	@Autowired
	private IPedido pedidoRepository;
	
	@RequestMapping(value = "api/pedidos", method = RequestMethod.GET)
	public ResponseEntity<?> getPedidos(){
		return ResponseEntity.ok(pedidoRepository.findAll());
	}
	
	@RequestMapping(value = "api/pedidos", method = RequestMethod.POST)
	public ResponseEntity<?> crearPedidos(@RequestBody Pedido pedido){
		
		System.out.println(pedido.getUsuario().getNombreUsuario());
		return ResponseEntity.ok(pedidoRepository.save(pedido));
		
	}

}

package com.dvj.foodandenjoy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dvj.foodandenjoy.model.dao.entity.RepartidorEntity;
import com.dvj.foodandenjoy.model.dao.imp.RepartidorEntityImp;
import com.dvj.foodandenjoy.model.dao.vo.Repartidor;

@CrossOrigin(origins = "*")
@RestController
public class RepartidorController {

	@Autowired
	private RepartidorEntityImp repartidorEntity;
	
	@RequestMapping(value = "api/repartidores", method = RequestMethod.GET)
	public List<Repartidor> getUsuarios(){
		return repartidorEntity.getListaUsuarios();
	}
	
	
	@RequestMapping(value= "api/repartidores", method = RequestMethod.POST)
	public ResponseEntity<?> crearUsuario(@RequestBody RepartidorEntity repartidor) {
		
		ResponseEntity<?> response = null;
		
		if(repartidorEntity.crear(repartidor)) {
			response = new ResponseEntity<String>("Repartidor creado correctamente", HttpStatus.CREATED);
			
		}else {			
			response = new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		
		return response;
	}
	
}

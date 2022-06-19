package com.dvj.foodandenjoy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dvj.foodandenjoy.model.dao.IRepartidor;
import com.dvj.foodandenjoy.model.dao.imp.RepartidorEntityImp;
import com.dvj.foodandenjoy.model.dao.vo.Repartidor;

@CrossOrigin(origins = "*")
@RestController
public class RepartidorController {

	@Autowired
	private RepartidorEntityImp repartidorEntity;
	
	@Autowired
	private IRepartidor repartidorRepository;
	
	@RequestMapping(value = "api/repartidores", method = RequestMethod.GET)
	public ResponseEntity<?> getUsuarios(){
		return ResponseEntity.ok(repartidorRepository.findAll());
	}
	
	
	@RequestMapping(value= "api/repartidores", method = RequestMethod.POST)
	public ResponseEntity<?> crearUsuario(@RequestBody Repartidor repartidor) {
		
		ResponseEntity<?> response = null;
		
		if(repartidorRepository.findById(repartidor.getRepartidor_Id()) != null) {
			repartidor.setContraseña(BCrypt.hashpw(repartidor.getContraseña(), BCrypt.gensalt(10)));
			repartidorRepository.save(repartidor);
			response = new ResponseEntity<String>("Repartidor creado correctamente", HttpStatus.CREATED);
			
		}else {			
			response = new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		
		return response;
	}
	
}

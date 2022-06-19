package com.dvj.foodandenjoy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dvj.foodandenjoy.model.dao.IComida;
import com.dvj.foodandenjoy.model.dao.vo.Comida;

@CrossOrigin(origins = "*")
@RestController
public class ComidaController {
	
	@Autowired
	private IComida comidaRepository;
	
	@RequestMapping(value = "api/comidas", method = RequestMethod.GET)
	public List<Comida> getUsuarios(){
		return comidaRepository.findAll();
	}
	
	
	@RequestMapping(value= "api/comidas", method = RequestMethod.POST)
	public ResponseEntity<?> crearComida(@RequestBody Comida comida) {
		
		Comida comidaGuardada = comidaRepository.save(comida);
		return ResponseEntity.ok(comidaGuardada);
	}

}

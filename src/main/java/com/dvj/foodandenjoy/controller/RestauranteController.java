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

import com.dvj.foodandenjoy.model.dao.entity.RestauranteEntity;
import com.dvj.foodandenjoy.model.dao.imp.RestauranteEntityImp;
import com.dvj.foodandenjoy.model.dao.vo.Restaurante;

@CrossOrigin(origins = "*")
@RestController
public class RestauranteController {

	@Autowired
	private RestauranteEntityImp restauranteEntity;
	
	@RequestMapping(value = "api/restaurantes", method = RequestMethod.GET)
	public List<Restaurante> getUsuarios(){
		return restauranteEntity.getListaUsuarios();
	}
	
	
	@RequestMapping(value= "api/restaurantes", method = RequestMethod.POST)
	public ResponseEntity<?> crearRestaurante(@RequestBody RestauranteEntity restaurante) {
		
		ResponseEntity<?> response = null;
		
		if(restauranteEntity.crear(restaurante)) {
			response = new ResponseEntity<String>("Restaurante creado correctamente", HttpStatus.CREATED);
			
		}else {			
			response = new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		
		return response;
	}
	
}

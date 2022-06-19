package com.dvj.foodandenjoy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dvj.foodandenjoy.model.dao.imp.RepartidorEntityImp;
import com.dvj.foodandenjoy.model.dao.imp.RestauranteEntityImp;
//import com.dvj.foodandenjoy.model.dao.imp.RestauranteEntityImp;
import com.dvj.foodandenjoy.model.dao.imp.UsuarioEntityImp;
import com.dvj.foodandenjoy.model.dao.vo.Repartidor;
import com.dvj.foodandenjoy.model.dao.vo.Restaurante;
import com.dvj.foodandenjoy.model.dao.vo.Usuario;
import com.dvj.foodandenjoy.utils.JWTUtil;

@CrossOrigin(origins = "*")
@RestController
public class AuthController {

	 @Autowired
	 private UsuarioEntityImp usuarioEntity;
	 
	 @Autowired
	 private RepartidorEntityImp repartidorEntity;
	 
	 @Autowired
	 private RestauranteEntityImp restauranteEntity;
	 
	 @Autowired
	 private JWTUtil jwtUtil;
	
	@RequestMapping(value= "api/login/usuario", method = RequestMethod.POST)
	public ResponseEntity<?> login(@RequestBody Usuario usuario) {
		

		ResponseEntity<?> response = null;
		Usuario usuarioLogueado = usuarioEntity.verificarLogin(usuario);
		
		if(usuarioLogueado != null) {
			String tokenJWT = jwtUtil.create(String.valueOf(usuarioLogueado.getId()), usuario.getNombreUsuario());
			usuarioLogueado.setTokenJWT(tokenJWT);
			response = ResponseEntity.ok()
				.body(usuarioLogueado);
			
		}else {			
			response = new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
		}
		
		
		return response;
	}
	
	@RequestMapping(value= "api/login/repartidor", method = RequestMethod.POST)
	public ResponseEntity<?> login(@RequestBody Repartidor repartidor) {
		

		ResponseEntity<?> response = null;
		Repartidor repartidorLogueado = repartidorEntity.verificarLogin(repartidor);
		
		if(repartidorLogueado != null) {
			String tokenJWT = jwtUtil.create(String.valueOf(repartidorLogueado.getRepartidor_Id()), repartidor.getNombreUsuario());
			repartidorLogueado.setTokenJWT(tokenJWT);
			response = ResponseEntity.ok()
				.body(repartidorLogueado);
			
		}else {			
			response = new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
		}
		
		
		return response;
	}
	
	
	@RequestMapping(value= "api/login/restaurante", method = RequestMethod.POST)
	public ResponseEntity<?> login(@RequestBody Restaurante restaurante) {
		

		ResponseEntity<?> response = null;
		Restaurante restauranteLogueado = restauranteEntity.verificarLogin(restaurante);
		
		if(restauranteLogueado != null) {
			String tokenJWT = jwtUtil.create(String.valueOf(restauranteLogueado.getRestaurante_id()), restaurante.getNombreUsuario());
			restauranteLogueado.setTokenJWT(tokenJWT);
			response = ResponseEntity.ok()
				.body(restauranteLogueado);
			
		}else {			
			response = new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
		}
		
		
		return response;
	}
	
}

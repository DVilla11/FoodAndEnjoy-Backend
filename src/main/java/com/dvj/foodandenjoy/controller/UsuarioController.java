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

import com.dvj.foodandenjoy.model.dao.entity.UsuarioEntity;
import com.dvj.foodandenjoy.model.dao.imp.UsuarioEntityImp;
import com.dvj.foodandenjoy.model.dao.vo.Usuario;


@CrossOrigin(origins = "*")
@RestController
public class UsuarioController {
	
	@Autowired
	private UsuarioEntityImp usuarioEntity;
	
	@RequestMapping(value = "api/usuarios", method = RequestMethod.GET)
	public List<Usuario> getUsuarios(){
		return usuarioEntity.getListaUsuarios();
	}
	
	
	@RequestMapping(value= "api/usuarios", method = RequestMethod.POST)
	public ResponseEntity<?> crearUsuario(@RequestBody UsuarioEntity usuario) {
		
		ResponseEntity<?> response = null;
		
		if(usuarioEntity.crear(usuario)) {
			response = new ResponseEntity<String>("Usuario creado correctamente", HttpStatus.CREATED);
			
		}else {			
			response = new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		
		return response;
	}
	

}
	
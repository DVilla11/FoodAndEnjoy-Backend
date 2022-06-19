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

import com.dvj.foodandenjoy.model.dao.IUsuario;
import com.dvj.foodandenjoy.model.dao.imp.UsuarioEntityImp;
import com.dvj.foodandenjoy.model.dao.vo.Usuario;


@CrossOrigin(origins = "*")
@RestController
public class UsuarioController {
	
	@Autowired
	private UsuarioEntityImp usuarioEntity;
	
	@Autowired
	private IUsuario usuarioRepository;
	
	@RequestMapping(value = "api/usuarios", method = RequestMethod.GET)
	public ResponseEntity<?> getUsuarios(){
		return ResponseEntity.ok(usuarioRepository.findAll());
	}
	
	
	@RequestMapping(value= "api/usuarios", method = RequestMethod.POST)
	public ResponseEntity<?> crearUsuario(@RequestBody Usuario usuario) {
		
		ResponseEntity<?> response = null;
		
		if(usuarioRepository.findById(usuario.getId()) != null) {
			usuario.setContraseña(BCrypt.hashpw(usuario.getContraseña(), BCrypt.gensalt(10)));
			usuarioRepository.save(usuario);
			response = new ResponseEntity<String>("Usuario creado correctamente", HttpStatus.CREATED);
			
		}else {			
			response = new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		
		return response;
	}
	

}
	
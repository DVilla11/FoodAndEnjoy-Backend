package com.dvj.foodandenjoy.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dvj.foodandenjoy.model.dao.IRestaurante;
import com.dvj.foodandenjoy.model.dao.IUsuario;
import com.dvj.foodandenjoy.model.dao.dto.RestauranteDTO;
import com.dvj.foodandenjoy.model.dao.imp.RestauranteEntityImp;
import com.dvj.foodandenjoy.model.dao.vo.Restaurante;
import com.dvj.foodandenjoy.utils.JWTUtil;
import com.fasterxml.jackson.annotation.JacksonInject.Value;

import io.jsonwebtoken.impl.crypto.JwtSignatureValidator;

@CrossOrigin(origins = "*")
@RestController
public class RestauranteController {

	@Autowired
	private RestauranteEntityImp restauranteEntity;
	
	@Autowired
	private IRestaurante restauranteRepository;
	
	@Autowired
	private IUsuario usuarioRepository;
	
	@Autowired
	private JWTUtil jwtUtil;	
	
	@RequestMapping(value = "api/restaurantes", method = RequestMethod.GET)
	public ResponseEntity<?> getUsuarios(@RequestHeader("Authorization") String token){
		
		System.out.println(jwtUtil.getKey(token.substring(7)));
		
		if(!usuarioRepository.findById(Integer.valueOf(jwtUtil.getKey(token.substring(7)))).isEmpty()) return ResponseEntity.ok(restauranteRepository.findAll());
		
		return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
	}
	
	@RequestMapping(value = "api/restaurantes/{localidad}", method = RequestMethod.GET)
	public ResponseEntity<?> getRestaurantesLocalidad(@RequestHeader("Authorization") String token ,@PathVariable("localidad") String localidad){
		
		if(token.substring(7).equals("null")) return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
		else if(!usuarioRepository.findById(Integer.valueOf(jwtUtil.getKey(token.substring(7)))).isEmpty()) return ResponseEntity.ok(restauranteEntity.buscarRestaurantesLocalidad(localidad));
		
		
		return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
	}
	
	
	@RequestMapping(value= "api/restaurantes", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<?> crearRestaurante(@RequestParam("imagen") MultipartFile imagen , @RequestParam("nombreRestaurante") String nombreRestaurante, @RequestParam("nombre") String nombre, @RequestParam("apellido") String apellido,
												@RequestParam("direccion") String direccion, @RequestParam("numero") int numero, @RequestParam("telefono") int telefono, @RequestParam("localidad") String localidad, @RequestParam("email") String email,
												@RequestParam("nombreUsuario") String nombreUsuario, @RequestParam("contraseña") String contraseña) {
		
		Restaurante restaurante = new Restaurante();
		restaurante.setNombre(nombre);
		restaurante.setApellido(apellido);
		restaurante.setNombreRestaurante(nombreRestaurante);
		restaurante.setNombreUsuario(nombreUsuario);
		restaurante.setContraseña(contraseña);
		restaurante.setDireccion(direccion);
		restaurante.setTelefono(telefono);
		restaurante.setLocalidad(localidad);
		restaurante.setEmail(email);
		
		
		if(!imagen.isEmpty()) {
			try {
				byte[] bytes = imagen.getBytes();
				restaurante.setImagenByte(bytes);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
		ResponseEntity<?> response = null;
		if(restauranteRepository.findById(restaurante.getTelefono()) != null) {
			restaurante.setContraseña(BCrypt.hashpw(restaurante.getContraseña(), BCrypt.gensalt(10)));
			restauranteRepository.save(restaurante);
			response = new ResponseEntity<String>("Restaurante creado correctamente", HttpStatus.CREATED);
		} else response = new ResponseEntity<Void>(HttpStatus.CONFLICT);
		
		return response;
	}
	
	@RequestMapping(value= "api/restaurantes/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> editarRestaurante(@RequestHeader("Authorization") String token, @PathVariable("id") int id, @RequestBody Restaurante restaurante) {
		
		ResponseEntity<?> response = null;
		if(!restauranteRepository.findById(Integer.valueOf(jwtUtil.getKey(token.substring(7)))).isEmpty()) {
			
			Optional<Restaurante> restauranteBD = restauranteRepository.findById(id);
			
			Restaurante restauranteBaseDatos = restauranteBD.get();
			restauranteBaseDatos.setNombre(restaurante.getNombre());
			restauranteBaseDatos.setApellido(restaurante.getApellido());
			restauranteBaseDatos.setDireccion(restaurante.getDireccion());
			//restauranteBaseDatos.setNumero(null);
			restauranteBaseDatos.setTelefono(restaurante.getTelefono());
			restauranteBaseDatos.setComida(restaurante.getComida());
			restauranteRepository.save(restauranteBaseDatos);
			restauranteEntity.borrarPlatos();
			
			RestauranteDTO restauranteDTO = new RestauranteDTO();
			restauranteDTO.setRestaurante_id(restauranteBaseDatos.getRestaurante_id());
			restauranteDTO.setImagenByte(restauranteBaseDatos.getImagenByte());
			restauranteDTO.setNombre(restaurante.getNombre());
			restauranteDTO.setApellido(restaurante.getApellido());
			restauranteDTO.setDireccion(restaurante.getDireccion());
			restauranteDTO.setEmail(restauranteBaseDatos.getEmail());
			restauranteDTO.setLocalidad(restauranteBaseDatos.getLocalidad());
			restauranteDTO.setNombreUsuario(restauranteBaseDatos.getNombreUsuario());
			restauranteDTO.setNombreRestaurante(restauranteBaseDatos.getNombreRestaurante());
			restauranteDTO.setTelefono(restaurante.getTelefono());
			restauranteDTO.setComida(restaurante.getComida());
			restauranteDTO.setImagenByte(restauranteBaseDatos.getImagenByte());
			
			response = new ResponseEntity<RestauranteDTO>(restauranteDTO, HttpStatus.OK);
			
		} else response = new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
		
		return response;
	}
	
}

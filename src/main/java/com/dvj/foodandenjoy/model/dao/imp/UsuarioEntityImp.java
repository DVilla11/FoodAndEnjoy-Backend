package com.dvj.foodandenjoy.model.dao.imp;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dvj.foodandenjoy.model.dao.IUsuario;
import com.dvj.foodandenjoy.model.dao.vo.Usuario;

@Repository
@Transactional
public class UsuarioEntityImp{

	@PersistenceContext
	@Autowired
	EntityManager entityManager;

	
	@SuppressWarnings("unchecked")
	public Usuario verificarLogin(Usuario usuario) {
		String query = "FROM Usuario WHERE nombre_usuario = :nombre_usuario";
		List<Usuario> lista = entityManager.createQuery(query)
								.setParameter("nombre_usuario", usuario.getNombreUsuario())
								.getResultList();
		
		if(lista.isEmpty()) return null;
		
		String constraseñaHashed = lista.get(0).getContraseña();
		
		if(BCrypt.checkpw(usuario.getContraseña(), constraseñaHashed)) {
			return lista.get(0);
		}
		
		return null;
	}
	
	

	
}

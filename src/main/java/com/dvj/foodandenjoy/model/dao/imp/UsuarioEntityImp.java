package com.dvj.foodandenjoy.model.dao.imp;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dvj.foodandenjoy.model.dao.IUsuario;
import com.dvj.foodandenjoy.model.dao.entity.UsuarioEntity;
import com.dvj.foodandenjoy.model.dao.vo.Usuario;

@Repository
@Transactional
public class UsuarioEntityImp implements IUsuario {

	@PersistenceContext
	@Autowired
	EntityManager entityManager;

	@Override
	public boolean crear(UsuarioEntity usuario) {
		if(entityManager.find(UsuarioEntity.class, usuario.getDNI()) == null) {
			usuario.setContraseña(BCrypt.hashpw(usuario.getContraseña(), BCrypt.gensalt(10)));
			entityManager.merge(usuario);
			return true;
			}
			
			return false;

	}

	@Override
	public void borrar(int idUsuario) {
		Usuario usuario = entityManager.find(Usuario.class, idUsuario);
		entityManager.remove(usuario);
	}

	@Override
	public void actualizar(Usuario usuario) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Usuario getUsuarioPorID(int idUsuario) {
		
		return null;
	}

	@Override
	@Transactional
	public List<Usuario> getListaUsuarios() {
		String query = "FROM UsuarioEntity";
		
		List<Usuario> lista = entityManager.createQuery(query).getResultList();
		
		return lista;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public UsuarioEntity verificarLogin(UsuarioEntity usuario) {
		String query = "FROM UsuarioEntity WHERE nombre_usuario = :nombre_usuario";
		List<UsuarioEntity> lista = entityManager.createQuery(query)
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

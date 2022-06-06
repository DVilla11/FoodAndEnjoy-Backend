package com.dvj.foodandenjoy.model.dao.imp;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dvj.foodandenjoy.model.dao.IRestaurante;
import com.dvj.foodandenjoy.model.dao.entity.RepartidorEntity;
import com.dvj.foodandenjoy.model.dao.entity.RestauranteEntity;
import com.dvj.foodandenjoy.model.dao.vo.Restaurante;

@Repository
@Transactional
public class RestauranteEntityImp implements IRestaurante {

	@PersistenceContext
	EntityManager entityManager;
	
	@Override
	public boolean crear(RestauranteEntity restaurante) {
		if(entityManager.find(RepartidorEntity.class, String.valueOf(restaurante.getId())) == null) {
			restaurante.setContraseña(BCrypt.hashpw(restaurante.getContraseña(), BCrypt.gensalt(10)));
			entityManager.merge(restaurante);
			return true;
			}
			
			return false;
	}

	@Override
	public void borrar(int idRestaurante) {
		Restaurante restaurante = entityManager.find(Restaurante.class, idRestaurante);
		entityManager.remove(restaurante);
	}

	@Override
	public void actualizar(RestauranteEntity restaurante) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Restaurante getUsuarioPorID(int idRestaurante) {
		return null;
	}

	@Override
	public List<Restaurante> getListaUsuarios() {
		String query = "FROM RestauranteEntity";
		return entityManager.createQuery(query).getResultList();
	}

	@Override
	public RestauranteEntity verificarLogin(RestauranteEntity restaurante) {
		String query = "FROM RestauranteEntity WHERE nombre_usuario = :nombre_usuario";
		List<RestauranteEntity> lista = entityManager.createQuery(query)
								.setParameter("nombre_usuario", restaurante.getNombreUsuario())
								.getResultList();
		
		if(lista.isEmpty()) return null;
		
		String constraseñaHashed = lista.get(0).getContraseña();
		
		if(BCrypt.checkpw(restaurante.getContraseña(), constraseñaHashed)) {
			return lista.get(0);
		}
		
		return null;
	}

}

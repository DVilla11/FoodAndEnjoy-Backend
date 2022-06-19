package com.dvj.foodandenjoy.model.dao.imp;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dvj.foodandenjoy.model.dao.IRestaurante;
import com.dvj.foodandenjoy.model.dao.vo.Restaurante;

@Repository
@Transactional
public class RestauranteEntityImp{

	@PersistenceContext
	EntityManager entityManager;
	
	public Restaurante verificarLogin(Restaurante restaurante) {
		String query = "FROM Restaurante WHERE nombre_usuario = :nombre_usuario";
		List<Restaurante> lista = entityManager.createQuery(query)
								.setParameter("nombre_usuario", restaurante.getNombreUsuario())
								.getResultList();
		
		if(lista.isEmpty()) return null;
		
		String constraseñaHashed = lista.get(0).getContraseña();
		
		if(BCrypt.checkpw(restaurante.getContraseña(), constraseñaHashed)) {
			return lista.get(0);
		}
		
		return null;
	}
	
	public List<Restaurante> buscarRestaurantesLocalidad(String localidad) {
		String query = "FROM Restaurante WHERE localidad = :localidad";
		List<Restaurante> lista = entityManager.createQuery(query)
											   .setParameter("localidad", localidad)
											   .getResultList();
		
		return lista;
	}
	
	public void borrarPlatos() {
		String query = "DELETE FROM Comida WHERE r_fk = NULL";
		entityManager.createQuery(query).executeUpdate();
		

	}

}

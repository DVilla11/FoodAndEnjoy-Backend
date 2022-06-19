package com.dvj.foodandenjoy.model.dao.imp;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dvj.foodandenjoy.model.dao.vo.Repartidor;


@Repository
@Transactional
public class RepartidorEntityImp {

	@PersistenceContext
	EntityManager entityManager;
	
	
	public Repartidor verificarLogin(Repartidor repartidor) {
		String query = "FROM Repartidor WHERE nombre_usuario = :nombre_usuario";
		List<Repartidor> lista = entityManager.createQuery(query)
								.setParameter("nombre_usuario", repartidor.getNombreUsuario())
								.getResultList();
		
		if(lista.isEmpty()) return null;
		
		String constraseñaHashed = lista.get(0).getContraseña();
		
		if(BCrypt.checkpw(repartidor.getContraseña(), constraseñaHashed)) {
			return lista.get(0);
		}
		
		return null;
	}

}

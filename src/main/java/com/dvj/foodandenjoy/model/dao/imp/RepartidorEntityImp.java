package com.dvj.foodandenjoy.model.dao.imp;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dvj.foodandenjoy.model.dao.IRepartidor;
import com.dvj.foodandenjoy.model.dao.entity.RepartidorEntity;
import com.dvj.foodandenjoy.model.dao.vo.Repartidor;


@Repository
@Transactional
public class RepartidorEntityImp implements IRepartidor {

	@PersistenceContext
	EntityManager entityManager;
	
	@Override
	public boolean crear(RepartidorEntity repartidor) {
		if(entityManager.find(RepartidorEntity.class, repartidor.getDni()) == null) {
			repartidor.setContraseña(BCrypt.hashpw(repartidor.getContraseña(), BCrypt.gensalt(10)));
			entityManager.merge(repartidor);
			return true;
			}
			
			return false;
	}

	@Override
	public void borrar(int idRepartidor) {
		Repartidor repartidor = entityManager.find(Repartidor.class, idRepartidor);
		entityManager.remove(repartidor);
		
	}

	@Override
	public void actualizar(RepartidorEntity repartidor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Repartidor getUsuarioPorID(int idRepartidor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public List<Repartidor> getListaUsuarios() {
		String query = "FROM RepartidorEntity";
		return entityManager.createQuery(query).getResultList();
	}

	@Override
	public RepartidorEntity verificarLogin(RepartidorEntity repartidor) {
		String query = "FROM RepartidorEntity WHERE nombre_usuario = :nombre_usuario";
		List<RepartidorEntity> lista = entityManager.createQuery(query)
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

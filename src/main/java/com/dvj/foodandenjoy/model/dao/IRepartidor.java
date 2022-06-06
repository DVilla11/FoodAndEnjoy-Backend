package com.dvj.foodandenjoy.model.dao;

import java.util.List;

import com.dvj.foodandenjoy.model.dao.entity.RepartidorEntity;
import com.dvj.foodandenjoy.model.dao.vo.Repartidor;

public interface IRepartidor {

	public boolean crear(RepartidorEntity repartidor);
	public void borrar(int idRepartidor);
	public void actualizar(RepartidorEntity repartidor);
	public Repartidor getUsuarioPorID(int idRepartidor);
	public List<Repartidor> getListaUsuarios();
	public RepartidorEntity verificarLogin(RepartidorEntity repartidor);
	
}

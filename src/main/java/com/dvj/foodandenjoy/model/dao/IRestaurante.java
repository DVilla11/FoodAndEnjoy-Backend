package com.dvj.foodandenjoy.model.dao;

import java.util.List;

import com.dvj.foodandenjoy.model.dao.entity.RestauranteEntity;
import com.dvj.foodandenjoy.model.dao.vo.Restaurante;

public interface IRestaurante {
	
	public boolean crear(RestauranteEntity restaurante);
	public void borrar(int idRestaurante);
	public void actualizar(RestauranteEntity restaurante);
	public Restaurante getUsuarioPorID(int idRestaurante);
	public List<Restaurante> getListaUsuarios();
	public RestauranteEntity verificarLogin(RestauranteEntity restaurante);

}

package com.dvj.foodandenjoy.model.dao;

import java.util.List;

import com.dvj.foodandenjoy.model.dao.entity.UsuarioEntity;
import com.dvj.foodandenjoy.model.dao.vo.Usuario;

public interface IUsuario {

	public boolean crear(UsuarioEntity usuario);
	public void borrar(int idUsuario);
	public void actualizar(Usuario usuario);
	public Usuario getUsuarioPorID(int idUsuario);
	public List<Usuario> getListaUsuarios();
	public UsuarioEntity verificarLogin(UsuarioEntity usuario);
	
}

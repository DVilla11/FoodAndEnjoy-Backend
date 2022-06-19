package com.dvj.foodandenjoy.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dvj.foodandenjoy.model.dao.vo.Usuario;

public interface IUsuario extends JpaRepository<Usuario, Integer>{

	
}

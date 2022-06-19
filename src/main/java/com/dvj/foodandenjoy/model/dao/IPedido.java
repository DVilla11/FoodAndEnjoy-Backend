package com.dvj.foodandenjoy.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dvj.foodandenjoy.model.dao.vo.Pedido;

public interface IPedido extends JpaRepository<Pedido, Integer> {

}

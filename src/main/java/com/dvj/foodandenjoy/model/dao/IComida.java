package com.dvj.foodandenjoy.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dvj.foodandenjoy.model.dao.vo.Comida;


public interface IComida extends JpaRepository<Comida, Integer> {

}

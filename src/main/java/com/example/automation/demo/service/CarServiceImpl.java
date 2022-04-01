package com.example.automation.demo.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.example.automation.demo.model.Car;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private JdbcTemplate jtm;

    @Override
    public List<Car> findAll() {

        String sql = "SELECT * FROM CARS";

        List<Car> cars = jtm.query(sql, new BeanPropertyRowMapper<Car>(Car.class));

        return cars;
    }
}

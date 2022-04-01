package com.example.automation.demo.controller;

import com.example.automation.demo.model.Car;
import com.example.automation.demo.service.CarService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MyController {

    @Autowired
    private CarService carService;
    
    @RequestMapping("/")
    public String index(Model model) {
        
        return "index";
    }    

    @RequestMapping("/showCars")
    public ModelAndView showCars() {

        List<Car> cars = carService.findAll();

        Map<String, Object> params = new HashMap<>();
        params.put("cars", cars);

        return new ModelAndView("showCars", params);
    }
}

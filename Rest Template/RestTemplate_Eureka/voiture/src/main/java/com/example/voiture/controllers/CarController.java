package com.example.voiture.controllers;

import com.example.voiture.models.CarResponse;
import com.example.voiture.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/car")
public class CarController {

    @Autowired
    private CarService carService;

    @GetMapping
    public List<CarResponse> getAllCars() {
        return carService.findAll();
    }

    @GetMapping("/{id}")
    public CarResponse getCarById(@PathVariable Long id) {
        return carService.findById(id);
    }
}

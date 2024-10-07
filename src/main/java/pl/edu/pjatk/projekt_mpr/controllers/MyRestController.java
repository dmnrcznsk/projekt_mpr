package pl.edu.pjatk.projekt_mpr.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.edu.pjatk.projekt_mpr.model.Car;
import pl.edu.pjatk.projekt_mpr.services.CarService;

import java.util.List;

@RestController
public class MyRestController {
    private CarService carService;

    @Autowired
    public MyRestController(CarService carService) {
        this.carService = carService;
    }
    @GetMapping("car/all")
    public List<Car> getAll() {
        return this.carService.getCarList();
    }
    @GetMapping("car/{id}")
    public Car getCar(@PathVariable int id) {
        return this.carService.findCarById(id);
    }

    @PostMapping("car")
    public void createCar(@RequestBody Car car) {
        this.carService.createCar(car);
    }
}

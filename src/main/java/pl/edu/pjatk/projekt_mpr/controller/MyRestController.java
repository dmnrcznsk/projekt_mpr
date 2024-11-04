package pl.edu.pjatk.projekt_mpr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.edu.pjatk.projekt_mpr.model.Car;
import pl.edu.pjatk.projekt_mpr.service.CarService;

import java.util.List;
import java.util.Optional;

@RestController
public class MyRestController {
    private CarService carService;

    @Autowired
    public MyRestController(CarService carService) {
        this.carService = carService;
    }
    @GetMapping("car/all")
    public List<Car> getAll() {
        return this.carService.getAllCars();
    }


    @GetMapping("car/all/raw")
    public List<Car> getAllRaw() {
        return this.carService.getRawOutputOfAllCars();
    }

    @GetMapping("car/{id}")
    public Optional<Car> get(@PathVariable Long id) {
        return this.carService.getById(id);
    }

    @GetMapping("car/make/{make}")
    public List<Car> getByMake(@PathVariable String make) {
        return this.carService.getCarByMake(make);
    }

    @GetMapping("car/color/{color}")
    public List<Car> getByColor(@PathVariable String color) {
        return this.carService.getCarByColor(color);
    }

    @PostMapping("car/add")
    public void createCar(@RequestBody Car car) {
        car.setIdentificator(car.calculateIdentificator());
        this.carService.createCar(car);
    }

    @DeleteMapping("car/delete/{id}")
    public void deleteCar(@PathVariable Long id) {
        this.carService.deleteCar(id);
    }

    @PatchMapping("car/update/{id}")
    public void updateCar(@PathVariable Long id, @RequestBody Car car) {
        this.carService.updateCar(id, car);
    }
}


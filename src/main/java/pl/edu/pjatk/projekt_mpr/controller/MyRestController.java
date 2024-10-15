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
    @GetMapping("car/{id}")
    public Optional<Car> get(@PathVariable Long id) {
        return this.carService.get(id);
    }

    @GetMapping("car/make/{make}")
    public List<Car> getByMake(@PathVariable String make) {
        return this.carService.getCarByMake(make);
    }

    @PostMapping("car")
    public void createCar(@RequestBody Car car) {
        this.carService.createCar(car);
    }

    @DeleteMapping("car/{id}")
    public void deleteCar(@PathVariable int id) {
        this.carService.deleteCar(id);
    }

    @PutMapping("car/{id}")
    public void updateCar(@PathVariable int id, @RequestBody Car car) {
        this.carService.updateCar(id, car);
    }
}


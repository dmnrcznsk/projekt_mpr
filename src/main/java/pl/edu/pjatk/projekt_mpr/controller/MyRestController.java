package pl.edu.pjatk.projekt_mpr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<Car>> getAll() {
        return new ResponseEntity<>(this.carService.getAllCars(), HttpStatus.OK);
    }

    @GetMapping("car/all/raw")
    public ResponseEntity<List<Car>> getAllRaw() {
        return new ResponseEntity<>(this.carService.getRawOutputOfAllCars(), HttpStatus.OK);
    }

    @GetMapping("car/{id}")
    public ResponseEntity<Car> get(@PathVariable Long id) {
        return new ResponseEntity<>(this.carService.getById(id), HttpStatus.OK);
    }

    @GetMapping("car/make/{make}")
    public ResponseEntity<List<Car>> getByMake(@PathVariable String make) {
        return new ResponseEntity<>(this.carService.getCarByMake(make), HttpStatus.OK);
    }

    @GetMapping("car/color/{color}")
    public ResponseEntity<List<Car>> getByColor(@PathVariable String color) {
        return new ResponseEntity<>(this.carService.getCarByColor(color), HttpStatus.OK);
    }

    @GetMapping("car/identifier")
    private ResponseEntity<Car> getByIdentificator(@PathVariable int identifier) {
        return new ResponseEntity<>(this.carService.getByIdentificator(identifier), HttpStatus.OK);
    }

    @GetMapping("car/pdf/{id}")
    private ResponseEntity<Car> getPdf(@PathVariable Long id) {
        this.carService.generatePDF(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("car/add")
    public ResponseEntity<Car> createCar(@RequestBody Car car) {
        car.setIdentificator(car.calculateIdentificator());
        this.carService.createCar(car);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("car/delete/{id}")
    public ResponseEntity<Car> deleteCar(@PathVariable Long id) {
        this.carService.deleteCar(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("car/update/{id}")
    public ResponseEntity<Car> updateCar(@PathVariable Long id, @RequestBody Car car) {
        this.carService.updateCar(id, car);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}


package pl.edu.pjatk.projekt_mpr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.pjatk.projekt_mpr.model.Car;
import pl.edu.pjatk.projekt_mpr.repository.CarRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class CarService {
    private CarRepository carRepository;
    List<Car> carList = new ArrayList<>();

    @Autowired
    public CarService(CarRepository repository) {
        this.carRepository = repository;
        this.carRepository.save(new Car("BMW", "black"));
        this.carRepository.save(new Car("Audi", "red"));
        this.carRepository.save(new Car("Mercedes", "white"));
        this.carRepository.save(new Car("BMW", "yellow"));
    }

    public List<Car> getCarByMake(String make) {
        return this.carRepository.findByMake(make);
    }

    public List<Car> getAllCars() {
        return (List<Car>) this.carRepository.findAll();
    }

    public List<Car> getCarList() {
        return carList;
    }

    public void createCar(Car car) {
        this.carList.add(car);
    }

    public void deleteCar(int id) {
        if(id >= 0 && id < carList.size()) {
            this.carList.remove(id);
        }
    }

    public Optional<Car> get(Long id) {
        return this.carRepository.findById(id);
    }

    public void updateCar(int id, Car car) {
        this.carList.set(id, car);
    }
}

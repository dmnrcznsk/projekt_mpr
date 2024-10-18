package pl.edu.pjatk.projekt_mpr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pjatk.projekt_mpr.model.Car;
import pl.edu.pjatk.projekt_mpr.repository.CarRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {
    private CarRepository carRepository;

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

    public List<Car> getCarByColor(String color) {
        return this.carRepository.findByColor(color);
    }

    public List<Car> getAllCars() {
        return (List<Car>) this.carRepository.findAll();
    }

    public void createCar(Car car) {
        this.carRepository.save(car);
    }

    public void deleteCar(Long id) {
        this.carRepository.deleteById(id);
    }

    public Optional<Car> getById(Long id) {
        return this.carRepository.findById(id);
    }

    public void updateCar(Long id, Car car) {
        Optional<Car> existingCar = this.carRepository.findById(id);

        if(existingCar.isPresent()) {
            Car updatedCar = existingCar.get();

            if (car.getMake() != null) {
                updatedCar.setMake(car.getMake());
            }

            if (car.getColor() != null) {
                updatedCar.setColor(car.getColor());
            }

            this.carRepository.save(updatedCar);

        } else {
            throw new RuntimeException("Car with ID " + id + " not found.");
        }
    }
}

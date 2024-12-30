package pl.edu.pjatk.projekt_mpr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pjatk.projekt_mpr.exception.CarAlreadyExistsException;
import pl.edu.pjatk.projekt_mpr.exception.CarNotFoundException;
import pl.edu.pjatk.projekt_mpr.model.Car;
import pl.edu.pjatk.projekt_mpr.repository.CarRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {
    private CarRepository carRepository;
    private StringUtilsService sus = new StringUtilsService();

    @Autowired
    public CarService(CarRepository repository, StringUtilsService sus) {
        this.sus = sus;
        this.carRepository = repository;
        this.carRepository.save(new Car("BMW", "black"));
        this.carRepository.save(new Car("Audi", "red"));
        this.carRepository.save(new Car("Mercedes", "white"));
        this.carRepository.save(new Car("BMW", "yellow"));
    }

    public List<Car> getCarByMake(String make) {
        List<Car> cars = carRepository.findByMake(make);

        if(cars.isEmpty()) { throw new CarNotFoundException("Cars with make " + make + " not found"); }

        cars.forEach(car ->{
          car.setMake(sus.toLowerCaseButCapitalizeFirstLetter(car.getMake()));
          car.setColor(sus.toLowerCaseButCapitalizeFirstLetter(car.getColor()));
        });
        return cars;
    }

    public List<Car> getCarByColor(String color) {
        List<Car> cars = carRepository.findByColor(color);

        if(cars.isEmpty()) { throw new CarNotFoundException("Cars with color " + color + " not found"); }

        cars.forEach(car ->{
            car.setMake(sus.toLowerCaseButCapitalizeFirstLetter(car.getMake()));
            car.setColor(sus.toLowerCaseButCapitalizeFirstLetter(car.getColor()));
        });
        return cars;
    }

    public List<Car> getRawOutputOfAllCars() {
        return (List<Car>) this.carRepository.findAll();
    }

    public List<Car> getAllCars() {
        List<Car> cars = (List<Car>) this.carRepository.findAll();
        cars.forEach(car ->{
            car.setMake(sus.toLowerCaseButCapitalizeFirstLetter(car.getMake()));
            car.setColor(sus.toLowerCaseButCapitalizeFirstLetter(car.getColor()));
        });
        return cars;
    }

    public void createCar(Car car) {
        car.setMake(sus.toUpperCase(car.getMake()));
        car.setColor(sus.toUpperCase(car.getColor()));

        Optional<Car> findCar = this.carRepository.findByIdentificator(car.getIdentificator());

        if (findCar.isPresent()) {
            throw new CarAlreadyExistsException();
        }

        this.carRepository.save(car);
    }

    public void deleteCar(Long id) {
        Optional<Car> car = this.carRepository.findById(id);

        if(car.isEmpty()) {
            throw new CarNotFoundException("Car with id " + id + " not found");
        }

        this.carRepository.deleteById(id);
    }

    public Car getById(Long id) {
        Optional<Car> car = this.carRepository.findById(id);

        if(car.isEmpty()) {
            throw new CarNotFoundException("Car with id " + id + " not found");
        }

        return car.get();
    }

    public Car getByIdentificator(int identificator) {
        Optional<Car> car = this.carRepository.findByIdentificator(identificator);

        if(car.isEmpty()) {
            throw new CarNotFoundException("Car with identificator " + identificator + " not found");
        }

        return car.get();
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
            throw new CarNotFoundException();
        }
    }
}

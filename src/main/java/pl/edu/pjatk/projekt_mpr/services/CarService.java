package pl.edu.pjatk.projekt_mpr.services;

import org.springframework.stereotype.Component;
import pl.edu.pjatk.projekt_mpr.model.Car;

import java.util.ArrayList;
import java.util.List;

@Component
public class CarService {
    List<Car> carList = new ArrayList<>();

    public CarService() {
        carList.add(new Car("BMW", "black"));
        carList.add(new Car("Audi", "red"));
        carList.add(new Car("Mercedes", "white"));
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

    public Car findCarById(int id) {
        if(id >= 0 && id < carList.size()) {
            return carList.get(id);
        } else {
            return null;
        }
    }
}

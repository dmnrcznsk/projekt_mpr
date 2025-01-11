package pl.edu.pjatk.projekt_mpr.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.edu.pjatk.projekt_mpr.model.Car;

import java.util.List;
import java.util.Objects;

@Service
public class CarService {
    private final RestTemplate restTemplate;
    private final String apiUrl = "http://localhost:8081/car";

    public CarService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Car> getAllCars() {
        try {
            return List.of(Objects.requireNonNull(restTemplate.getForObject("http://localhost:8081/car/all", Car[].class)));
        } catch (Exception e) {
            System.out.println("Błąd podczas pobierania samochodów: " + e.getMessage());
            throw e;
        }
    }

    public Car getCarById(Long id) {
        try {
            return restTemplate.getForObject("http://localhost:8081/car/" + id, Car.class);
        } catch (Exception e) {
            throw new RuntimeException("Błąd podczas pobierania samochodu o ID: " + id, e);
        }
    }

    public void addCar(Car car) {
        restTemplate.postForObject(apiUrl + "/add", car, Car.class);
    }

    public void updateCar(Long id, Car car) {
        restTemplate.postForObject(apiUrl + "/update/" + id, car, Void.class);
    }

    public void deleteCar(Long id) {
        restTemplate.postForObject(apiUrl + "/delete/" + id, null, Void.class);
    }
}

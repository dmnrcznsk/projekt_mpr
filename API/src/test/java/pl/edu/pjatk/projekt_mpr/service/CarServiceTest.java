package pl.edu.pjatk.projekt_mpr.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pl.edu.pjatk.projekt_mpr.model.Car;
import pl.edu.pjatk.projekt_mpr.repository.CarRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CarServiceTest {
    private CarService carService;
    private StringUtilsService sus;
    private CarRepository carRepo;

    @BeforeEach
    public void setup() {
        this.sus = Mockito.mock(StringUtilsService.class);
        this.carRepo = Mockito.mock(CarRepository.class);
        this.carService = new CarService(carRepo, sus);
        clearInvocations(carRepo);
    }

    @Test
    public void createSetsCarToUpperCase() {
        Car car = new Car("Audi", "blue");

        this.carService.createCar(car);

        verify(sus, times(2)).toUpperCase(any());
        verify(carRepo, times(1)).save(any());
    }

    @Test
    public void getSetsCarToLowerCaseAndCapitalizedFirstLetter() {
        Car car = new Car("AUDI", "BLUE");
        Car car1 = new Car("BMW", "RED");
        List<Car> cars = Arrays.asList(car, car1);

        when(carRepo.findAll()).thenReturn(cars);
        when(sus.toLowerCaseButCapitalizeFirstLetter(anyString())).thenReturn("Audi").thenReturn("Blue").thenReturn("Bmw").thenReturn("Red");

        List<Car> result = this.carService.getAllCars();

        verify(sus, times(4)).toLowerCaseButCapitalizeFirstLetter(any());
        verify(carRepo, times(1)).findAll();
        assertEquals("Audi", cars.get(0).getMake());
        assertEquals("Blue", cars.get(0).getColor());
        assertEquals("Bmw", cars.get(1).getMake());
        assertEquals("Red", cars.get(1).getColor());
    }

    @Test
    public void getSetsCarByMakeToUpperCaseAndCapitalizedFirstLetter() {
        Car car = new Car("AUDI", "BLUE");
        Car car1 = new Car("BMW", "RED");
        List<Car> cars = Arrays.asList(car, car1);

        when(carRepo.findByMake("Audi")).thenReturn(cars.stream().filter(c -> {
            return c.getMake().equalsIgnoreCase("Audi");
        }).toList());

        when(sus.toLowerCaseButCapitalizeFirstLetter(anyString())).thenReturn("Audi").thenReturn("Blue");

        List<Car> result = this.carService.getCarByMake("Audi");

        verify(carRepo, times(1)).findByMake("Audi");
        verify(sus, times(2)).toLowerCaseButCapitalizeFirstLetter(any());
        assertEquals("Audi", result.get(0).getMake());
        assertEquals("Blue", result.get(0).getColor());
        assertEquals(1, result.size());
    }

    @Test
    public void getSetsCarByColorToUpperCaseAndCapitalizedFirstLetter() {
        Car car = new Car("AUDI", "BLUE");
        Car car1 = new Car("BMW", "RED");
        List<Car> cars = Arrays.asList(car, car1);

        when(carRepo.findByColor("Blue")).thenReturn(cars.stream().filter(c -> {
            return c.getColor().equalsIgnoreCase("Blue");
        }).toList());

        when(sus.toLowerCaseButCapitalizeFirstLetter(anyString())).thenReturn("Audi").thenReturn("Blue");

        List<Car> result = this.carService.getCarByColor("Blue");

        verify(carRepo, times(1)).findByColor("Blue");
        verify(sus, times(2)).toLowerCaseButCapitalizeFirstLetter(any());
        assertEquals("Audi", result.get(0).getMake());
        assertEquals("Blue", result.get(0).getColor());
        assertEquals(1, result.size());
    }

    @Test
    public void getCarByIdToUpperCaseAndCapitalizedFirstLetter() {
        Car car = new Car("AUDI", "BLUE");
        car.setId(1L);

        when(carRepo.findById(1L)).thenReturn(Optional.of(car));
        when(sus.toLowerCaseButCapitalizeFirstLetter(anyString())).thenReturn("Audi").thenReturn("Blue");

        Optional<Car> result = carService.getById(1L);

        verify(carRepo, times(1)).findById(1L);
        verify(sus, times(2)).toLowerCaseButCapitalizeFirstLetter(any());
        assertEquals("Audi", car.getMake());
        assertEquals("Blue", car.getColor());
        assertEquals(1L, car.getId());
    }
}

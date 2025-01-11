package pl.edu.pjatk.projekt_mpr.service;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pjatk.projekt_mpr.exception.CarAlreadyExistsException;
import pl.edu.pjatk.projekt_mpr.exception.CarNotFoundException;
import pl.edu.pjatk.projekt_mpr.model.Car;
import pl.edu.pjatk.projekt_mpr.repository.CarRepository;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
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

        if(cars.isEmpty()) { throw new CarNotFoundException("Cars not found"); }

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

            updatedCar.setIdentificator(updatedCar.calculateIdentificator());

            this.carRepository.save(updatedCar);

        } else {
            throw new CarNotFoundException();
        }
    }

    public void generatePDF(Long id) {
        Car car = getById(id);
        try (PDDocument document = new PDDocument(); ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            PDPage page = new PDPage();
            document.addPage(page);

            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 12);
                contentStream.beginText();
                contentStream.setLeading(14.5f);
                contentStream.newLineAtOffset(50, 750);

                contentStream.showText("Wynik zapytania:");
                contentStream.newLine();
                contentStream.showText("ID: " + car.getId());
                contentStream.newLine();
                contentStream.showText("Make: " + car.getMake());
                contentStream.newLine();
                contentStream.showText("Color: " + car.getColor());
                contentStream.newLine();
                contentStream.showText("Identificator: " + car.getIdentificator());
                contentStream.endText();
            }

            document.save("result.pdf");
        } catch (IOException e) {
            throw new RuntimeException("Błąd podczas generowania PDF: " + e.getMessage(), e);
        }
    }
}
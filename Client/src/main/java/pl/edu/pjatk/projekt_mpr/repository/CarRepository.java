package pl.edu.pjatk.projekt_mpr.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pjatk.projekt_mpr.model.Car;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarRepository extends CrudRepository<Car, Long> {
     List<Car> findByMake(String make);
     List<Car> findByColor(String color);
     Optional<Car> findByIdentificator(int identificator);
}

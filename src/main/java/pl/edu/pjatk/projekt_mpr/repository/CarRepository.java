package pl.edu.pjatk.projekt_mpr.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pjatk.projekt_mpr.model.Car;

import java.util.List;

@Repository
public interface CarRepository extends CrudRepository<Car, Long> {
    public List<Car> findByMake(String make);
}

package pl.edu.pjatk.projekt_mpr.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.edu.pjatk.projekt_mpr.model.Car;
import pl.edu.pjatk.projekt_mpr.service.CarService;

import java.util.List;

@Controller
@RequestMapping("/cars")
public class ViewController {
    private final CarService carService;

    public ViewController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping
    public String getAllCars(Model model) {
        model.addAttribute("cars", carService.getAllCars());
        return "viewAll";
    }

    @GetMapping("/add")
    public String showAddCarForm(Model model) {
        model.addAttribute("car", new Car());
        return "addCarForm";
    }

    @PostMapping
    public String addCar(@ModelAttribute Car car) {
        carService.addCar(car);
        return "redirect:/cars";
    }

    @GetMapping("/update")
    public String showUpdateCarSelectionForm(Model model) {
        List<Car> cars = carService.getAllCars();
        model.addAttribute("cars", cars);
        return "carUpdateSelectionForm";
    }

    @GetMapping("/update/updateForm")
    public String showUpdateCarForm(@RequestParam Long id, Model model) {
        model.addAttribute("car", carService.getCarById(id));
        return "updateCarForm";
    }

    @PostMapping("/update/finish")
    public String updateCar(@ModelAttribute Car car) {
        carService.updateCar(car.getId(), car);
        return "redirect:/cars";
    }

    @GetMapping("/delete")
    public String showDeleteCarForm(Model model) {
        List<Car> cars = carService.getAllCars();
        model.addAttribute("cars", cars);
        return "deleteCarForm";
    }

    @PostMapping("/delete/finish")
    public String deleteCar(@RequestParam Long id) {
        carService.deleteCar(id);
        return "redirect:/cars";
    }
}

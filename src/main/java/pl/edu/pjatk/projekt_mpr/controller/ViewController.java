package pl.edu.pjatk.projekt_mpr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.edu.pjatk.projekt_mpr.model.Car;
import pl.edu.pjatk.projekt_mpr.service.CarService;

import java.util.List;

@Controller
public class ViewController {
    @Autowired
    private CarService carService;

    @GetMapping("view/all")
    public String viewAll(Model model){
        List<Car> cars = carService.getAllCars();
        model.addAttribute("cars", cars);
        return "viewAll";
    }

    @GetMapping("view/add")
    public String showCreateForm(Model model) {
        model.addAttribute("car", new Car());
        return "addCarForm";
    }

    @PostMapping("view/add")
    public String createCar(@ModelAttribute Car car) {
        car.setIdentificator(car.calculateIdentificator());
        carService.createCar(car);
        return "redirect:/view/all";
    }

    @GetMapping("view/delete")
    public String showDeleteSelectionForm(Model model) {
        List<Car> cars = carService.getAllCars();
        model.addAttribute("cars", cars);
        return "deleteCarForm";
    }

    @PostMapping("view/delete")
    public String deleteCarBySelection(@RequestParam Long id) {
        carService.deleteCar(id);
        return "redirect:/view/all";
    }

    @GetMapping("view/update")
    public String showUpdateSelectionForm(Model model) {
        List<Car> cars = carService.getAllCars();
        model.addAttribute("cars", cars);
        return "carUpdateSelectionForm";
    }

    @GetMapping("view/update/form")
    public String showUpdateForm(@RequestParam Long id, Model model) {
        Car car = carService.getById(id);
        model.addAttribute("car", car);
        return "updateCarForm";
    }

    @PostMapping("view/update")
    public String updateCar(@ModelAttribute Car car) {
        carService.updateCar(car.getId(), car);
        return "redirect:/view/all";
    }


}

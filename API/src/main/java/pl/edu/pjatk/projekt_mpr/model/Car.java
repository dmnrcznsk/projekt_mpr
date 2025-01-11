package pl.edu.pjatk.projekt_mpr.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String make;
    private String color;
    private int identificator;

    public Car(){}

    public Car(String make, String color) {
        this.make = make;
        this.color = color;
        this.identificator = this.calculateIdentificator();
    }
    public String getMake() {
        return make;
    }
    public void setMake(String make) {
        this.make = make;
    }
    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public int getIdentificator() {
        return identificator;
    }

    public void setIdentificator(int identificator) {
        this.identificator = identificator;
    }

    public int calculateIdentificator() {
        int sum = 0;
        String combined = make + color;

        for (char c : combined.toCharArray()) {
            sum += Character.getNumericValue(c);
        }

        return sum;
    }
}

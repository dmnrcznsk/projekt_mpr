package pl.edu.pjatk.projekt_mpr.model;

public class Car {
    private String make;
    private String color;

    public Car(String make, String color) {
        this.make = make;
        this.color = color;
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
}

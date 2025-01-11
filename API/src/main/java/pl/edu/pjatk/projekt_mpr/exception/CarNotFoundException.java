package pl.edu.pjatk.projekt_mpr.exception;


public class CarNotFoundException extends RuntimeException {
    public CarNotFoundException() { super("Car not found"); }
    public CarNotFoundException(String message) { super(message); }
}

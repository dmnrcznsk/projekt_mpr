package pl.edu.pjatk.projekt_mpr.exception;

public class CarAlreadyExistsException extends RuntimeException {
    public CarAlreadyExistsException() {
        super("This car already exists.");
    }
    public CarAlreadyExistsException(String message) { super(message); }
}

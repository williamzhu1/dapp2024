package be.kuleuven.foodrestservice.exceptions;

public class OrderInvalidException extends RuntimeException{

    public OrderInvalidException() {
        super("Could not find order ");
    }
}
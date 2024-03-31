package be.kuleuven.foodrestservice.exceptions;

public class MealAlreadyExists extends RuntimeException {

    public MealAlreadyExists() {
        super("MealAlreadyExists");
    }
}

package be.kuleuven.foodrestservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

public class MealAlreadyExists extends RuntimeException {

    public MealAlreadyExists() {
        super("MealAlreadyExists");
    }
}

package be.kuleuven.foodrestservice.controllers;

import be.kuleuven.foodrestservice.domain.Meal;
import be.kuleuven.foodrestservice.domain.MealsRepository;
import be.kuleuven.foodrestservice.domain.Order;
import be.kuleuven.foodrestservice.exceptions.MealNotFoundException;
import be.kuleuven.foodrestservice.exceptions.OrderNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RestController
public class MealsRestRpcStyleController {

    private final MealsRepository mealsRepository;

    @Autowired
    MealsRestRpcStyleController(MealsRepository mealsRepository) {
        this.mealsRepository = mealsRepository;
    }

    @GetMapping("/restrpc/meals/{id}")
    Meal getMealById(@PathVariable String id) {
        Optional<Meal> meal = mealsRepository.findMeal(id);

        return meal.orElseThrow(() -> new MealNotFoundException(id));
    }

    @GetMapping("/restrpc/meals")
    Collection<Meal> getMeals() {
        return mealsRepository.getAllMeal();
    }

    @PostMapping("/restrpc/addMeal")
    void addMeal(@RequestBody Meal meal) {
        mealsRepository.addMeal(meal);
    }

    @PutMapping("/restrpc/meals/{id}")
    void updateMeal(@RequestBody Meal meal) {
        mealsRepository.updateMeal(meal);
    }

    @DeleteMapping("/restrpc/meals/{id}")
    void deleteMealById(@PathVariable String id) {
        mealsRepository.deleteMeal(id);
    }

    @GetMapping("/restrpc/meals/getCheapest")
    Meal getCheapestMeal() {
        return mealsRepository.getCheapestMeal();
    }

    @GetMapping("/restrpc/meals/getLargest")
    Meal getLargestMeal() {
        return mealsRepository.getLargestMeal();
    }

    @GetMapping("/restrpc/orders/{id}")
    Order getOrderById(@PathVariable String id) {
        return mealsRepository.findOrder(id).orElseThrow(() -> new OrderNotFoundException(id));
    }

    @GetMapping("/restrpc/orders")
    Collection<Order> getOrders() {
        return mealsRepository.getAllOrder();
    }

    @PostMapping("/restrpc/addMeal")
    void addOrder(@RequestBody Order order) {
        mealsRepository.addOrder(order);
    }

}

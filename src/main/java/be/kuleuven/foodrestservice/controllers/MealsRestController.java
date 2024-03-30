package be.kuleuven.foodrestservice.controllers;

import be.kuleuven.foodrestservice.domain.Meal;
import be.kuleuven.foodrestservice.domain.MealsRepository;
import be.kuleuven.foodrestservice.domain.Order;
import be.kuleuven.foodrestservice.exceptions.MealNotFoundException;
import be.kuleuven.foodrestservice.exceptions.OrderNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
public class MealsRestController {

    private final MealsRepository mealsRepository;

    @Autowired
    MealsRestController(MealsRepository mealsRepository) {
        this.mealsRepository = mealsRepository;
    }

    @GetMapping("/rest/meals/{id}")
    EntityModel<Meal> getMealById(@PathVariable String id) {
        Meal meal = mealsRepository.findMeal(id).orElseThrow(() -> new MealNotFoundException(id));

        return mealToEntityModel(id, meal);
    }

    @GetMapping("/rest/meals")
    CollectionModel<EntityModel<Meal>> getMeals() {
        Collection<Meal> meals = mealsRepository.getAllMeal();

        List<EntityModel<Meal>> mealEntityModels = new ArrayList<>();
        for (Meal m : meals) {
            EntityModel<Meal> em = mealToEntityModel(m.getId(), m);
            mealEntityModels.add(em);
        }
        return CollectionModel.of(mealEntityModels,
                linkTo(methodOn(MealsRestController.class).getMeals()).withSelfRel());
    }

    @PostMapping("/rest/addMeal")
    void addMeal(@RequestBody Meal meal) {
        mealsRepository.addMeal(meal);
    }

    @PutMapping("/rest/meals/{id}")
    void updateMeal(@RequestBody Meal meal) {
        mealsRepository.updateMeal(meal);
    }

    @DeleteMapping("/rest/meals/{id}")
    void deleteMealById(@PathVariable String id) {
        mealsRepository.deleteMeal(id);
    }

    @GetMapping("/rest/meals/getCheapest")
    EntityModel<Meal> getCheapestMeal() {
        Meal meal = mealsRepository.getCheapestMeal();

        return mealToEntityModel(meal.getId(), meal);
    }

    @GetMapping("/rest/meals/getLargest")
    EntityModel<Meal> getLargestMeal() {
        Meal meal = mealsRepository.getLargestMeal();

        return mealToEntityModel(meal.getId(), meal);
    }

    @GetMapping("/rest/orders/{id}")
    EntityModel<Order> getOrderById(@PathVariable String id) {
        Order order = mealsRepository.findOrder(id).orElseThrow(() -> new OrderNotFoundException(id));

        return orderToEntityModel(id, order);
    }

    @GetMapping("/rest/orders")
    CollectionModel<EntityModel<Order>> getOrder() {
        Collection<Order> orders = mealsRepository.getAllOrder();

        List<EntityModel<Order>> orderEntityModels = new ArrayList<>();
        for (Order o : orders) {
            EntityModel<Order> em = orderToEntityModel(o.getId(), o);
            orderEntityModels.add(em);
        }
        return CollectionModel.of(orderEntityModels,
                linkTo(methodOn(MealsRestController.class).getOrder()).withSelfRel());
    }

    @PostMapping("/rest/addMeal")
    void addOrder(@RequestBody Order order) {
        mealsRepository.addOrder(order);
    }


    private EntityModel<Meal> mealToEntityModel(String id, Meal meal) {
        return EntityModel.of(meal,
                linkTo(methodOn(MealsRestController.class).getMealById(id)).withSelfRel(),
                linkTo(methodOn(MealsRestController.class).getMeals()).withRel("rest/meals"));
    }

    private EntityModel<Order> orderToEntityModel(String id, Order order){
        return EntityModel.of(order,
                linkTo(methodOn(MealsRestController.class).getOrderById(id)).withSelfRel(),
                linkTo(methodOn(MealsRestController.class).getOrder()).withRel("rest/meals"));
    }

}

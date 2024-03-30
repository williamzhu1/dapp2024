package be.kuleuven.foodrestservice.domain;

import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;
import java.util.*;

@Component
public class MealsRepository {
    // map: id -> meal
    private static final Map<String, Meal> meals = new HashMap<>();
    private static ArrayList<Order> orders = new ArrayList<>();

    @PostConstruct
    public void initData() {

        Meal a = new Meal();
        a.setId("5268203c-de76-4921-a3e3-439db69c462a");
        a.setName("Steak");
        a.setDescription("Steak with fries");
        a.setMealType(MealType.MEAT);
        a.setKcal(1100);
        a.setPrice((10.00));

        meals.put(a.getId(), a);

        Meal b = new Meal();
        b.setId("4237681a-441f-47fc-a747-8e0169bacea1");
        b.setName("Portobello");
        b.setDescription("Portobello Mushroom Burger");
        b.setMealType(MealType.VEGAN);
        b.setKcal(637);
        b.setPrice((7.00));

        meals.put(b.getId(), b);

        Meal c = new Meal();
        c.setId("cfd1601f-29a0-485d-8d21-7607ec0340c8");
        c.setName("Fish and Chips");
        c.setDescription("Fried fish with chips");
        c.setMealType(MealType.FISH);
        c.setKcal(950);
        c.setPrice(5.00);

        meals.put(c.getId(), c);

        Order estebanOrder = new Order();
        estebanOrder.setId("1");
        estebanOrder.setAddress("Leuven");
        estebanOrder.addMeal(c);
//        estebanOrder.setMeals(new ArrayList<>(Arrays.asList(c));
        orders.add(estebanOrder);
    }

    public Optional<Meal> findMeal(String id) {
        Assert.notNull(id, "The meal id must not be null");
        Meal meal = meals.get(id);
        return Optional.ofNullable(meal);
    }

    public void addMeal(Meal newMeal){
        Assert.notNull(newMeal, "The meal object must not be null");
        do {
            newMeal.setId(UUID.randomUUID().toString());
        } while (meals.containsKey(newMeal.id));
        meals.put(newMeal.getId(), newMeal);
    }

    public void updateMeal(Meal updatedMeal){
        Assert.notNull(updatedMeal, "The updated meal object must not be null");

        // Check if the meal exists in the repository
        if (meals.containsKey(updatedMeal.id)) {
            meals.put(updatedMeal.id, updatedMeal);
        } else {
            throw new IllegalArgumentException("Meal with id " + updatedMeal.getId() + " does not exist.");
        }
    }

    public void deleteMeal(String id){
        Assert.notNull(id, "The meal id must not be null");
        meals.remove(id);
    }

    public Meal getCheapestMeal() {
        return meals.values().stream().min(Comparator.comparing(Meal::getPrice)).orElse(null);
    }

    public Meal getLargestMeal() {
        return meals.values().stream().max(Comparator.comparing(Meal::getKcal)).orElse(null);
    }

    public Collection<Meal> getAllMeal() {
        return meals.values();
    }

    public Optional<Order> findOrder(String id) {
        Assert.notNull(id, "The order id must not be null");
        Order order = orders.stream().filter(o -> o.getId().equals(id)).findFirst().orElse(null);
        return Optional.ofNullable(order);
    }

    public Collection<Order> getAllOrder() {
        return orders;
    }
}

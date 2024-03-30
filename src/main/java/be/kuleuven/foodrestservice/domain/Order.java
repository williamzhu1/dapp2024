package be.kuleuven.foodrestservice.domain;

import java.util.ArrayList;

public class Order {


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public ArrayList<Meal> getMeals() {
        return meals;
    }

    public void addMeal(Meal meal) {
        meals.add(meal);
    }

    public void setMeals(ArrayList<Meal> meals) {
        this.meals = meals;
    }

    protected String id;
    protected String address;
    protected ArrayList<Meal> meals = new ArrayList<Meal>();

}


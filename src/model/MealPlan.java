package model;

import observer.MealPlanObserver;
import java.util.*;

public class MealPlan {
    private final List<Recipe> meals = new ArrayList<>();
    private final List<MealPlanObserver> observers = new ArrayList<>();

    public void addMeal(Recipe r) {
        meals.add(r);
        notifyObservers();
    }

    public void replaceMeal(int index, Recipe r) {
        meals.set(index, r);
        notifyObservers();
    }

    public List<Recipe> getMeals() {
        return meals;
    }

    public void addObserver(MealPlanObserver observer) {
        observers.add(observer);
    }

    private void notifyObservers() {
        for (MealPlanObserver o : observers) {
            o.update(this);
        }
    }
}
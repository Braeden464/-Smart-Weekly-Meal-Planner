package observer;

import model.*;
import java.util.*;

public class ShoppingListGenerator implements MealPlanObserver {

    public void update(MealPlan plan) {
        Set<String> ingredients = new HashSet<>();

        for (Recipe r : plan.getMeals()) {
            ingredients.addAll(r.getIngredients());
        }

        System.out.println("Shopping List: " + ingredients);
    }
}
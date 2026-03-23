import model.*;
import facade.*;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        List<Recipe> recipes = new ArrayList<>();
        recipes.add(new Recipe("Eggs", List.of("egg"), 10, List.of()));
        recipes.add(new Recipe("Steak", List.of("beef"), 30, List.of()));

        UserPreferences prefs = new UserPreferences(15);

        MealPlannerFacade facade = new MealPlannerFacade();
        MealPlan plan = facade.generateMealPlan(recipes, prefs, 1);

        System.out.println("Meals planned: " + plan.getMeals().size());
    }
}
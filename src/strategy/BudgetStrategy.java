package strategy;

import model.*;
import java.util.*;

public class BudgetStrategy implements MealGenerationStrategy{
    public MealPlan generatePlan(List<Recipe> recipes, UserPreferences prefs) {
        MealPlan plan = new MealPlan();

        for (Recipe r : recipes) {
            if (r.matchesPreferences(prefs)) {
                plan.addMeal(r);
                if (plan.getMeals().size() == 7) break;
            }
        }

        return plan;
    }

}

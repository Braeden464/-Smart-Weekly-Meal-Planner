package strategy;

import model.Recipe;
import model.UserPreferences;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

// Filters recipes that fit within the weekly budget and sorts them cheapest-first.
public class BudgetStrategy implements MealGenerationStrategy {

    @Override
    public List<Recipe> selectRecipes(List<Recipe> available, UserPreferences prefs) {
        // 21 meals per week (3 per day × 7 days); divide total budget equally across all meals.
        double maxCostPerMeal = prefs.getWeeklyBudget() / 21.0;

        // costPerServing is per person, so multiply by personCount to get the total meal cost.
        return available.stream()
                .filter(r -> r.getCostPerServing() * prefs.getPersonCount() <= maxCostPerMeal)
                .sorted(Comparator.comparingDouble(Recipe::getCostPerServing))
                .collect(Collectors.toList());
    }
}
package strategy;

import model.Recipe;
import model.UserPreferences;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class BudgetStrategy implements MealGenerationStrategy {

    @Override
    public List<Recipe> selectRecipes(List<Recipe> available, UserPreferences prefs) {
        // 14 meals per week: lunch + dinner for 7 days
        double maxCostPerMeal = prefs.getWeeklyBudget() / 14.0;

        return available.stream()
                .filter(r -> r.getCostPerServing() * prefs.getPersonCount() <= maxCostPerMeal)
                .sorted(Comparator.comparingDouble(Recipe::getCostPerServing))
                .collect(Collectors.toList());
    }
}
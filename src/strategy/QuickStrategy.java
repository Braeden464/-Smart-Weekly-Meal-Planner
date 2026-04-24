package strategy;

import model.Recipe;
import model.UserPreferences;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class QuickStrategy implements MealGenerationStrategy {

    @Override
    public List<Recipe> selectRecipes(List<Recipe> available, UserPreferences prefs) {
        int maxMinutes = prefs.getCookingTime().getMaxMinutes();

        return available.stream()
                .filter(r -> r.getPrepTimeMinutes() <= maxMinutes)
                .sorted(Comparator.comparingInt(Recipe::getPrepTimeMinutes))
                .collect(Collectors.toList());
    }
}
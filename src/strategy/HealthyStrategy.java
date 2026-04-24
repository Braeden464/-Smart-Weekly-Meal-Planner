package strategy;

import model.Recipe;
import model.UserPreferences;
import java.util.List;
import java.util.stream.Collectors;

public class HealthyStrategy implements MealGenerationStrategy {

    @Override
    public List<Recipe> selectRecipes(List<Recipe> available, UserPreferences prefs) {
        return available.stream()
                .filter(r -> r.matchesTags(prefs.getDietaryTags()))
                .collect(Collectors.toList());
    }
}
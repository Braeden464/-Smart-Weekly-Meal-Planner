package strategy;

import model.Recipe;
import model.UserPreferences;
import java.util.List;

// Each implementation decides which recipes to pick and in what order.
// The Builder uses this list to assemble the actual WeekMenu.
public interface MealGenerationStrategy {
    List<Recipe> selectRecipes(List<Recipe> available, UserPreferences prefs);
}
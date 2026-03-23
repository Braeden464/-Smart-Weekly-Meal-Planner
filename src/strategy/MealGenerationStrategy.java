package strategy;

import model.*;
import java.util.List;

public interface MealGenerationStrategy {
    MealPlan generatePlan(List<Recipe> recipes, UserPreferences prefs);
}

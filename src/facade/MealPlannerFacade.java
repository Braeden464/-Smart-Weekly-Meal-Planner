package facade;

import model.*;
import strategy.*;
import factory.*;

import java.util.List;

public class MealPlannerFacade {

    public MealPlan generateMealPlan(List<Recipe> recipes, UserPreferences prefs, int strategyType) {
        MealGenerationStrategy strategy = StrategySelector.getStrategy(strategyType);
        return strategy.generatePlan(recipes, prefs);
    }
}
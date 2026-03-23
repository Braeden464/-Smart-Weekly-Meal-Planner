import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import strategy.*;
import model.*;

import java.util.*;

public class StrategyTest {

    @Test
    public void testGeneratePlan() {
        MealGenerationStrategy strategy = new BudgetStrategy();

        List<Recipe> recipes = new ArrayList<>();
        recipes.add(new Recipe("Eggs", List.of(), 10, List.of()));

        MealPlan plan = strategy.generatePlan(recipes, new UserPreferences(15));

        assertNotNull(plan);
    }

    @Test
    public void testFiltersRecipes() {
        MealGenerationStrategy strategy = new BudgetStrategy();

        List<Recipe> recipes = new ArrayList<>();
        recipes.add(new Recipe("Slow", List.of(), 30, List.of()));

        MealPlan plan = strategy.generatePlan(recipes, new UserPreferences(10));

        assertEquals(0, plan.getMeals().size());
    }
}

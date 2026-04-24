import factory.RecipeFactory;
import model.CookingTime;
import model.Recipe;
import model.UserPreferences;
import strategy.BudgetStrategy;
import strategy.HealthyStrategy;
import strategy.MealGenerationStrategy;
import strategy.QuickStrategy;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class StrategyTest {

    @Test
    public void budgetStrategy_excludesRecipesAboveBudget() {
        // $50 budget / 14 meals / 2 people = $1.78 max per serving
        UserPreferences prefs = new UserPreferences(2, 50.0, CookingTime.HIGH, List.of());
        List<Recipe> recipes = List.of(
            RecipeFactory.createLunch("Cheap Soup",  List.of(), 20, 1.50, List.of()),
            RecipeFactory.createLunch("Fancy Steak", List.of(), 30, 10.0, List.of())
        );

        List<Recipe> result = new BudgetStrategy().selectRecipes(recipes, prefs);

        assertEquals(1, result.size());
        assertEquals("Cheap Soup", result.get(0).getName());
    }

    @Test
    public void quickStrategy_excludesRecipesAboveTimeLimit() {
        UserPreferences prefs = new UserPreferences(2, 100.0, CookingTime.LOW, List.of()); // max 20 min
        List<Recipe> recipes = List.of(
            RecipeFactory.createLunch("Quick Salad", List.of(), 10, 3.0, List.of()),
            RecipeFactory.createLunch("Slow Roast",  List.of(), 90, 4.0, List.of())
        );

        List<Recipe> result = new QuickStrategy().selectRecipes(recipes, prefs);

        assertEquals(1, result.size());
        assertEquals("Quick Salad", result.get(0).getName());
    }

    @Test
    public void healthyStrategy_filtersRecipesByTags() {
        UserPreferences prefs = new UserPreferences(2, 100.0, CookingTime.HIGH, List.of("vegetarian"));
        List<Recipe> recipes = List.of(
            RecipeFactory.createLunch("Veggie Bowl", List.of(), 15, 2.0, List.of("vegetarian")),
            RecipeFactory.createLunch("Chicken",     List.of(), 20, 4.0, List.of("high-protein"))
        );

        List<Recipe> result = new HealthyStrategy().selectRecipes(recipes, prefs);

        assertEquals(1, result.size());
        assertEquals("Veggie Bowl", result.get(0).getName());
    }

    @Test
    public void strategies_areInterchangeable_throughInterface() {
        // Demonstrates polymorphism: same call, different behavior
        UserPreferences prefs = new UserPreferences(2, 30.0, CookingTime.LOW, List.of());
        List<Recipe> recipes = List.of(
            RecipeFactory.createLunch("Fast Cheap", List.of(), 10, 1.0, List.of()),
            RecipeFactory.createLunch("Slow Cheap", List.of(), 60, 1.0, List.of()),
            RecipeFactory.createLunch("Fast Pricey", List.of(), 10, 9.0, List.of())
        );

        MealGenerationStrategy budget = new BudgetStrategy();
        MealGenerationStrategy quick  = new QuickStrategy();

        // BudgetStrategy keeps cheap ones regardless of time
        List<Recipe> budgetResult = budget.selectRecipes(recipes, prefs);
        // QuickStrategy keeps fast ones regardless of price
        List<Recipe> quickResult  = quick.selectRecipes(recipes, prefs);

        assertEquals(2, budgetResult.size()); // Fast Cheap + Slow Cheap
        assertEquals(2, quickResult.size());  // Fast Cheap + Fast Pricey
    }
}
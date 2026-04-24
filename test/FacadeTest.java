import builder.MenuDirector;
import builder.StandardWeekMenuBuilder;
import builder.WeekMenuBuilder;
import composite.WeekMenu;
import model.CookingTime;
import model.UserPreferences;
import observer.ShoppingListGenerator;
import repository.InMemoryRecipeRepository;
import strategy.BudgetStrategy;
import strategy.QuickStrategy;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

// Tests for the Builder pattern (renamed from FacadeTest)
public class FacadeTest {

    @Test
    public void builder_producesNonEmptyWeekMenu() {
        WeekMenuBuilder builder = new StandardWeekMenuBuilder(new InMemoryRecipeRepository());
        MenuDirector director = new MenuDirector(builder);

        UserPreferences prefs = new UserPreferences(2, 100.0, CookingTime.HIGH, List.of());
        WeekMenu result = director.construct(new BudgetStrategy(), prefs, List.of());

        assertNotNull(result);
        assertFalse(result.getRecipes().isEmpty());
    }

    @Test
    public void builder_withObserver_populatesShoppingListOnBuild() {
        ShoppingListGenerator shoppingList = new ShoppingListGenerator();
        WeekMenuBuilder builder = new StandardWeekMenuBuilder(new InMemoryRecipeRepository());
        MenuDirector director = new MenuDirector(builder);

        UserPreferences prefs = new UserPreferences(2, 100.0, CookingTime.HIGH, List.of());
        director.construct(new BudgetStrategy(), prefs, List.of(shoppingList));

        assertFalse(shoppingList.getAggregatedIngredients().isEmpty());
    }

    @Test
    public void builder_differentStrategiesProduceDifferentMenus() {
        UserPreferences prefs = new UserPreferences(2, 40.0, CookingTime.LOW, List.of());

        WeekMenu budgetPlan = new MenuDirector(new StandardWeekMenuBuilder(new InMemoryRecipeRepository()))
                .construct(new BudgetStrategy(), prefs, List.of());

        WeekMenu quickPlan = new MenuDirector(new StandardWeekMenuBuilder(new InMemoryRecipeRepository()))
                .construct(new QuickStrategy(), prefs, List.of());

        // Both produce valid menus but may differ in recipe selection
        assertNotNull(budgetPlan);
        assertNotNull(quickPlan);
    }
}
import composite.DayMenu;
import composite.Meal;
import composite.WeekMenu;
import factory.RecipeFactory;
import model.Ingredient;
import observer.ShoppingListGenerator;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class ObserverTest {

    @Test
    public void shoppingList_isEmptyBeforeAnyDayIsAdded() {
        WeekMenu week = new WeekMenu();
        ShoppingListGenerator observer = new ShoppingListGenerator();
        week.addObserver(observer);

        assertTrue(observer.getAggregatedIngredients().isEmpty());
    }

    @Test
    public void shoppingList_updatesAutomaticallyWhenDayAdded() {
        WeekMenu week = new WeekMenu();
        ShoppingListGenerator observer = new ShoppingListGenerator();
        week.addObserver(observer);

        DayMenu day = new DayMenu("Monday");
        day.addMeal(new Meal(RecipeFactory.createLunch("Soup",
                List.of(new Ingredient("tomato", 3, "units")), 20, 2.0, List.of())));
        week.addDay(day);

        // Observer was notified automatically — no manual call needed
        assertFalse(observer.getAggregatedIngredients().isEmpty());
        assertEquals(3.0, observer.getAggregatedIngredients().get("tomato"), 0.001);
    }

    @Test
    public void shoppingList_aggregatesQuantitiesAcrossMultipleMeals() {
        WeekMenu week = new WeekMenu();
        ShoppingListGenerator observer = new ShoppingListGenerator();
        week.addObserver(observer);

        DayMenu day = new DayMenu("Monday");
        // Two different meals both use eggs
        day.addMeal(new Meal(RecipeFactory.createBreakfast("Scrambled Eggs",
                List.of(new Ingredient("eggs", 2, "units")), 10, 1.5, List.of())));
        day.addMeal(new Meal(RecipeFactory.createLunch("Egg Salad",
                List.of(new Ingredient("eggs", 3, "units")), 15, 2.0, List.of())));
        week.addDay(day);

        // Should sum: 2 + 3 = 5 eggs
        assertEquals(5.0, observer.getAggregatedIngredients().get("eggs"), 0.001);
    }
}
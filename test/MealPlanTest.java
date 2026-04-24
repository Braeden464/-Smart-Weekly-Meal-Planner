import composite.DayMenu;
import composite.Meal;
import composite.WeekMenu;
import factory.RecipeFactory;
import model.Ingredient;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

// Tests for the Composite pattern (WeekMenu → DayMenu → Meal)
public class MealPlanTest {

    @Test
    public void dayMenu_totalCostSumsAllMeals() {
        DayMenu day = new DayMenu("Monday");
        day.addMeal(new Meal(RecipeFactory.createBreakfast("Oats",  List.of(), 10, 1.50, List.of())));
        day.addMeal(new Meal(RecipeFactory.createLunch("Salad",     List.of(), 15, 3.00, List.of())));
        day.addMeal(new Meal(RecipeFactory.createDinner("Chicken",  List.of(), 25, 5.00, List.of())));

        assertEquals(9.50, day.getTotalCost(), 0.001);
    }

    @Test
    public void weekMenu_totalCostSumsAllDays() {
        WeekMenu week = new WeekMenu();

        DayMenu day1 = new DayMenu("Monday");
        day1.addMeal(new Meal(RecipeFactory.createLunch("Pasta", List.of(), 20, 3.0, List.of())));

        DayMenu day2 = new DayMenu("Tuesday");
        day2.addMeal(new Meal(RecipeFactory.createLunch("Rice",  List.of(), 15, 2.0, List.of())));

        week.addDay(day1);
        week.addDay(day2);

        assertEquals(5.0, week.getTotalCost(), 0.001);
    }

    @Test
    public void weekMenu_getRecipes_returnsAllMealsFlattened() {
        WeekMenu week = new WeekMenu();

        DayMenu day = new DayMenu("Monday");
        day.addMeal(new Meal(RecipeFactory.createBreakfast("Oats",    List.of(), 5,  1.0, List.of())));
        day.addMeal(new Meal(RecipeFactory.createLunch("Salad",       List.of(), 10, 2.0, List.of())));
        day.addMeal(new Meal(RecipeFactory.createDinner("Stir Fry",   List.of(), 20, 4.0, List.of())));

        week.addDay(day);

        assertEquals(3, week.getRecipes().size());
    }
}
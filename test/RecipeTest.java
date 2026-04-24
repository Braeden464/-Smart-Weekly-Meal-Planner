import factory.RecipeFactory;
import model.MealType;
import model.Recipe;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class RecipeTest {

    @Test
    public void recipe_matchesWhenItHasAllRequiredTags() {
        Recipe r = RecipeFactory.createLunch("Salad", List.of(), 10, 2.0,
                List.of("vegetarian", "gluten-free"));

        assertTrue(r.matchesTags(List.of("vegetarian")));
        assertTrue(r.matchesTags(List.of("vegetarian", "gluten-free")));
    }

    @Test
    public void recipe_failsWhenMissingARequiredTag() {
        Recipe r = RecipeFactory.createLunch("Pasta", List.of(), 20, 3.0,
                List.of("vegetarian"));

        assertFalse(r.matchesTags(List.of("vegetarian", "gluten-free")));
    }

    @Test
    public void factory_assignsCorrectMealType() {
        Recipe breakfast = RecipeFactory.createBreakfast("Oats",  List.of(), 5,  1.0, List.of());
        Recipe lunch     = RecipeFactory.createLunch("Salad",     List.of(), 10, 2.0, List.of());
        Recipe dinner    = RecipeFactory.createDinner("Steak",    List.of(), 40, 8.0, List.of());

        assertEquals(MealType.BREAKFAST, breakfast.getType());
        assertEquals(MealType.LUNCH,     lunch.getType());
        assertEquals(MealType.DINNER,    dinner.getType());
    }
}

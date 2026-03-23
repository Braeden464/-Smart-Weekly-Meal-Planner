import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import model.*;
import observer.*;

import java.util.List;

public class MealPlanTest {

    @Test
    public void testAddMeal() {
        MealPlan plan = new MealPlan();
        plan.addMeal(new Recipe("Eggs", List.of("egg"), 10, List.of()));

        assertEquals(1, plan.getMeals().size());
    }

    @Test
    public void testReplaceMeal() {
        MealPlan plan = new MealPlan();
        plan.addMeal(new Recipe("A", List.of(), 10, List.of()));
        plan.replaceMeal(0, new Recipe("B", List.of(), 10, List.of()));

        assertEquals("B", plan.getMeals().get(0).getName());
    }

    @Test
    public void testObserverTriggered() {
        MealPlan plan = new MealPlan();
        plan.addObserver(new ShoppingListGenerator());

        plan.addMeal(new Recipe("Eggs", List.of("egg"), 10, List.of()));

        assertEquals(1, plan.getMeals().size());
    }
}

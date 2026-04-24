package observer;

import composite.WeekMenu;
import model.Ingredient;
import model.Recipe;
import java.util.*;

public class ShoppingListGenerator implements MenuObserver {

    // ingredient name → total quantity needed across all meals
    private final Map<String, Double> aggregated = new LinkedHashMap<>();

    @Override
    public void onMenuChanged(WeekMenu menu) {
        aggregated.clear();
        for (Recipe recipe : menu.getRecipes()) {
            for (Ingredient ingredient : recipe.getIngredients()) {
                // merge sums quantities when the same ingredient appears in multiple recipes
                aggregated.merge(ingredient.getName(), ingredient.getQuantity(), Double::sum);
            }
        }
    }

    public void printShoppingList() {
        System.out.println("=== Shopping List ===");
        aggregated.forEach((name, qty) ->
                System.out.printf("  - %s: %.1f%n", name, qty));
    }

    public Map<String, Double> getAggregatedIngredients() {
        return Collections.unmodifiableMap(aggregated);
    }
}
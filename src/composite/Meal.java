package composite;

import model.Recipe;
import java.util.List;

public class Meal implements MenuComponent {
    private final Recipe recipe;

    public Meal(Recipe recipe) {
        this.recipe = recipe;
    }

    @Override
    public String getName() { return recipe.getName(); }

    @Override
    public List<Recipe> getRecipes() { return List.of(recipe); }

    @Override
    public double getTotalCost() { return recipe.getCostPerServing(); }

    @Override
    public void display() {
        System.out.printf("      [Meal] %s - $%.2f%n", recipe.getName(), recipe.getCostPerServing());
    }
}
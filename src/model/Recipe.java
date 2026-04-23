package model;

import java.util.List;

public class Recipe {
    private final String name;
    private final List<Ingredient> ingredients;
    private final int prepTimeMinutes;
    private final double costPerServing;
    private final List<String> tags;  // e.g. "vegetarian", "high-protein", "gluten-free"
    private final MealType type;

    public Recipe(String name, List<Ingredient> ingredients, int prepTimeMinutes,
                  double costPerServing, List<String> tags, MealType type) {
        this.name = name;
        this.ingredients = ingredients;
        this.prepTimeMinutes = prepTimeMinutes;
        this.costPerServing = costPerServing;
        this.tags = tags;
        this.type = type;
    }

    // Returns true only if this recipe has ALL the required tags
    public boolean matchesTags(List<String> requiredTags) {
        return tags.containsAll(requiredTags);
    }

    public String getName() { return name; }
    public List<Ingredient> getIngredients() { return ingredients; }
    public int getPrepTimeMinutes() { return prepTimeMinutes; }
    public double getCostPerServing() { return costPerServing; }
    public List<String> getTags() { return tags; }
    public MealType getType() { return type; }
}
package model;

import java.util.List;

public class Recipe {
    private final String name;
    private final List<String> ingredients;
    private final int prepTime;

    public Recipe(String name, List<String> ingredients, int prepTime, List<String> tags) {
        this.name = name;
        this.ingredients = ingredients;
        this.prepTime = prepTime;
    }

    public boolean matchesPreferences(UserPreferences prefs) {
        return prepTime <= prefs.getMaxTime();
    }

    public String getName() { return name; }
    public List<String> getIngredients() { return ingredients; }
}
package factory;

import model.Ingredient;
import model.MealType;
import model.Recipe;
import java.util.List;

public class RecipeFactory {

    // Central creation method — the type parameter is what makes this a real factory
    public static Recipe create(MealType type, String name, List<Ingredient> ingredients,
                                int prepTimeMinutes, double costPerServing, List<String> tags) {
        return new Recipe(name, ingredients, prepTimeMinutes, costPerServing, tags, type);
    }

    public static Recipe createBreakfast(String name, List<Ingredient> ingredients,
                                         int prepTimeMinutes, double costPerServing, List<String> tags) {
        return create(MealType.BREAKFAST, name, ingredients, prepTimeMinutes, costPerServing, tags);
    }

    public static Recipe createLunch(String name, List<Ingredient> ingredients,
                                     int prepTimeMinutes, double costPerServing, List<String> tags) {
        return create(MealType.LUNCH, name, ingredients, prepTimeMinutes, costPerServing, tags);
    }

    public static Recipe createDinner(String name, List<Ingredient> ingredients,
                                      int prepTimeMinutes, double costPerServing, List<String> tags) {
        return create(MealType.DINNER, name, ingredients, prepTimeMinutes, costPerServing, tags);
    }
}
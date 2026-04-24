package repository;

import factory.RecipeFactory;
import model.Ingredient;
import model.Recipe;
import java.util.List;

public class InMemoryRecipeRepository implements RecipeRepository {

    @Override
    public List<Recipe> findAll() {
        return List.of(
            // Breakfasts
            RecipeFactory.createBreakfast("Oatmeal",
                List.of(new Ingredient("oats", 80, "g"), new Ingredient("milk", 200, "ml")),
                10, 1.50, List.of("vegetarian")),

            RecipeFactory.createBreakfast("Scrambled Eggs",
                List.of(new Ingredient("eggs", 3, "units"), new Ingredient("butter", 10, "g")),
                10, 2.00, List.of("high-protein", "gluten-free")),

            RecipeFactory.createBreakfast("Greek Yogurt Bowl",
                List.of(new Ingredient("greek yogurt", 200, "g"), new Ingredient("honey", 20, "ml")),
                5, 2.50, List.of("vegetarian", "high-protein")),

            // Lunches
            RecipeFactory.createLunch("Lentil Soup",
                List.of(new Ingredient("lentils", 200, "g"), new Ingredient("carrot", 1, "units")),
                30, 2.00, List.of("vegetarian", "vegan", "gluten-free")),

            RecipeFactory.createLunch("Grilled Chicken Salad",
                List.of(new Ingredient("chicken breast", 200, "g"), new Ingredient("lettuce", 100, "g")),
                20, 4.50, List.of("high-protein", "gluten-free")),

            RecipeFactory.createLunch("Pasta Primavera",
                List.of(new Ingredient("pasta", 200, "g"), new Ingredient("zucchini", 1, "units")),
                25, 3.00, List.of("vegetarian")),

            // Dinners
            RecipeFactory.createDinner("Beef Stir Fry",
                List.of(new Ingredient("beef", 250, "g"), new Ingredient("bell pepper", 1, "units")),
                25, 6.00, List.of("high-protein", "gluten-free")),

            RecipeFactory.createDinner("Vegetable Curry",
                List.of(new Ingredient("chickpeas", 200, "g"), new Ingredient("coconut milk", 400, "ml")),
                35, 3.50, List.of("vegetarian", "vegan", "gluten-free")),

            RecipeFactory.createDinner("Baked Salmon",
                List.of(new Ingredient("salmon fillet", 200, "g"), new Ingredient("lemon", 1, "units")),
                30, 7.00, List.of("high-protein", "gluten-free")),

            RecipeFactory.createDinner("Spaghetti Bolognese",
                List.of(new Ingredient("ground beef", 300, "g"), new Ingredient("pasta", 200, "g")),
                45, 5.00, List.of("high-protein"))
        );
    }
}
import builder.MenuDirector;
import builder.StandardWeekMenuBuilder;
import builder.WeekMenuBuilder;
import composite.WeekMenu;
import model.CookingTime;
import model.UserPreferences;
import observer.ShoppingListGenerator;
import repository.InMemoryRecipeRepository;
import repository.RecipeRepository;
import strategy.BudgetStrategy;
import strategy.QuickStrategy;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        // --- Setup (shared across both runs) ---
        RecipeRepository repository = new InMemoryRecipeRepository();  // DI: coded to interface
        ShoppingListGenerator shoppingList = new ShoppingListGenerator(); // Observer

        // --- Run 1: Budget Strategy ---
        System.out.println("========================================");
        System.out.println(" PLAN 1: Budget Strategy (max $70/week, 2 people)");
        System.out.println("========================================");

        UserPreferences budgetPrefs = new UserPreferences(
                2,                          // person count
                70.0,                       // weekly budget
                CookingTime.MEDIUM,         // up to 45 min
                List.of("gluten-free")      // dietary tags
        );

        WeekMenuBuilder builder = new StandardWeekMenuBuilder(repository);
        MenuDirector director = new MenuDirector(builder);

        // Builder + Observer + Strategy all wired through the Director
        WeekMenu plan1 = director.construct(new BudgetStrategy(), budgetPrefs, List.of(shoppingList));

        plan1.display();
        System.out.println();
        shoppingList.printShoppingList();

        // --- Run 2: Quick Strategy (swap strategy, no other change) ---
        System.out.println("\n========================================");
        System.out.println(" PLAN 2: Quick Strategy (max 20 min, vegetarian)");
        System.out.println("========================================");

        UserPreferences quickPrefs = new UserPreferences(
                2,
                100.0,
                CookingTime.LOW,            // up to 20 min
                List.of("vegetarian")
        );

        // New builder instance, same director pattern — Strategy swapped, everything else identical
        WeekMenuBuilder builder2 = new StandardWeekMenuBuilder(repository);
        MenuDirector director2 = new MenuDirector(builder2);

        WeekMenu plan2 = director2.construct(new QuickStrategy(), quickPrefs, List.of(shoppingList));

        plan2.display();
        System.out.println();
        shoppingList.printShoppingList();
    }
}
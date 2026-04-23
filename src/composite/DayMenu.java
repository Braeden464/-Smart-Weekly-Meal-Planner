package composite;

import model.Recipe;
import java.util.ArrayList;
import java.util.List;

public class DayMenu implements MenuComponent {
    private final String dayName;
    private final List<MenuComponent> meals = new ArrayList<>();

    public DayMenu(String dayName) {
        this.dayName = dayName;
    }

    public void addMeal(MenuComponent meal) {
        meals.add(meal);
    }

    @Override
    public String getName() { return dayName; }

    @Override
    public List<Recipe> getRecipes() {
        List<Recipe> all = new ArrayList<>();
        for (MenuComponent m : meals) {
            all.addAll(m.getRecipes());  // each Meal returns its own recipe
        }
        return all;
    }

    @Override
    public double getTotalCost() {
        double total = 0;
        for (MenuComponent m : meals) total += m.getTotalCost();
        return total;
    }

    @Override
    public void display() {
        System.out.printf("  [Day] %s (total: $%.2f)%n", dayName, getTotalCost());
        for (MenuComponent m : meals) m.display();
    }
}
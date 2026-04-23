package composite;

import model.Recipe;
import java.util.List;

public interface MenuComponent {
    String getName();
    List<Recipe> getRecipes();  // returns all recipes flattened from this level down
    double getTotalCost();
    void display();
}
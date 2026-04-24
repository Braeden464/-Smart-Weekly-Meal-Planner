package repository;

import model.Recipe;
import java.util.List;

public interface RecipeRepository {
    List<Recipe> findAll();
}
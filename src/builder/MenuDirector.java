package builder;

import composite.WeekMenu;
import model.UserPreferences;
import observer.MenuObserver;
import strategy.MealGenerationStrategy;
import java.util.List;

public class MenuDirector {

    private final WeekMenuBuilder builder;

    public MenuDirector(WeekMenuBuilder builder) {
        this.builder = builder;
    }

    // Orchestrates the build steps in the correct order
    public WeekMenu construct(MealGenerationStrategy strategy,
                              UserPreferences prefs,
                              List<MenuObserver> observers) {
        observers.forEach(builder::addObserver);
        builder.setStrategy(strategy)
               .setPreferences(prefs)
               .build();
        return builder.getResult();
    }
}
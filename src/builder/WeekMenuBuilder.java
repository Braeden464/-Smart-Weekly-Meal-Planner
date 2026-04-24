package builder;

import composite.WeekMenu;
import observer.MenuObserver;
import strategy.MealGenerationStrategy;
import model.UserPreferences;

public interface WeekMenuBuilder {
    WeekMenuBuilder setStrategy(MealGenerationStrategy strategy);
    WeekMenuBuilder setPreferences(UserPreferences prefs);
    WeekMenuBuilder addObserver(MenuObserver observer);
    void build();
    WeekMenu getResult();
}
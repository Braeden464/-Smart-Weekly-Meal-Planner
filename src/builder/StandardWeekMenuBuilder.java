package builder;

import composite.DayMenu;
import composite.Meal;
import composite.WeekMenu;
import model.MealType;
import model.Recipe;
import model.UserPreferences;
import observer.MenuObserver;
import repository.RecipeRepository;
import strategy.MealGenerationStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StandardWeekMenuBuilder implements WeekMenuBuilder {

    private static final String[] DAY_NAMES = {
        "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"
    };

    private final RecipeRepository repository;
    private MealGenerationStrategy strategy;
    private UserPreferences prefs;
    private final List<MenuObserver> observers = new ArrayList<>();
    private WeekMenu weekMenu;

    public StandardWeekMenuBuilder(RecipeRepository repository) {
        this.repository = repository;
    }

    @Override
    public WeekMenuBuilder setStrategy(MealGenerationStrategy strategy) {
        this.strategy = strategy;
        return this;
    }

    @Override
    public WeekMenuBuilder setPreferences(UserPreferences prefs) {
        this.prefs = prefs;
        return this;
    }

    @Override
    public WeekMenuBuilder addObserver(MenuObserver observer) {
        observers.add(observer);
        return this;
    }

    @Override
    public void build() {
        weekMenu = new WeekMenu();
        observers.forEach(weekMenu::addObserver);

        // Strategy filters the full recipe catalog
        List<Recipe> selected = strategy.selectRecipes(repository.findAll(), prefs);

        List<Recipe> breakfasts = byType(selected, MealType.BREAKFAST);
        List<Recipe> lunches    = byType(selected, MealType.LUNCH);
        List<Recipe> dinners    = byType(selected, MealType.DINNER);

        for (int i = 0; i < 7; i++) {
            DayMenu day = new DayMenu(DAY_NAMES[i]);

            // % cycles through available recipes if there are fewer than 7
            if (!breakfasts.isEmpty()) day.addMeal(new Meal(breakfasts.get(i % breakfasts.size())));
            if (!lunches.isEmpty())    day.addMeal(new Meal(lunches.get(i % lunches.size())));
            if (!dinners.isEmpty())    day.addMeal(new Meal(dinners.get(i % dinners.size())));

            weekMenu.addDay(day); // triggers Observer notification automatically
        }
    }

    private List<Recipe> byType(List<Recipe> recipes, MealType type) {
        return recipes.stream()
                .filter(r -> r.getType() == type)
                .collect(Collectors.toList());
    }

    @Override
    public WeekMenu getResult() {
        return weekMenu;
    }
}
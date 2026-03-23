package factory;

import strategy.*;

public class StrategySelector {
    public static MealGenerationStrategy getStrategy(int option) {
        if (option == 1) {
            return new BudgetStrategy();
        }
        return new BudgetStrategy();
    }
}
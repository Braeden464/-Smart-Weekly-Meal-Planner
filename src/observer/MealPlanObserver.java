package observer;

import model.MealPlan;

public interface MealPlanObserver {
    void update(MealPlan plan);
}
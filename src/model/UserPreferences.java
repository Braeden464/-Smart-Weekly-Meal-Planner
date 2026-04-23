package model;

import java.util.List;

public class UserPreferences {
    private final int personCount;
    private final double weeklyBudget;
    private final CookingTime cookingTime;
    private final List<String> dietaryTags;

    public UserPreferences(int personCount, double weeklyBudget,
                           CookingTime cookingTime, List<String> dietaryTags) {
        this.personCount = personCount;
        this.weeklyBudget = weeklyBudget;
        this.cookingTime = cookingTime;
        this.dietaryTags = dietaryTags;
    }

    public int getPersonCount() { return personCount; }
    public double getWeeklyBudget() { return weeklyBudget; }
    public CookingTime getCookingTime() { return cookingTime; }
    public List<String> getDietaryTags() { return dietaryTags; }
}
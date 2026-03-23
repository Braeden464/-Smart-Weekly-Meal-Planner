package model;

public class UserPreferences {
    private final int maxTime;

    public UserPreferences(int maxTime) {
        this.maxTime = maxTime;
    }

    public int getMaxTime() {
        return maxTime;
    }
}
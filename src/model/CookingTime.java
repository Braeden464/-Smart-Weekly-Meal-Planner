package model;

public enum CookingTime {
    LOW(20),
    MEDIUM(45),
    HIGH(120);

    private final int maxMinutes;

    CookingTime(int maxMinutes) {
        this.maxMinutes = maxMinutes;
    }

    public int getMaxMinutes() { return maxMinutes; }
}
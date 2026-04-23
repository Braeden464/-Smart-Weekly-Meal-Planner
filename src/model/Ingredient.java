package model;

public class Ingredient {
    private final String name;
    private final double quantity;
    private final String unit; // e.g. "g", "ml", "units"

    public Ingredient(String name, double quantity, String unit) {
        this.name = name;
        this.quantity = quantity;
        this.unit = unit;
    }

    public String getName() { return name; }
    public double getQuantity() { return quantity; }
    public String getUnit() { return unit; }

    @Override
    public String toString() {
        return quantity + " " + unit + " of " + name;
    }
}
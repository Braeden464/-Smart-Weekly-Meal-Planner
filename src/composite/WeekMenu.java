package composite;

import model.Recipe;
import observer.MenuObserver;
import java.util.ArrayList;
import java.util.List;

public class WeekMenu implements MenuComponent {
    private final List<MenuComponent> days = new ArrayList<>();
    private final List<MenuObserver> observers = new ArrayList<>();

    public void addDay(MenuComponent day) {
        days.add(day);
        notifyObservers();  // every time a day is added, observers are notified
    }

    public void addObserver(MenuObserver observer) {
        observers.add(observer);
    }

    private void notifyObservers() {
        for (MenuObserver o : observers) o.onMenuChanged(this);
    }

    @Override
    public String getName() { return "Weekly Menu"; }

    @Override
    public List<Recipe> getRecipes() {
        List<Recipe> all = new ArrayList<>();
        for (MenuComponent d : days) all.addAll(d.getRecipes());
        return all;
    }

    @Override
    public double getTotalCost() {
        double total = 0;
        for (MenuComponent d : days) total += d.getTotalCost();
        return total;
    }

    @Override
    public void display() {
        System.out.printf("[WeekMenu] Total cost: $%.2f%n", getTotalCost());
        for (MenuComponent d : days) d.display();
    }
}
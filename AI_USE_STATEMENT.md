# AI Use Statement

This project was developed independently. The only use of AI assistance (Claude) was limited to the following three lines in `src/builder/StandardWeekMenuBuilder.java`:

```java
if (!breakfasts.isEmpty()) day.addMeal(new Meal(breakfasts.get(i % breakfasts.size())));
if (!lunches.isEmpty())    day.addMeal(new Meal(lunches.get(i % lunches.size())));
if (!dinners.isEmpty())    day.addMeal(new Meal(dinners.get(i % dinners.size())));
```

The integration and structure of all design patterns used in this project (Factory, Builder, Composite, Observer, Strategy) were fully designed and implemented us.
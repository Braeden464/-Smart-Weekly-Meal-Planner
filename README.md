# Smart Weekly Meal Planner

## Project Overview

The Smart Weekly Meal Planner is a Java-based object-oriented application that generates a personalized weekly meal plan based on the user's preferences: number of people, weekly budget, available cooking time, and dietary requirements (e.g. vegetarian, gluten-free, high-protein).

The system produces a structured week menu (organized by day and meal type), automatically generates an aggregated shopping list, and supports switching between different planning algorithms at runtime.

---

## Design Patterns Used

### 1. Strategy Pattern
**Location:** `src/strategy/`

**How it is used:** The user's preferences can be optimized in different ways. The `MealGenerationStrategy` interface defines a single method `selectRecipes(available, prefs)` that each algorithm implements differently:

- `BudgetStrategy` — divides the weekly budget across 14 meals and keeps only recipes whose cost per serving fits within that limit, sorted cheapest first.
- `QuickStrategy` — filters recipes by preparation time using the `CookingTime` enum (LOW = 20 min, MEDIUM = 45 min, HIGH = 120 min), sorted fastest first.
- `HealthyStrategy` — returns only recipes that contain all the dietary tags the user requested (e.g. `"vegetarian"` and `"gluten-free"`).

The `MenuDirector` receives any `MealGenerationStrategy` implementation and delegates recipe selection to it without knowing which algorithm is running. Swapping from `BudgetStrategy` to `QuickStrategy` requires changing a single line in `Main`.

**OO principle demonstrated:** Polymorphism — the same `selectRecipes()` call produces different results depending on the concrete class behind the interface.

---

### 2. Composite Pattern
**Location:** `src/composite/`

**How it is used:** A weekly meal plan is a natural tree: a week contains days, and each day contains meals. The `MenuComponent` interface unifies all three levels so that operations like `getTotalCost()` and `getRecipes()` work identically at any level of the hierarchy.

```
WeekMenu   (root composite)   → holds 7 DayMenu nodes
  DayMenu  (inner composite)  → holds Meal leaves (breakfast, lunch, dinner)
    Meal   (leaf)             → wraps a single Recipe
```

`WeekMenu.getTotalCost()` calls `DayMenu.getTotalCost()` which calls `Meal.getTotalCost()` — no level needs to know about the others. Adding a new level to the hierarchy would require no changes to the calling code.

**OO principle demonstrated:** Coding to abstractions — `MenuDirector` and the tests only interact with `MenuComponent`, never with concrete subclasses.

---

### 3. Simple Factory Pattern
**Location:** `src/factory/`

**How it is used:** `RecipeFactory` centralizes the creation of `Recipe` objects and ensures each one is tagged with the correct `MealType`. Rather than constructing `Recipe` directly with all six parameters scattered across the codebase, callers use:

```java
RecipeFactory.createBreakfast(name, ingredients, prepTime, cost, tags)
RecipeFactory.createLunch(...)
RecipeFactory.createDinner(...)
```

All three methods delegate to a central `create(MealType, ...)` method. `InMemoryRecipeRepository` uses the factory to build the entire recipe catalog — creating three distinct types of objects from a single point, which is the core purpose of the pattern.

**OO principle demonstrated:** Encapsulation of object creation — if the `Recipe` constructor changes, only `RecipeFactory` needs to be updated.

---

### 4. Observer Pattern
**Location:** `src/observer/`, `src/composite/WeekMenu.java`

**How it is used:** `WeekMenu` acts as the subject. It holds a list of `MenuObserver` instances and calls `notifyObservers()` every time a `DayMenu` is added via `addDay()`. `ShoppingListGenerator` implements `MenuObserver` and reacts by rebuilding the aggregated shopping list from scratch.

```
weekMenu.addDay(day)
  → notifyObservers()
      → ShoppingListGenerator.onMenuChanged(weekMenu)
          → iterates all recipes, sums ingredient quantities
```

The ingredient aggregation uses `Map.merge()` to sum quantities when the same ingredient appears in multiple recipes (e.g. eggs used in both breakfast and lunch). The shopping list is always in sync with the menu without any manual update call.

**OO principle demonstrated:** Loose coupling — `WeekMenu` knows nothing about shopping lists, and `ShoppingListGenerator` knows nothing about how the menu was built.

---

## Object-Oriented Principles

### Coding to Abstractions
`RecipeRepository` is an interface; `MenuDirector` receives a `WeekMenuBuilder` interface; strategies are passed as `MealGenerationStrategy`. No class depends on a concrete implementation it could instead receive through an interface.

### Polymorphism
`BudgetStrategy`, `QuickStrategy`, and `HealthyStrategy` all implement `MealGenerationStrategy`. The test `strategies_areInterchangeable_throughInterface` in `StrategyTest` demonstrates that the same call produces different outputs depending on the runtime type.

### Dependency Injection
`StandardWeekMenuBuilder` receives a `RecipeRepository` through its constructor. `MenuDirector` receives a `WeekMenuBuilder`. Neither hardcodes its dependencies — they are injected by the caller (`Main`).

---

## Project Structure

```
src/
  model/
    CookingTime.java
    MealType.java
    Ingredient.java
    Recipe.java
    UserPreferences.java

  composite/
    MenuComponent.java
    Meal.java
    DayMenu.java
    WeekMenu.java

  strategy/
    MealGenerationStrategy.java
    BudgetStrategy.java
    HealthyStrategy.java
    QuickStrategy.java

  factory/
    RecipeFactory.java

  observer/
    MenuObserver.java
    ShoppingListGenerator.java

  builder/
    WeekMenuBuilder.java
    StandardWeekMenuBuilder.java
    MenuDirector.java

  repository/
    RecipeRepository.java
    InMemoryRecipeRepository.java

  Main.java

test/
  StrategyTest.java   (4 tests)
  RecipeTest.java     (3 tests)
  MealPlanTest.java   (3 tests — Composite)
  ObserverTest.java   (3 tests)
  FacadeTest.java     (3 tests — Builder)
```

---

## Testing

16 unit tests covering all four patterns:

- **Strategy:** each algorithm filters correctly; all three implementations are interchangeable through the interface
- **Composite:** cost aggregation cascades through the tree; `getRecipes()` returns a flat list from any level
- **Factory:** `RecipeFactory` assigns the correct `MealType` to each recipe created
- **Observer:** `ShoppingListGenerator` updates automatically on `addDay()`; ingredient quantities are summed across meals
- **Builder:** `MenuDirector` produces a valid `WeekMenu`; observer is populated after build

---

## How to Run

**Requirements:** Java 17+

**Run the application:**
```bash
./gradlew run
```

**Run all tests:**
```bash
./gradlew test
```

On Windows use `gradlew.bat` instead of `./gradlew`, or run directly from IntelliJ IDEA (File → Open → select project folder, then load as Gradle project).

---

## Authors
Juan Marin & Brady Gaona
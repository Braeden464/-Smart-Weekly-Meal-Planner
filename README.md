# Smart Weekly Meal Planner

## Project Overview
The Smart Weekly Meal Planner is a Java-based object-oriented application that helps users generate weekly meal plans based on preferences such as cooking time, budget, and dietary needs. The system also generates a shopping list automatically and is designed using multiple software design patterns to ensure modularity and scalability.

---

## Design Patterns Used

### 1. Strategy Pattern
**Location:** `src/strategy/`

The Strategy Pattern is used to define different algorithms for generating meal plans. The system can switch between different meal planning strategies without modifying the core logic.

- Interface: `MealGenerationStrategy`
- Implementation: `BudgetStrategy`

**Purpose:**
- Allows multiple meal generation algorithms
- Supports runtime flexibility
- Demonstrates polymorphism

---

### 2. Factory Pattern
**Location:** `src/factory/`

The Factory Pattern is used to create instances of meal generation strategies.

- Class: `StrategySelector`

**Purpose:**
- Encapsulates object creation
- Reduces dependency on concrete classes
- Makes it easy to add new strategies in the future

---

### 3. Observer Pattern
**Location:** `src/observer/`

The Observer Pattern is used to automatically update the shopping list whenever the meal plan changes.

- Subject: `MealPlan`
- Interface: `MealPlanObserver`
- Concrete Observer: `ShoppingListGenerator`

**Purpose:**
- Automatically synchronizes shopping list with meal plan
- Reduces manual updates
- Promotes loose coupling

---

### 4. Facade Pattern
**Location:** `src/facade/`

The Facade Pattern provides a simplified interface for generating meal plans.

- Class: `MealPlannerFacade`

**Purpose:**
- Simplifies system usage
- Hides complexity from `Main`
- Provides a single entry point for meal planning

---

## Object-Oriented Principles

### Abstraction
Interfaces such as `MealGenerationStrategy` define behavior without implementation details.

### Polymorphism
Different strategy implementations provide different behaviors through the same interface.

### Dependency Injection
Strategies are selected and passed into the system rather than being hardcoded.

---

## Project Structure

src/
model/
Recipe.java
MealPlan.java
UserPreferences.java

strategy/
MealGenerationStrategy.java
BudgetStrategy.java

factory/
StrategySelector.java

observer/
MealPlanObserver.java
ShoppingListGenerator.java

facade/
MealPlannerFacade.java

Main.java

test/
MealPlanTest.java
RecipeTest.java
StrategyTest.java

---

## Testing
The project includes unit tests covering:

- Adding meals to a meal plan
- Replacing meals
- Recipe preference matching
- Strategy-based meal generation
- Observer updates

---

## Author
Juan Marin & Brady Gaona

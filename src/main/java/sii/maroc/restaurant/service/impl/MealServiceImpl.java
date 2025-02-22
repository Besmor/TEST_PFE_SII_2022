package sii.maroc.restaurant.service.impl;

import sii.maroc.restaurant.meal.Meal;
import sii.maroc.restaurant.meal.Recipe;
import sii.maroc.restaurant.service.MealService;

import java.util.HashMap;
import java.util.Map;

import static sii.maroc.restaurant.stock.Stock.ITEM_UNLIMITED_AMOUNT;

public class MealServiceImpl implements MealService {
    private static final Map<String, Recipe> DEFAULT_RECIPES_BY_NAME = new HashMap<>();

    static {
        initDefaultRecipes();
    }

    @Override
    public Meal loadMeal(String recipeName, int dishCount) {
        Recipe recipe = loadRecipe(recipeName);
        return new Meal(recipe, dishCount);
    }

    private Recipe loadRecipe(String recipeName) {
        return DEFAULT_RECIPES_BY_NAME.get(recipeName);
    }

    private static void initDefaultRecipes() {
        String recipeName = "Tomato Mozzarella Salad";
        Recipe recipe = new Recipe();
        recipe.addIngredient("balls Mozzarella", 1);
        recipe.addIngredient("tomatoes", 2);
        recipe.addIngredient("olive oil", ITEM_UNLIMITED_AMOUNT);
        recipe.setPreparationTimeInMinutes(6);
        DEFAULT_RECIPES_BY_NAME.put(recipeName, recipe);
    }
}

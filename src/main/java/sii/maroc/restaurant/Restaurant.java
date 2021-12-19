package sii.maroc.restaurant;

import sii.maroc.restaurant.exception.UnavailableDishException;
import sii.maroc.restaurant.meal.Ingredients;
import sii.maroc.restaurant.meal.Meal;
import sii.maroc.restaurant.meal.Ticket;
import sii.maroc.restaurant.stock.Stock;
import sii.maroc.restaurant.exception.ItemOutOfStockException;
import sii.maroc.restaurant.exception.NotEnoughAmountInStockException;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Restaurant {
    private final Stock stock;
    private final Map<Ticket, Meal> ticketToMeal = new HashMap<>();

    public Restaurant(String... stockItems) {
        stock = new Stock(stockItems);
    }

    public Ticket order(String order) {
        Objects.requireNonNull(order, "You should specify the order");

        Ticket ticket = new Ticket();
        Meal meal = Meal.parseMealFromOrder(order);
        prepare(meal);

        ticketToMeal.put(ticket, meal);

        return ticket;
    }

    private void prepare(Meal meal) {
        try {
            Ingredients ingredients = meal.getNeededIngredients();
            stock.retrieveAll(ingredients);
        } catch (ItemOutOfStockException | NotEnoughAmountInStockException e) {
            throw new UnavailableDishException("We could not prepare recipe", e);
        }
    }

    public Meal retrieveMealFor(Ticket ticket) {
        return ticketToMeal.remove(ticket);
    }

    public double getStockAmountOf(String itemName) {
        return stock.getAmountOf(itemName);
    }
}

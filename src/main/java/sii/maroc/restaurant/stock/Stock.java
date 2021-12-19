package sii.maroc.restaurant.stock;

import sii.maroc.restaurant.meal.Ingredients;
import sii.maroc.restaurant.exception.ItemOutOfStockException;
import sii.maroc.restaurant.exception.NotEnoughAmountInStockException;

public class Stock {
    public static final int ITEM_UNLIMITED_AMOUNT = -1;

    private final StockItems stockItems;

    public Stock(String... itemsAsString) {
       stockItems = StockItems.fromItemsAsString(itemsAsString);
    }

    public Ingredients retrieveAll(Ingredients ingredients)  {
        return (Ingredients) stockItems.retrieveAndUpdateItems(ingredients);
    }

    public int getAmountOf(String itemName) {
        return stockItems.getAmountOf(itemName);
    }
}

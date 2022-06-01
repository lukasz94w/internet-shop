package pl.lukasz94w.shop;

import pl.lukasz94w.exception.BasketException;

import java.util.HashMap;
import java.util.Map;

public final class Basket {
    private final Map<Item, Integer> orderedItems;

    public Basket() {
        orderedItems = new HashMap<>();
    }

    public void addItems(Integer numberOfItemsToAdd, Item item) {
        if (numberOfItemsToAdd <= 0 || numberOfItemsToAdd > 100) {
            throw new BasketException("Number of items must be a number between 1 and 100");
        }

        // could be used there .contains() instead
        Integer numberOfAlreadyExistedItems = orderedItems.get(item);
        if (numberOfAlreadyExistedItems != null) {
            orderedItems.put(item, numberOfAlreadyExistedItems + numberOfItemsToAdd);
        } else {
            orderedItems.put(item, numberOfItemsToAdd);
            System.out.println("Successfully added " + numberOfItemsToAdd + " items");
        }
    }

    public void removeItems(Integer numberOfItemsToDelete, Item item) {
        if (numberOfItemsToDelete <= 0) {
            throw new BasketException("Cannot delete negative number of items");
        }

        // could be used there .contains() instead
        Integer numberOfAlreadyExistedItems = orderedItems.get(item);
        if (numberOfAlreadyExistedItems == null) {
            throw new BasketException("Such item(s) doesn't exist in the basket");
        } else {
            int newNumberOfItems = numberOfAlreadyExistedItems - numberOfItemsToDelete;
            if (newNumberOfItems < 0) {
                orderedItems.remove(item);
            } else {
                orderedItems.put(item, newNumberOfItems);
            }
            System.out.println("Successfully removed items from basket");
        }
    }

    public Integer getNumberOfSpecificItem(Item item) {
        return orderedItems.get(item);
    }

    public int getBasketItemsValue() {
        int totalValueOfItems = 0;
        for (Item item : orderedItems.keySet()) {
            int numberOfItems = orderedItems.get(item);
            totalValueOfItems += item.getPrice() * numberOfItems;
        }
        return totalValueOfItems;
    }
}

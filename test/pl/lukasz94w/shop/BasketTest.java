package pl.lukasz94w.shop;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.lukasz94w.exception.BasketException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class BasketTest {
    private Basket basket;

    @BeforeEach
    void prepareCleanBasket() {
        basket = new Basket();
    }

    @Test
    void testAddingNegativeNumberOfItemsShouldThrowException() {
        assertThatThrownBy(() -> basket.addItems(-25, new Item(25.5, "Ball")))
                .isInstanceOf(BasketException.class)
                .hasMessageContaining("Number of items must be a number between 1 and 100");
    }

    @Test
    void testAdding5ItemsThenDeleteMoreToCheckIfBasketRemoveThemCompletely() {
        int penPrice = 5;
        int penNumberOfItems = 5;
        Item pen = new Item(penPrice, "Pen");

        basket.addItems(penNumberOfItems, pen);
        assertEquals(penNumberOfItems, basket.getNumberOfSpecificItem(pen));
        assertEquals(penNumberOfItems * penPrice, basket.getBasketItemsValue());

        basket.removeItems(penNumberOfItems + 100, pen);
        assertNull(basket.getNumberOfSpecificItem(pen));
    }

    @Test
    void testOfDeletingLessThanFullNumberOfItems() {
        Item bike = new Item(2500, "Romet");
        basket.addItems(20, bike);
        assertEquals(20, basket.getNumberOfSpecificItem(bike));
        basket.removeItems(19, bike);
        assertEquals(1, basket.getNumberOfSpecificItem(bike));
    }

    @Test
    void testDeletingNegativeNumberOfItems() {
        assertThatThrownBy(() -> basket.removeItems(-1, new Item(12.2, "Car")))
                .isInstanceOf(BasketException.class)
                .hasMessageContaining("Cannot delete negative number of items");
    }

    @Test
    void testDeletingItemsThatDoesntExist() {
        assertThatThrownBy(() -> basket.removeItems(25, new Item(25, "PlayStation 5")))
                .isInstanceOf(BasketException.class)
                .hasMessageContaining("Such item(s) doesn't exist in the basket");
    }

    @Test
    void checkSummingTotalValuesOfItems() {
        basket.addItems(25, new Item(100, "Bike"));
        basket.addItems(100, new Item(13, "Nigga"));
        assertEquals(25 * 100 + 100 * 13, basket.getBasketItemsValue());
        basket.removeItems(10, new Item(100, "Bike"));
        assertEquals((25 - 10) * 100 + 13 * 100, basket.getBasketItemsValue());
    }
}
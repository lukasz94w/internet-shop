package pl.lukasz94w.shop;

import org.junit.jupiter.api.Test;
import pl.lukasz94w.exception.ItemException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class ItemTest {

    @Test
    void testCreatingNewItemWithNegativePrice() {
        assertThatThrownBy(() -> new Item(-12, "Ball"))
                .isInstanceOf(ItemException.class)
                .hasMessageContaining("Price must be greater than zero");
    }

    @Test
    void testCreatingNewItemWithNoCorrectName() {
        assertThatThrownBy(() -> new Item(25, ""))
                .isInstanceOf(ItemException.class)
                .hasMessageContaining("Incorrect name");
    }

    @Test
    void testEqualsMethod() {
        // given
        Item item = new Item(25, "Computer");
        Item theSameItem = new Item(25, "Computer");
        Item notTheSameItem1 = new Item(25, "Pencil");
        Item notTheSameItem2 = new Item(12, "Computer");

        // when, then
        assertEquals(item, theSameItem);
        assertNotEquals(item, notTheSameItem1);
        assertNotEquals(item, notTheSameItem2);
        assertNotEquals(null, theSameItem);
    }
}
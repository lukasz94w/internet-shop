package pl.lukasz94w;

import pl.lukasz94w.shop.Basket;
import pl.lukasz94w.shop.Item;

public class Main {

    public static void main(String[] args) {
        Basket basket = new Basket();

        Item ball = new Item(15.0, "Ball");
        basket.addItems(2, ball);
        basket.addItems(4, ball);

        System.out.println("Total value of items: " + basket.getBasketItemsValue());
    }
}

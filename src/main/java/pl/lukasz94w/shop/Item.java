package pl.lukasz94w.shop;

import pl.lukasz94w.exception.ItemException;

public final class Item {
    private final double price;
    private final String name;

    public Item(double price, String name) {
        if (price <= 0) {
            throw new ItemException("Price must be greater than zero");
        }
        if (name.length() == 0) {
            throw new ItemException("Incorrect name");
        }
        this.price = price;
        this.name = name;
    }

    double getPrice() {
        return price;
    }

    String getName() {
        return name;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null) {
            return false;
        }
        if (object instanceof Item) {
            Item item = (Item) object;
            return item.getPrice() == price && item.getName().equals(name);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return (int) (price * 1) + name.hashCode();
    }
}

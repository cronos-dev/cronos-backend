package com.cronos.view;

import com.cronos.model.Item;

/**
 * Created by toshikijahja on 10/18/17.
 */
public class ItemView {

    private final Item item;

    public ItemView(final Item item) {
        this.item = item;
    }

    public int getId() {
        return item.getId();
    }

    public int getRestaurantId() {
        return item.getRestaurant().getId();
    }

    public String getRestaurantName() {
        return item.getRestaurant().getName();
    }

    public Item.Type getType() {
        return item.getType();
    }
}

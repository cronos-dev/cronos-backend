package com.cronos.view;

import com.cronos.model.Item;

import java.math.BigDecimal;

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

    public String getName() {
        return item.getName();
    }

    public BigDecimal getPrice() {
        return item.getPrice();
    }

    public int getRestaurantId() {
        return item.getRestaurant().getId();
    }

    public Item.Type getType() {
        return item.getType();
    }

    public boolean isEnabled() {
        return item.isEnabled();
    }

}

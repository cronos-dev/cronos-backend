package com.cronos.requestBody;

import com.cronos.model.Item;

/**
 * Created by toshikijahja on 10/18/17.
 */
public class CreateItemRequestBody {
    private String name;
    private int restaurantId;
    private Item.Type type;

    public CreateItemRequestBody() {

    }

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public int getRestaurantId() {
        return this.restaurantId;
    }

    public void setRestaurantId(final int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public Item.Type getType() {
        return this.type;
    }

    public void setType(final Item.Type type) {
        this.type = type;
    }
}

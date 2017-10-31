package com.cronos.requestBody;

import com.cronos.model.Item;

import java.math.BigDecimal;

/**
 * Created by toshikijahja on 10/18/17.
 */
public class CreateItemRequestBody {
    private String name;
    private BigDecimal price;
    private int restaurantId;
    private Item.Type type;
    private Item.Status status = Item.Status.ACTIVE;

    public CreateItemRequestBody() {

    }

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(final BigDecimal price) {
        this.price = price;
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

    public Item.Status getStatus() {
        return this.status;
    }

    public void setStatus(final Item.Status status) {
        this.status = status;
    }
}

package com.cronos.requestBody;

/**
 * Created by toshikijahja on 11/5/17.
 */
public class OrderItemRequestBody {

    private int itemId;
    private int quantity;

    public OrderItemRequestBody() {

    }

    public int getItemId() {
        return this.itemId;
    }

    public void setItemId(final int itemId) {
        this.itemId = itemId;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(final int quantity) {
        this.quantity = quantity;
    }
}

package com.cronos.requestBody;

import java.util.List;

/**
 * Created by toshikijahja on 11/5/17.
 */
public class AddOrderItemRequestBody {

    private int orderId;
    private String notes;
    private List<OrderItemRequestBody> orderItems;

    public AddOrderItemRequestBody() {

    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(final int orderId) {
        this.orderId = orderId;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(final String notes) {
        this.notes = notes;
    }

    public List<OrderItemRequestBody> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(final List<OrderItemRequestBody> orderItems) {
        this.orderItems = orderItems;
    }
}

package com.cronos.view;

import com.cronos.model.OrderItem;

/**
 * Created by toshikijahja on 11/14/17.
 */
public class OrderItemView {

    private final OrderItem orderItem;

    public OrderItemView(final OrderItem orderItem) {
        this.orderItem = orderItem;
    }

    public int getId() {
        return orderItem.getId();
    }

    public String getNotes() {
        return orderItem.getNotes();
    }

    public OrderItem.Status getStatus() {
        return orderItem.getStatus();
    }

    public ItemView getItem() {
        return new ItemView(orderItem.getItem());
    }

    public int getOrderId() {
        return orderItem.getOrder().getId();
    }
}

package com.cronos.view;

import com.cronos.model.Order;

import java.util.Date;

/**
 * Created by toshikijahja on 10/30/17.
 */
public class OrderView {

    private final Order order;

    public OrderView(final Order order) {
        this.order = order;
    }

    public int getId() {
        return order.getId();
    }

    public int getRestaurantId() {
        return order.getRestaurantId();
    }

    public int getTableId() {
        return order.getTableId();
    }

    public int getUserId() {
        return order.getUserId();
    }

    public Order.Status getStatus() {
        return order.getStatus();
    }

    public Date getOpenTime() {
        return order.getOpenTime();
    }

    public Date getCloseTime() {
        return order.getCloseTime();
    }
}

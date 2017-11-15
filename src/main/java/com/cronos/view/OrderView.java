package com.cronos.view;

import com.cronos.model.Order;
import com.cronos.model.OrderItem;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by toshikijahja on 10/30/17.
 */
public class OrderView {

    private final Order order;
    private final List<OrderItemView> orderItems;

    public OrderView(final Order order) {
        this(order, Collections.emptyList());
    }

    public OrderView(final Order order, final List<OrderItemView> orderItems) {
        this.order = order;
        this.orderItems = orderItems;
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

    public Order.Type getType() {
        return order.getType();
    }

    public Date getOpenTime() {
        return order.getOpenTime();
    }

    public Date getCloseTime() {
        return order.getCloseTime();
    }

    public List<OrderItemView> getOrderItems() {
        return orderItems;
    }

    public String getNotes() {
        return order.getNotes();
    }

    public BigDecimal getAmount() {
        return order.getAmount();
    }
}

package com.cronos.requestBody;

import com.cronos.model.Order;

import java.util.List;

/**
 * Created by toshikijahja on 10/30/17.
 */
public class StartOrderRequestBody {

    private int restaurantId;
    private int tableId;
    private int userId;
    private Order.Type type = Order.Type.DINE_IN;
    private List<OrderItemRequestBody> orderItems;
    private String notes;

    public StartOrderRequestBody() {

    }

    public int getRestaurantId() {
        return this.restaurantId;
    }

    public void setRestaurantId(final int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public int getTableId() {
        return this.tableId;
    }

    public void setTableId(final int tableId) {
        this.tableId = tableId;
    }

    public int getUserId() {
        return this.userId;
    }

    public void setUserId(final int userId) {
        this.userId = userId;
    }

    public Order.Type getType() {
        return this.type;
    }

    public void setType(final Order.Type type) {
        this.type = type;
    }

    public List<OrderItemRequestBody> getOrderItems() {
        return this.orderItems;
    }

    public void setOrderItems(final List<OrderItemRequestBody> orderItems) {
        this.orderItems = orderItems;
    }

    public String getNotes() {
        return this.notes;
    }

    public void setNotes(final String notes) {
        this.notes = notes;
    }

}

package com.cronos.requestBody;

import java.util.List;

/**
 * Created by toshikijahja on 10/30/17.
 */
public class StartOrderRequestBody {

    private int restaurantId;
    private int tableId;
    private int userId;
    private List<OrderItemRequestBody> orderItems;

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

    public List<OrderItemRequestBody> getOrderItems() {
        return this.orderItems;
    }

    public void setOrderItems(final List<OrderItemRequestBody> orderItems) {
        this.orderItems = orderItems;
    }

}

package com.cronos.requestBody;

import java.util.List;

/**
 * Created by toshikijahja on 11/5/17.
 */
public class AddOrderItemRequestBody {

    private List<OrderItemRequestBody> orderItems;

    public AddOrderItemRequestBody() {

    }

    public List<OrderItemRequestBody> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(final List<OrderItemRequestBody> orderItems) {
        this.orderItems = orderItems;
    }
}

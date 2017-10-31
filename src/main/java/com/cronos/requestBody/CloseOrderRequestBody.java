package com.cronos.requestBody;

/**
 * Created by toshikijahja on 10/30/17.
 */
public class CloseOrderRequestBody {

    private int orderId;

    public CloseOrderRequestBody() {

    }

    public int getOrderId() {
        return this.orderId;
    }

    public void setOrderId(final int orderId) {
        this.orderId = orderId;
    }
}

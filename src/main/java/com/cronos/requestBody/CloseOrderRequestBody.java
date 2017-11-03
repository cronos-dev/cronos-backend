package com.cronos.requestBody;

/**
 * Created by toshikijahja on 10/30/17.
 */
public class CloseOrderRequestBody {

    private int orderId;
    private String token;

    public CloseOrderRequestBody() {

    }

    public int getOrderId() {
        return this.orderId;
    }

    public void setOrderId(final int orderId) {
        this.orderId = orderId;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(final String token) {
        this.token = token;
    }
}

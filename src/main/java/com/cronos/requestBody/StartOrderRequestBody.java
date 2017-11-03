package com.cronos.requestBody;

/**
 * Created by toshikijahja on 10/30/17.
 */
public class StartOrderRequestBody {

    private int restaurantId;
    private int tableId;
    private int userId;

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

}

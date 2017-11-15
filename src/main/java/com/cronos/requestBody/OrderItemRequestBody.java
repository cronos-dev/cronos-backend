package com.cronos.requestBody;

/**
 * Created by toshikijahja on 11/5/17.
 */
public class OrderItemRequestBody {

    private int itemId;
    private String notes;

    public OrderItemRequestBody() {

    }

    public int getItemId() {
        return this.itemId;
    }

    public void setItemId(final int itemId) {
        this.itemId = itemId;
    }

    public String getNotes() {
        return this.notes;
    }

    public void setNotes(final String notes) {
        this.notes = notes;
    }

}

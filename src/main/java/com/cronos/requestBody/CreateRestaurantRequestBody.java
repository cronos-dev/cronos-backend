package com.cronos.requestBody;

/**
 * Created by toshikijahja on 10/18/17.
 */
public class CreateRestaurantRequestBody {
    private String name;

    public CreateRestaurantRequestBody() {

    }

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }
}

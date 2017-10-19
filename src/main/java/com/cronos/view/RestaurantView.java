package com.cronos.view;

import com.cronos.model.Restaurant;

/**
 * Created by toshikijahja on 10/18/17.
 */
public class RestaurantView {

    private final Restaurant restaurant;

    public RestaurantView(final Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public String getName() {
        return restaurant.getName();
    }
}

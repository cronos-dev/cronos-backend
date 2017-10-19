package com.cronos.dao;

import com.cronos.model.Restaurant;
import com.cronos.view.RestaurantView;

/**
 * Created by toshikijahja on 10/18/17.
 */
public class RestaurantDao extends BaseDao<Restaurant> {

    public RestaurantDao(final SessionProvider sessionProvider) {
        super(sessionProvider, Restaurant.class);
    }

    public RestaurantView addRestaurant(final String name) {
        getSessionProvider().startTransaction();
        final Restaurant restaurant = new Restaurant.Builder().name(name).build();
        getSessionProvider().getSession().save(restaurant);
        getSessionProvider().commitTransaction();
        return new RestaurantView(restaurant);
    }

}

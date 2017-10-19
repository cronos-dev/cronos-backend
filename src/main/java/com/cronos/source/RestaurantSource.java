package com.cronos.source;

import com.cronos.dao.RestaurantDao;
import com.cronos.dao.SessionProvider;
import com.cronos.model.Restaurant;
import com.cronos.requestBody.CreateRestaurantRequestBody;
import com.cronos.view.RestaurantView;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Created by toshikijahja on 10/18/17.
 */
public class RestaurantSource {

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public RestaurantView createUser(final CreateRestaurantRequestBody createRestaurantRequestBody) {
        try (final SessionProvider sessionProvider = new SessionProvider()) {
            final RestaurantDao restaurantDao = new RestaurantDao(sessionProvider);
            return restaurantDao.addRestaurant(createRestaurantRequestBody.getName());
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public RestaurantView getRestaurant(@PathParam("id") final int id) {
        try (final SessionProvider sessionProvider = new SessionProvider()) {
            final RestaurantDao restaurantDao = new RestaurantDao(sessionProvider);
            final Restaurant restaurant = restaurantDao.getById(id);
            return new RestaurantView(restaurant);
        }
    }
}

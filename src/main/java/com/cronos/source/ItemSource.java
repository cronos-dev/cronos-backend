package com.cronos.source;

import com.cronos.dao.ItemDao;
import com.cronos.dao.RestaurantDao;
import com.cronos.dao.SessionProvider;
import com.cronos.model.Item;
import com.cronos.model.Restaurant;
import com.cronos.requestBody.CreateItemRequestBody;
import com.cronos.requestBody.CreateRestaurantRequestBody;
import com.cronos.view.ItemView;
import com.cronos.view.RestaurantView;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Created by toshikijahja on 10/18/17.
 */
@Path("/item")
public class ItemSource {

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ItemView createItem(final CreateItemRequestBody createItemRequestBody) {
        try (final SessionProvider sessionProvider = new SessionProvider()) {
            final ItemDao itemDao = new ItemDao(sessionProvider);
            return itemDao.addItem(createItemRequestBody);
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public ItemView getItem(@PathParam("id") final int id) {
        try (final SessionProvider sessionProvider = new SessionProvider()) {
            final ItemDao itemDao = new ItemDao(sessionProvider);
            final Item item = itemDao.getById(id);
            return new ItemView(item);
        }
    }
}

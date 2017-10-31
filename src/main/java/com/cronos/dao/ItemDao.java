package com.cronos.dao;

import com.cronos.model.Item;
import com.cronos.model.Order;
import com.cronos.model.Restaurant;
import com.cronos.requestBody.CreateItemRequestBody;
import com.cronos.view.ItemView;

/**
 * Created by toshikijahja on 10/18/17.
 */
public class ItemDao extends BaseDao<Item> {

    private final RestaurantDao restaurantDao;

    public ItemDao(final SessionProvider sessionProvider) {
        super(sessionProvider, Item.class);
        this.restaurantDao = new RestaurantDao(getSessionProvider());
    }

    public ItemView addItem(final CreateItemRequestBody createItemRequestBody) {
        getSessionProvider().startTransaction();
        final Restaurant restaurant = restaurantDao.getById(createItemRequestBody.getRestaurantId());
        final Item item = new Item.Builder()
                .name(createItemRequestBody.getName())
                .price(createItemRequestBody.getPrice())
                .restaurant(restaurant)
                .type(createItemRequestBody.getType())
                .status(createItemRequestBody.getStatus())
                .build();
        getSessionProvider().getSession().save(item);
        getSessionProvider().commitTransaction();
        return new ItemView(item);
    }

}

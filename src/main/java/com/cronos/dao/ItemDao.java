package com.cronos.dao;

import com.cronos.model.Item;
import com.cronos.model.Restaurant;
import com.cronos.view.ItemView;

/**
 * Created by toshikijahja on 10/18/17.
 */
public class ItemDao extends BaseDao<Item> {

    public ItemDao(final SessionProvider sessionProvider) {
        super(sessionProvider, Item.class);
    }

    public ItemView addItem(final Restaurant restaurant, final String name, final Item.Type type) {
        getSessionProvider().startTransaction();
        final Item item = new Item.Builder()
                .restaurant(restaurant)
                .type(type)
                .build();
        getSessionProvider().getSession().save(item);
        getSessionProvider().commitTransaction();
        return new ItemView(item);
    }

}


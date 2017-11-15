package com.cronos.dao;

import com.cronos.model.Item;
import com.cronos.model.Order;
import com.cronos.model.Restaurant;
import com.cronos.requestBody.CreateItemRequestBody;
import com.cronos.view.ItemView;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import java.util.List;
import java.util.stream.Collectors;

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
                .enabled(createItemRequestBody.isEnabled())
                .build();
        getSessionProvider().getSession().save(item);
        getSessionProvider().commitTransaction();
        return new ItemView(item);
    }

    public List<ItemView> getByRestaurantIdEnabledType(final int restaurantId,
                                                       final Boolean enabled,
                                                       final List<Item.Type> types) {
        final Criteria criteria = getSessionProvider().getSession().createCriteria(Item.class);
        criteria.createAlias("restaurant", "restaurant");
        criteria.add(Restrictions.eq("restaurant.id", restaurantId));
        if (null != enabled) {
            criteria.add(Restrictions.eq("enabled", enabled));
        }
        if (!types.isEmpty()) {
            criteria.add(Restrictions.in("type", types));
        }
        @SuppressWarnings("unchecked")
        final List<Item> items = (List<Item>) criteria.list();
        return items.stream().map(ItemView::new).collect(Collectors.toList());
    }

}

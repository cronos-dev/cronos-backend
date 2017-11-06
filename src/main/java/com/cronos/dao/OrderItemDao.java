package com.cronos.dao;

import com.cronos.model.Item;
import com.cronos.model.Order;
import com.cronos.model.OrderItem;
import com.cronos.requestBody.OrderItemRequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by toshikijahja on 11/3/17.
 */
public class OrderItemDao extends BaseDao<OrderItem> {

    private final ItemDao itemDao;

    public OrderItemDao(final SessionProvider sessionProvider) {
        super(sessionProvider, OrderItem.class);
        this.itemDao = new ItemDao(sessionProvider);
    }

    public List<OrderItem> getByOrderId(final Integer orderId) {
        return getByField("order.id", orderId);
    }

    public List<OrderItem> addOrderItem(final Order order, final List<OrderItemRequestBody> orderItemRequestBodies) {
        getSessionProvider().startTransaction();

        final List<Integer> itemIds = orderItemRequestBodies.stream()
                .map(OrderItemRequestBody::getItemId).collect(Collectors.toList());
        final List<Item> items = itemDao.getByIds(itemIds);
        final Map<Integer, Item> itemIdToItemMap = items.stream().collect(Collectors.toMap(Item::getId, Function.identity()));

        final List<OrderItem> orderItems = new ArrayList<>();
        orderItemRequestBodies.forEach(oi -> {
            final OrderItem orderItem = new OrderItem.Builder()
                    .item(itemIdToItemMap.get(oi.getItemId()))
                    .order(order)
                    .quantity(oi.getQuantity())
                    .build();
            getSessionProvider().getSession().save(orderItem);
        });

        getSessionProvider().commitTransaction();
        return orderItems;
    }

}

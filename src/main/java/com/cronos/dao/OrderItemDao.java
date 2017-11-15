package com.cronos.dao;

import com.cronos.model.Item;
import com.cronos.model.Order;
import com.cronos.model.OrderItem;
import com.cronos.requestBody.OrderItemRequestBody;
import com.cronos.view.OrderItemView;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.Collections;
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

    public List<OrderItemView> getByOrderId(final Integer orderId) {
        return getByOrderIds(Collections.singletonList(orderId));
    }

    public List<OrderItemView> getByOrderIds(final List<Integer> orderIds) {
        final Criteria criteria = getSessionProvider().getSession().createCriteria(OrderItem.class);
        criteria.createAlias("order", "order");
        criteria.add(Restrictions.in("order.id", orderIds));
        @SuppressWarnings("unchecked")
        final List<OrderItem> orderItems = (List<OrderItem>) criteria.list();
        return orderItems.stream().map(OrderItemView::new).collect(Collectors.toList());
    }

    public List<OrderItemView> addOrderItem(final Order order, final List<OrderItemRequestBody> orderItemRequestBodies) {
        if (orderItemRequestBodies.isEmpty()) {
            return Collections.emptyList();
        }

        getSessionProvider().startTransaction();

        final List<Integer> itemIds = orderItemRequestBodies.stream()
                .map(OrderItemRequestBody::getItemId).collect(Collectors.toList());
        final List<Item> items = itemDao.getByIds(itemIds);
        final Map<Integer, Item> itemIdToItemMap = items.stream().collect(Collectors.toMap(Item::getId, Function.identity()));

        orderItemRequestBodies.forEach(oi -> {
            final OrderItem orderItem = new OrderItem.Builder()
                    .item(itemIdToItemMap.get(oi.getItemId()))
                    .order(order)
                    .notes(oi.getNotes())
                    .status(OrderItem.Status.ORDERED)
                    .build();
            getSessionProvider().getSession().save(orderItem);
        });

        getSessionProvider().commitTransaction();


        return getByOrderId(order.getId());
    }

}

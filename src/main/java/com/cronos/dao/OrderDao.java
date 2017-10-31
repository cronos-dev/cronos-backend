package com.cronos.dao;

import com.cronos.model.Order;
import com.cronos.requestBody.CloseOrderRequestBody;
import com.cronos.requestBody.StartOrderRequestBody;
import com.cronos.view.OrderView;

import java.util.Date;
import java.util.HashSet;

/**
 * Created by toshikijahja on 10/30/17.
 */
public class OrderDao extends BaseDao<Order> {


    public OrderDao(final SessionProvider sessionProvider) {
        super(sessionProvider, Order.class);
    }

    public OrderView startOrder(final StartOrderRequestBody startOrderRequestBody) {
        getSessionProvider().startTransaction();
        final Order order = new Order.Builder()
                .restaurantId(startOrderRequestBody.getRestaurantId())
                .tableId(startOrderRequestBody.getTableId())
                .userId(startOrderRequestBody.getUserId())
                .status(Order.Status.OPEN)
                .openTime(new Date())
                .build();
        getSessionProvider().getSession().save(order);
        getSessionProvider().commitTransaction();
        return new OrderView(order);
    }

    public OrderView closeOrder(final CloseOrderRequestBody closeOrderRequestBody) {
        getSessionProvider().startTransaction();
        final Order order = getById(closeOrderRequestBody.getOrderId());
        order.setCloseTime(new Date());
        getSessionProvider().commitTransaction();
        return new OrderView(order);
    }

}


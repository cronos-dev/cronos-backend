package com.cronos.dao;

import com.cronos.model.Order;
import com.cronos.model.OrderItem;
import com.cronos.model.Restaurant;
import com.cronos.model.Transaction;
import com.cronos.requestBody.CloseOrderRequestBody;
import com.cronos.requestBody.StartOrderRequestBody;
import com.cronos.service.StripeService;
import com.cronos.view.OrderView;
import com.stripe.Stripe;
import com.stripe.exception.*;

import javax.persistence.Query;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import static com.cronos.Constants.DATA;
import static com.cronos.model.Order.Status.CLOSED;
import static com.cronos.model.Order.Status.OPEN;
import static com.cronos.model.Transaction.Status.COMPLETED;
import static com.cronos.model.Transaction.Status.FAILED;

/**
 * Created by toshikijahja on 10/30/17.
 */
public class OrderDao extends BaseDao<Order> {

    private final StripeService stripeService;
    private final OrderItemDao orderItemDao;
    private final RestaurantDao restaurantDao;

    public OrderDao(final SessionProvider sessionProvider) {
        super(sessionProvider, Order.class);
        stripeService = new StripeService();
        orderItemDao = new OrderItemDao(sessionProvider);
        restaurantDao = new RestaurantDao(sessionProvider);
    }

    public OrderView startOrder(final StartOrderRequestBody startOrderRequestBody) {
        getSessionProvider().startTransaction();
        final Order order = new Order.Builder()
                .restaurantId(startOrderRequestBody.getRestaurantId())
                .tableId(startOrderRequestBody.getTableId())
                .userId(startOrderRequestBody.getUserId())
                .status(OPEN)
                .openTime(new Date())
                .build();
        getSessionProvider().getSession().save(order);
        getSessionProvider().commitTransaction();
        return new OrderView(order);
    }

    public OrderView closeOrder(final CloseOrderRequestBody closeOrderRequestBody) {
        getSessionProvider().startTransaction();
        final Order order = getById(closeOrderRequestBody.getOrderId());
        final Restaurant restaurant = restaurantDao.getById(order.getRestaurantId());
        final BigDecimal amount = orderItemDao.calculateTotalPrice(order.getId());
        final Transaction transaction = new Transaction.Builder()
                .amount(amount)
                .userId(order.getUserId())
                .orderId(order.getId())
                .build();

        try {
            final String chargeId = stripeService.charge(closeOrderRequestBody.getToken(), String.valueOf(amount), restaurant.getName());
            transaction.setChargeId(chargeId);
            transaction.setStatus(COMPLETED);
            order.setCloseTime(new Date());
            order.setStatus(CLOSED);
        } catch (APIConnectionException | AuthenticationException | InvalidRequestException | APIException | CardException e) {
            e.printStackTrace();
            order.setCloseTime(null);
            order.setStatus(OPEN);
            transaction.setStatus(FAILED);
        }

        getSessionProvider().getSession().save(transaction);
        getSessionProvider().commitTransaction();
        return new OrderView(order);
    }



}


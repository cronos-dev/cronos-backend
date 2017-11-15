package com.cronos.dao;

import com.cronos.model.Order;
import com.cronos.model.OrderItem;
import com.cronos.model.Restaurant;
import com.cronos.model.Transaction;
import com.cronos.requestBody.CloseOrderRequestBody;
import com.cronos.requestBody.StartOrderRequestBody;
import com.cronos.service.StripeService;
import com.cronos.view.OrderItemView;
import com.cronos.view.OrderView;
import com.stripe.Stripe;
import com.stripe.exception.*;

import javax.persistence.Query;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    private final TransactionDao transactionDao;

    public OrderDao(final SessionProvider sessionProvider) {
        super(sessionProvider, Order.class);
        stripeService = new StripeService();
        orderItemDao = new OrderItemDao(sessionProvider);
        restaurantDao = new RestaurantDao(sessionProvider);
        transactionDao = new TransactionDao(sessionProvider);
    }

    public OrderView startOrder(final StartOrderRequestBody startOrderRequestBody) {
        getSessionProvider().startTransaction();
        final Order order = new Order.Builder()
                .restaurantId(startOrderRequestBody.getRestaurantId())
                .tableId(startOrderRequestBody.getTableId())
                .userId(startOrderRequestBody.getUserId())
                .type(startOrderRequestBody.getType())
                .notes(startOrderRequestBody.getNotes())
                .status(OPEN)
                .openTime(new Date())
                .build();
        getSessionProvider().getSession().save(order);
        final List<OrderItemView> orderItems = orderItemDao.addOrderItem(order, startOrderRequestBody.getOrderItems());
        order.setAmount(calculateAmount(orderItems));
        getSessionProvider().commitTransaction();
        return new OrderView(order, orderItems);
    }

    public OrderView closeOrder(final CloseOrderRequestBody closeOrderRequestBody) {
        getSessionProvider().startTransaction();
        final Order order = getById(closeOrderRequestBody.getOrderId());
        final Restaurant restaurant = restaurantDao.getById(order.getRestaurantId());
        final List<OrderItemView> orderItems = orderItemDao.getByOrderId(order.getId());
        final BigDecimal amount = calculateAmount(orderItems);
        order.setAmount(amount);
        final Transaction transaction = new Transaction.Builder()
                .amount(amount)
                .userId(order.getUserId())
                .orderId(order.getId())
                .build();

        try {
            final String description = transactionDao.buildDescription(restaurant.getName(), amount, order.getId());
//            final String chargeId = stripeService.charge(closeOrderRequestBody.getToken(), String.valueOf(amount), description);
            final String chargeId = "chargeId";
            transaction.setChargeId(chargeId);
            transaction.setStatus(COMPLETED);
            order.setCloseTime(new Date());
            order.setStatus(CLOSED);
            order.setAmount(amount);
//        } catch (APIConnectionException | AuthenticationException | InvalidRequestException | APIException | CardException e) {
//            e.printStackTrace();
//            order.setCloseTime(null);
//            order.setStatus(OPEN);
//            transaction.setStatus(FAILED);
        } catch (final Exception e) {}

        getSessionProvider().getSession().save(transaction);
        getSessionProvider().commitTransaction();
        return new OrderView(order, orderItems);
    }

    public Order addNotes(final int orderId, final String notes) {
        getSessionProvider().startTransaction();
        final Order order = getById(orderId);
        order.setNotes(notes);
        getSessionProvider().commitTransaction();
        return order;
    }

    public Order updateAmount(final int orderId, final List<OrderItemView> orderItems) {
        getSessionProvider().startTransaction();
        final Order order = getById(orderId);
        order.setAmount(calculateAmount(orderItems));
        getSessionProvider().commitTransaction();
        return order;
    }

    private BigDecimal calculateAmount(final List<OrderItemView> orderItems) {
        return orderItems.stream()
                .filter(orderItem -> OrderItem.Status.CANCELED != orderItem.getStatus())
                .map(orderItem -> orderItem.getItem().getPrice())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public List<OrderView> getByUserId(final int userId) {
        final List<Order> orders = getByField("userId", userId);
        final List<Integer> orderIds = orders.stream().map(Order::getId).collect(Collectors.toList());
        final List<OrderItemView> orderItems = orderItemDao.getByOrderIds(orderIds);
        final Map<Integer, List<OrderItemView>> orderIdToOrderItems = orderItems.stream()
                .collect(Collectors.groupingBy(OrderItemView::getOrderId));
        return orders.stream()
                .map(order -> new OrderView(order, orderIdToOrderItems.get(order.getId())))
                .collect(Collectors.toList());
    }

}


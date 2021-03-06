package com.cronos.source;

import com.cronos.dao.OrderDao;
import com.cronos.dao.OrderItemDao;
import com.cronos.dao.SessionProvider;
import com.cronos.model.Order;
import com.cronos.model.OrderItem;
import com.cronos.requestBody.AddOrderItemRequestBody;
import com.cronos.requestBody.CloseOrderRequestBody;
import com.cronos.requestBody.StartOrderRequestBody;
import com.cronos.view.OrderItemView;
import com.cronos.view.OrderView;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by toshikijahja on 10/30/17.
 */
@Path("/order")
public class OrderSource {

    @POST
    @Path("/start")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public OrderView startOrder(final StartOrderRequestBody startOrderRequestBody) {
        try (final SessionProvider sessionProvider = new SessionProvider()) {
            final OrderDao orderDao = new OrderDao(sessionProvider);
            return orderDao.startOrder(startOrderRequestBody);
        }
    }

    @POST
    @Path("/close")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public OrderView closeOrder(final CloseOrderRequestBody closeOrderRequestBody) {
        try (final SessionProvider sessionProvider = new SessionProvider()) {
            final OrderDao orderDao = new OrderDao(sessionProvider);
            return orderDao.closeOrder(closeOrderRequestBody);
        }
    }

    @POST
    @Path("/add")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public OrderView addOrder(final AddOrderItemRequestBody addOrderItemRequestBody) {
        try (final SessionProvider sessionProvider = new SessionProvider()) {
            final OrderDao orderDao = new OrderDao(sessionProvider);
            final OrderItemDao orderItemDao = new OrderItemDao(sessionProvider);
            final Order order = orderDao.addNotes(addOrderItemRequestBody.getOrderId(), addOrderItemRequestBody.getNotes());
            final List<OrderItemView> orderItems = orderItemDao.addOrderItem(order, addOrderItemRequestBody.getOrderItems());
            final Order updatedOrder = orderDao.updateAmount(addOrderItemRequestBody.getOrderId(), orderItems);
            return new OrderView(updatedOrder, orderItems);
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public OrderView getOrder(@PathParam("id") final int id) {
        try (final SessionProvider sessionProvider = new SessionProvider()) {
            final OrderDao orderDao = new OrderDao(sessionProvider);
            final OrderItemDao orderItemDao = new OrderItemDao(sessionProvider);
            final Order order= orderDao.getById(id);
            final List<OrderItemView> orderItems = orderItemDao.getByOrderId(order.getId());
            return new OrderView(order, orderItems);
        }
    }

    @GET
    @Path("/history")
    @Produces(MediaType.APPLICATION_JSON)
    public List<OrderView> getOrderHistory(@QueryParam("userId") final int userId) {
        try (final SessionProvider sessionProvider = new SessionProvider()) {
            final OrderDao orderDao = new OrderDao(sessionProvider);
            return orderDao.getByUserId(userId);
        }
    }
}

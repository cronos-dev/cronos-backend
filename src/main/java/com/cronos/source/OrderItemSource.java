package com.cronos.source;

import com.cronos.dao.OrderDao;
import com.cronos.dao.OrderItemDao;
import com.cronos.dao.SessionProvider;
import com.cronos.model.Order;
import com.cronos.model.OrderItem;
import com.cronos.requestBody.AddOrderItemRequestBody;
import com.cronos.requestBody.OrderItemRequestBody;
import com.owlike.genson.Genson;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by toshikijahja on 11/3/17.
 */
@Path("/orderItem")
public class OrderItemSource {

    @POST
    @Path("/{orderId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<OrderItem> addOrderItem(@PathParam("orderId") final int orderId,
                                        final AddOrderItemRequestBody addOrderItemRequestBody) {
        try (final SessionProvider sessionProvider = new SessionProvider()) {
            final OrderDao orderDao = new OrderDao(sessionProvider);
            final OrderItemDao orderItemDao = new OrderItemDao(sessionProvider);
            final Order order = orderDao.getById(orderId);
            return orderItemDao.addOrderItem(order, addOrderItemRequestBody.getOrderItems());
        }
    }

    @GET
    @Path("/{orderId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<OrderItem> getOrderItem(@PathParam("orderId") final int orderId) {
        try (final SessionProvider sessionProvider = new SessionProvider()) {
            final OrderItemDao orderItemDao = new OrderItemDao(sessionProvider);
            return orderItemDao.getByOrderId(orderId);
        }
    }
}

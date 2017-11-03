package com.cronos.source;

import com.cronos.dao.OrderItemDao;
import com.cronos.dao.SessionProvider;
import com.cronos.model.OrderItem;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by toshikijahja on 11/3/17.
 */
@Path("/orderItem")
public class OrderItemSource {

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

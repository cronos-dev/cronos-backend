package com.cronos.source;

import com.cronos.dao.OrderDao;
import com.cronos.dao.SessionProvider;
import com.cronos.model.Order;
import com.cronos.requestBody.CloseOrderRequestBody;
import com.cronos.requestBody.StartOrderRequestBody;
import com.cronos.view.OrderView;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

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

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public OrderView getOrder(@PathParam("id") final int id) {
        try (final SessionProvider sessionProvider = new SessionProvider()) {
            final OrderDao orderDao = new OrderDao(sessionProvider);
            final Order order= orderDao.getById(id);
            return new OrderView(order);
        }
    }
}

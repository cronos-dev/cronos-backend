package com.cronos.dao;

import com.cronos.model.OrderItem;

import javax.persistence.Query;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by toshikijahja on 11/3/17.
 */
public class OrderItemDao extends BaseDao<OrderItem> {

    public OrderItemDao(final SessionProvider sessionProvider) {
        super(sessionProvider, OrderItem.class);
    }

    public List<OrderItem> getByOrderId(final int orderId) {
        return getByField("orderId", orderId);
    }

    public BigDecimal calculateTotalPrice(final int orderId) {
        final Query query = getSessionProvider().getSession().createQuery(
                "SELECT SUM(oi.quantity * i.price) " +
                        "FROM OrderItem oi " +
                        "INNER JOIN Item i on oi.itemId = i.id " +
                        "WHERE oi.orderId = :orderId");
        query.setParameter("orderId", orderId);
        return (BigDecimal) query.getResultList().get(0);
    }
}

package com.cronos.model;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;

/**
 * Created by toshikijahja on 10/31/17.
 */
@Embeddable
public class OrderItemId implements Serializable {

    @ManyToOne
    private Order order;

    @ManyToOne
    private Item item;

    public Order getOrder() {
        return order;
    }

    public void setOrder(final Order order) {
        this.order = order;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(final Item item) {
        this.item = item;
    }

}

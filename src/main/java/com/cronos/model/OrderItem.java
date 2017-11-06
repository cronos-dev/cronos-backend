package com.cronos.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by toshikijahja on 10/31/17.
 */
@Entity
@Table(name = "`OrderItem`")
public class OrderItem implements Serializable {

    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "orderId")
    private Order order;

    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "itemId")
    private Item item;

    @Column
    private int quantity;

    public OrderItem() {

    }

    public OrderItem(final Builder builder) {
        setOrder(builder.order);
        setItem(builder.item);
        setQuantity(builder.quantity);
    }

    public Order getOrder() {
        return this.order;
    }

    public void setOrder(final Order order) {
        this.order = order;
    }

    public Item getItem() {
        return this.item;
    }

    public void setItem(final Item item) {
        this.item = item;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(final int quantity) {
        this.quantity = quantity;
    }

    public void addQuantity() {
        this.quantity += 1;
    }

    public void removeQuantity() {
        if (this.quantity > 0) {
            this.quantity -= 1;
        }
    }

    public static class Builder {
        private Order order;
        private Item item;
        private int quantity = 1;

        public Builder order(final Order order) {
            this.order = order;
            return this;
        }

        public Builder item(final Item item) {
            this.item = item;
            return this;
        }

        public Builder quantity(final int quantity) {
            this.quantity = quantity;
            return this;
        }

        public OrderItem build() {
            return new OrderItem(this);
        }
    }

}


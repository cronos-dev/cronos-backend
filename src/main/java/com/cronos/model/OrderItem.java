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
    @GeneratedValue
    @Column
    private int id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "orderId")
    private Order order;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "itemId")
    private Item item;

    @Column(columnDefinition = "text")
    private String notes;

    @Column
    @Enumerated(EnumType.STRING)
    private Status status;

    public enum Status {
        ORDERED,
        COOKING,
        SERVED,
        CANCELED
    }

    public OrderItem() {

    }

    public OrderItem(final Builder builder) {
        setOrder(builder.order);
        setItem(builder.item);
        setNotes(builder.notes);
        setStatus(builder.status);
    }

    public int getId() {
        return this.id;
    }

    public void setId(final int id) {
        this.id = id;
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

    public String getNotes() {
        return this.notes;
    }

    public void setNotes(final String notes) {
        this.notes = notes;
    }

    public Status getStatus() {
        return this.status;
    }

    public void setStatus(final Status status) {
        this.status = status;
    }

    public static class Builder {
        private Order order;
        private Item item;
        private String notes;
        private Status status;

        public Builder order(final Order order) {
            this.order = order;
            return this;
        }

        public Builder item(final Item item) {
            this.item = item;
            return this;
        }

        public Builder notes(final String notes) {
            this.notes = notes;
            return this;
        }

        public Builder status(final Status status) {
            this.status = status;
            return this;
        }

        public OrderItem build() {
            return new OrderItem(this);
        }
    }

}


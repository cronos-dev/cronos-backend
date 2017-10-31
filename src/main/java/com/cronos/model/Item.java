package com.cronos.model;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import static com.cronos.model.Item.Status.ACTIVE;

/**
 * Created by toshikijahja on 7/29/17.
 */
@Entity
public class Item implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private BigDecimal price;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "restaurantId", foreignKey = @ForeignKey(name = "FK_RESTAURANT"))
    private Restaurant restaurant;

    @Column
    @Enumerated(EnumType.STRING)
    private Type type;

    @Column
    @Enumerated(EnumType.STRING)
    private Status status = ACTIVE;

    @OneToMany(mappedBy = "pk.item")
    private Set<OrderItem> orderItems = new HashSet<OrderItem>();

    public enum Type {
        APPETIZER,
        MAIN,
        DESSERT,
        BEVERAGE
    }

    public enum Status {
        ACTIVE,
        INACTIVE
    }

    public Item() {

    }

    public Item(final Builder builder) {
        setName(builder.name);
        setPrice(builder.price);
        setRestaurant(builder.restaurant);
        setType(builder.type);
        setStatus(builder.status);
    }

    public int getId() {
        return this.id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(final BigDecimal price) {
        this.price = price;
    }

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Restaurant getRestaurant() {
        return this.restaurant;
    }

    public void setRestaurant(final Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Type getType() {
        return type;
    }

    public void setType(final Type type) {
        this.type = type;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(final Status status) {
        this.status = status;
    }

    public Set<OrderItem> getOrderItems() {
        return this.orderItems;
    }

    public void setOrderItem(final Set<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public static class Builder {
        private String name;
        private BigDecimal price;
        private Restaurant restaurant;
        private Type type;
        private Status status;

        public Builder name(final String name) {
            this.name = name;
            return this;
        }

        public Builder price(final BigDecimal price) {
            this.price = price;
            return this;
        }

        public Builder restaurant(final Restaurant restaurant) {
            this.restaurant = restaurant;
            return this;
        }

        public Builder type(final Type type) {
            this.type = type;
            return this;
        }

        public Builder status(final Status status) {
            this.status = status;
            return this;
        }

        public Item build() {
            return new Item(this);
        }
    }
}

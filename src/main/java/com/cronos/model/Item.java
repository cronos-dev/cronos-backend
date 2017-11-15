package com.cronos.model;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

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
    @JoinColumn(name = "restaurantId")
    private Restaurant restaurant;

    @Column
    @Enumerated(EnumType.STRING)
    private Type type;

    @Column
    private boolean enabled;

    @OneToMany(mappedBy = "item")
    private Set<OrderItem> orderItems = new HashSet<OrderItem>();

    public enum Type {
        APPETIZER,
        MAIN,
        DESSERT,
        BEVERAGE
    }

    public Item() {

    }

    public Item(final Builder builder) {
        setName(builder.name);
        setPrice(builder.price);
        setRestaurant(builder.restaurant);
        setType(builder.type);
        setEnabled(builder.enabled);
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

    public boolean isEnabled() {
        return this.enabled;
    }

    public void setEnabled(final boolean enabled) {
        this.enabled = enabled;
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
        private boolean enabled;

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

        public Builder enabled(final boolean enabled) {
            this.enabled = enabled;
            return this;
        }

        public Item build() {
            return new Item(this);
        }
    }
}

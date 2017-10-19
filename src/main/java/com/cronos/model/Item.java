package com.cronos.model;

import javax.persistence.*;

/**
 * Created by toshikijahja on 7/29/17.
 */
@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String name;

    @ManyToOne
    @JoinColumn(name="restaurantId")
    private Restaurant restaurant;

    @Column
    @Enumerated(EnumType.STRING)
    private Type type;

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
        setRestaurant(builder.restaurant);
        setType(builder.type);
    }

    public int getId() {
        return this.id;
    }

    public void setId(final int id) {
        this.id = id;
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

    public static class Builder {
        private String name;
        private Restaurant restaurant;
        private Type type;

        public Builder name(final String name) {
            this.name = name;
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

        public Item build() {
            return new Item(this);
        }
    }
}

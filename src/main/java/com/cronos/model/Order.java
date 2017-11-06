package com.cronos.model;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by toshikijahja on 10/30/17.
 */
@Entity
@Table(name = "`Order`")
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private int restaurantId;

    @Column
    private int tableId;

    @Column
    private int userId;

    @Column
    private BigDecimal amount;

    @Column
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date openTime = null;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date closeTime = null;

    @OneToMany(mappedBy = "order")
    private Set<OrderItem> orderItems = new HashSet<>();

    public enum Status {
        OPEN,
        CLOSED
    }

    public Order() {

    }

    public Order(final Builder builder) {
        setRestaurantId(builder.restaurantId);
        setTableId(builder.tableId);
        setUserId(builder.userId);
        setAmount(builder.amount);
        setStatus(builder.status);
        setOpenTime(builder.openTime);
        setCloseTime(builder.closeTime);
        setOrderItem(builder.orderItems);
    }

    public int getId() {
        return this.id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public int getRestaurantId() {
        return this.restaurantId;
    }

    public void setRestaurantId(final int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public int getTableId() {
        return this.tableId;
    }

    public void setTableId(final int tableId) {
        this.tableId = tableId;
    }

    public int getUserId() {
        return this.userId;
    }

    public void setUserId(final int userId) {
        this.userId = userId;
    }

    public BigDecimal getAmount() {
        return this.amount;
    }

    public void setAmount(final BigDecimal amount) {
        this.amount = amount;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(final Status status) {
        this.status = status;
    }

    public Date getOpenTime() {
        return this.openTime;
    }

    public void setOpenTime(final Date openTime) {
        this.openTime = openTime;
    }

    public Date getCloseTime() {
        return this.closeTime;
    }

    public void setCloseTime(final Date closeTime) {
        this.closeTime = closeTime;
    }

    public Set<OrderItem> getOrderItems() {
        return this.orderItems;
    }

    public void setOrderItem(final Set<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public static class Builder {
        private int restaurantId;
        private int tableId;
        private int userId;
        private BigDecimal amount = BigDecimal.ZERO;
        private Status status;
        private Date openTime = new Date();
        private Date closeTime;
        private Set<OrderItem> orderItems = new HashSet<>();

        public Builder restaurantId(final int restaurantId) {
            this.restaurantId = restaurantId;
            return this;
        }

        public Builder tableId(final int tableId) {
            this.tableId = tableId;
            return this;
        }

        public Builder userId(final int userId) {
            this.userId = userId;
            return this;
        }

        public Builder amount(final BigDecimal amount) {
            this.amount = amount;
            return this;
        }

        public Builder status(final Status status) {
            this.status = status;
            return this;
        }

        public Builder openTime(final Date openTime) {
            this.openTime = openTime;
            return this;
        }

        public Builder closeTime(final Date closeTime) {
            this.closeTime = closeTime;
            return this;
        }

        public Builder orderItems(final Set<OrderItem> orderItems) {
            this.orderItems = orderItems;
            return this;
        }

        public Order build() {
            return new Order(this);
        }
    }

}

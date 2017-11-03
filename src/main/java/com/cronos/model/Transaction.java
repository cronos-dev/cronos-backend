package com.cronos.model;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by toshikijahja on 11/2/17.
 */
@Entity
public class Transaction {

    @Id
    private int orderId;

    @Column
    private String chargeId;

    @Column
    private int userId;

    @Column
    private BigDecimal amount;

    @Column
    @Enumerated(EnumType.STRING)
    private Status status;

    public enum Status {
        COMPLETED,
        FAILED,
    }

    public Transaction() {

    }

    public Transaction(final Builder builder) {
        setOrderId(builder.orderId);
        setChargeId(builder.chargeId);
        setUserId(builder.userId);
        setAmount(builder.amount);
        setStatus(builder.status);
    }

    public int getOrderId() {
        return this.orderId;
    }

    public void setOrderId(final int orderId) {
        this.orderId = orderId;
    }

    public String getChargeId() {
        return this.chargeId;
    }

    public void setChargeId(final String chargeId) {
        this.chargeId = chargeId;
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
        return this.status;
    }

    public void setStatus(final Status status) {
        this.status = status;
    }

    public static class Builder {
        private int orderId;
        private String chargeId;
        private int userId;
        private BigDecimal amount;
        private Status status;

        public Builder orderId(final int orderId) {
            this.orderId = orderId;
            return this;
        }

        public Builder chargeId(final String chargeId) {
            this.chargeId = chargeId;
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

        public Transaction build() {
            return new Transaction(this);
        }
    }
}

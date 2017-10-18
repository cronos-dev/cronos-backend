package com.cronos.model;

import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.sql.Date;

/**
 * Created by toshikijahja on 7/29/17.
 */
@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private int userId;

    @Column
    private Integer linerId;

    @Column
    private Status status;

    @Column (nullable = false)
    private String address;

    @Column (nullable = false)
    private String city;

    @Column (nullable = false)
    private BigDecimal latitude;

    @Column (nullable = false)
    private BigDecimal longitude;

    @Column (nullable = false)
    @Type(type="timestamp")
    private Date requestStartTime;

    @Column
    @Type(type="timestamp")
    private Date startTime;

    @Column
    @Type(type="timestamp")
    private Date updateTime;

    @Column
    @Type(type="timestamp")
    private Date completedTime;

    public enum Status {
        INACTIVE,
        ACTIVE,
        COMPLETED;
    }

    public Task() {

    }

    public int getId() {
        return this.id;
    }

    public void setId() {
        this.id = id;
    }

    public int getUserId() {
        return this.userId;
    }

    public void setUserId(final int userId) {
        this.userId = userId;
    }

    public Integer getLinerId() {
        return this.linerId;
    }

    public void setLinerId(final Integer linerId) {
        this.linerId = linerId;
    }

    public Status getStatus() {
        return this.status;
    }

    public void setStatus(final Status status) {
        this.status = status;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(final String address) {
        this.address = address;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(final String city) {
        this.city = city;
    }

    public BigDecimal getLatitude() {
        return this.latitude;
    }

    public void setLatitude(final BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return this.longitude;
    }

    public void setLongitude(final BigDecimal longitude) {
        this.longitude = longitude;
    }

    public Date getRequestStartTime() {
        return this.requestStartTime;
    }

    public void setRequestStartTime(final Date requestStartTime) {
        this.requestStartTime = requestStartTime;
    }

    public Date getStartTime() {
        return this.startTime;
    }

    public void setStartTime(final Date startTime) {
        this.startTime = startTime;
    }

    public Date getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(final Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getCompletedTime() {
        return this.completedTime;
    }

    public void setCompletedTime(final Date completedTime) {
        this.completedTime = completedTime;
    }
}

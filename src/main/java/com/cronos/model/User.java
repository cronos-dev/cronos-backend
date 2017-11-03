package com.cronos.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String email;

    @Column
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column (nullable = false)
    private String stripeCustomerId;

    public enum Gender {
        MALE, FEMALE;
    }

    public User() {

    }

    public User(final Builder builder) {
        setFirstName(builder.firstName);
        setLastName(builder.lastName);
        setEmail(builder.email);
        setGender(builder.gender);
        setStripeCustomerId(builder.stripeCustomerId);
    }

    public int getId() {
        return this.id;
    }

    public void setId() {
        this.id = id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public Gender getGender() {
        return this.gender;
    }

    public void setGender(final Gender gender) {
        this.gender = gender;
    }

    public String getStripeCustomerId() {
        return this.stripeCustomerId;
    }

    public void setStripeCustomerId(final String stripeCustomerId) {
        this.stripeCustomerId = stripeCustomerId;
    }

    public static class Builder {
        private String firstName;
        private String lastName;
        private String email;
        private Gender gender;
        private String stripeCustomerId;

        public Builder firstName(final String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder lastName(final String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder email(final String email) {
            this.email = email;
            return this;
        }

        public Builder gender(final Gender gender) {
            this.gender = gender;
            return this;
        }

        public Builder stripeCustomerId(final String stripeCustomerId) {
            this.stripeCustomerId = stripeCustomerId;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}
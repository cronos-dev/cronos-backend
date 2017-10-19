package com.cronos.model;

import javax.persistence.*;

/**
 * Created by toshikijahja on 10/18/17.
 */
@Entity
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

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

    public Restaurant() {

    }

    public Restaurant(final Builder builder) {
        setName(builder.name);
    }

    public static class Builder {
        private String name;

        public Builder name(final String name) {
            this.name = name;
            return this;
        }

        public Restaurant build() {
            return new Restaurant(this);
        }
    }
}

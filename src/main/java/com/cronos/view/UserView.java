package com.cronos.view;

import com.cronos.model.User;

/**
 * Created by toshikijahja on 10/18/17.
 */
public class UserView {

    private final User user;

    public UserView(final User user) {
        this.user = user;
    }

    public String getFirstName() {
        return user.getFirstName();
    }

    public String getLastName() {
        return user.getLastName();
    }

}

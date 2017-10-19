package com.cronos.dao;

import com.cronos.model.User;
import com.cronos.view.UserView;

import java.sql.Date;

/**
 * Created by toshikijahja on 6/7/17.
 */
public class UserDao extends BaseDao<User> {

    public UserDao(final SessionProvider sessionProvider) {
        super(sessionProvider, User.class);
    }

    public UserView addUser(final String firstName, final String lastName, final String email,
                        final long birthday, final String gender) {
        getSessionProvider().startTransaction();
        final User user = new User.Builder()
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .birthday(new Date(birthday))
                .gender(User.Gender.valueOf(gender))
                .build();
        getSessionProvider().getSession().save(user);
        getSessionProvider().commitTransaction();
        return new UserView(user);
    }
}

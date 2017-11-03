package com.cronos.dao;

import com.cronos.model.User;
import com.cronos.requestBody.CreateUserRequestBody;
import com.cronos.view.UserView;

import java.sql.Date;

/**
 * Created by toshikijahja on 6/7/17.
 */
public class UserDao extends BaseDao<User> {

    public UserDao(final SessionProvider sessionProvider) {
        super(sessionProvider, User.class);
    }

    public UserView createUser(final CreateUserRequestBody createUserRequestBody,
                               final String stripeCustomerId) {
        getSessionProvider().startTransaction();
        final User user = new User.Builder()
                .firstName(createUserRequestBody.getFirstName())
                .lastName(createUserRequestBody.getLastName())
                .email(createUserRequestBody.getEmail())
                .gender(User.Gender.valueOf(createUserRequestBody.getGender()))
                .stripeCustomerId(stripeCustomerId)
                .build();
        getSessionProvider().getSession().save(user);
        getSessionProvider().commitTransaction();
        return new UserView(user);
    }
}

package com.cronos.source;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.cronos.dao.SessionProvider;
import com.cronos.dao.UserDao;
import com.cronos.model.User;
import com.cronos.requestBody.CreateUserRequestBody;
import com.cronos.view.UserView;

@Path("/user")
public class UserSource {

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public UserView createUser(final CreateUserRequestBody createUserRequestBody) {
        try (final SessionProvider sessionProvider = new SessionProvider()) {
            final UserDao userDao = new UserDao(sessionProvider);
            return userDao.addUser(
                    createUserRequestBody.getFirstName(),
                    createUserRequestBody.getLastName(),
                    createUserRequestBody.getEmail(),
                    createUserRequestBody.getBirthday(),
                    createUserRequestBody.getGender());
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public UserView getUser(@PathParam("id") final int id) {
        try (final SessionProvider sessionProvider = new SessionProvider()) {
            final UserDao userDao = new UserDao(sessionProvider);
            final User user = userDao.getById(id);
            return new UserView(user);
        }
    }
}
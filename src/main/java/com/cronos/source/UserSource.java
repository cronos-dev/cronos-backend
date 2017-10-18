package com.cronos.source;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.cronos.dao.SessionProvider;
import com.cronos.dao.UserDao;
import com.cronos.model.User;
import com.cronos.requestBody.CreateUserRequestBody;

@Path("/user")
public class UserSource {

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public User createUser(final CreateUserRequestBody user) {
        try (final SessionProvider sessionProvider = new SessionProvider()) {
            final UserDao userDao = new UserDao(sessionProvider);
            return userDao.addUser(user.getFirstName(), user.getLastName());
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public User getUser(@PathParam("id") final int id) {

        try (final SessionProvider sessionProvider = new SessionProvider()) {
            final UserDao userDao = new UserDao(sessionProvider);
            return userDao.getById(id);
        }
    }
}
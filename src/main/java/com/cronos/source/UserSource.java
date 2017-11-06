package com.cronos.source;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.cronos.dao.SessionProvider;
import com.cronos.dao.UserDao;
import com.cronos.model.User;
import com.cronos.requestBody.CreateUserRequestBody;
import com.cronos.service.StripeService;
import com.cronos.view.UserView;
import com.stripe.exception.*;

@Path("/user")
public class UserSource {

    private StripeService stripeService = new StripeService();

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public UserView createUser(final CreateUserRequestBody createUserRequestBody) {
        try (final SessionProvider sessionProvider = new SessionProvider()) {
            final UserDao userDao = new UserDao(sessionProvider);
            final String stripeCustomerId = stripeService.createCustomer(
                    createUserRequestBody.getToken(), createUserRequestBody.getEmail());
            return userDao.createUser(createUserRequestBody, stripeCustomerId);
        } catch (final APIConnectionException | AuthenticationException | APIException | InvalidRequestException | CardException e) {
            e.printStackTrace();
            return null;
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
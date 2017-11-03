package com.cronos.service;

import com.cronos.Config;
import com.stripe.Stripe;
import com.stripe.exception.*;
import com.stripe.model.Charge;
import com.stripe.model.Customer;

import java.util.HashMap;
import java.util.Map;

import static com.cronos.Constants.*;

/**
 * Created by toshikijahja on 11/2/17.
 */
public class StripeService {

    private String apiKey;

    public StripeService() {
        final Config config = new Config();
        this.apiKey = config.getString(STRIPE_API_KEY);
    }

    public String createCustomer(final String token, final String email)
            throws CardException, APIException, AuthenticationException, InvalidRequestException, APIConnectionException {
        Stripe.apiKey = apiKey;
        final Map<String, Object> customerParams = new HashMap<>();
        customerParams.put(EMAIL, email);
        customerParams.put(SOURCE, token);
        final Customer customer = Customer.create(customerParams);
        return customer.getId();
    }

    public String charge(final String token, final String amount, final String description)
            throws CardException, APIException, AuthenticationException, InvalidRequestException, APIConnectionException {
        final Map<String, Object> params = new HashMap<>();
        params.put(AMOUNT, amount);
        params.put(CURRENCY, USD);
        params.put(DESCRIPTION, description);
        params.put(SOURCE, token);
        final Charge charge = Charge.create(params);
        return charge.getId();
    }
}

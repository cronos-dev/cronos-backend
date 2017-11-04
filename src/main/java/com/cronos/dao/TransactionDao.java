package com.cronos.dao;

import com.cronos.model.Restaurant;
import com.cronos.model.Transaction;

import java.math.BigDecimal;

import static com.cronos.Constants.WHITE_SPACE;

/**
 * Created by toshikijahja on 11/2/17.
 */
public class TransactionDao extends BaseDao<Transaction> {

    public TransactionDao(final SessionProvider sessionProvider) {
        super(sessionProvider, Transaction.class);
    }

    public String buildDescription(final String restaurantName, final BigDecimal amount, final int orderId) {
        final StringBuilder stringBuilder = new StringBuilder()
                .append(restaurantName)
                .append(WHITE_SPACE)
                .append("$")
                .append(amount)
                .append(WHITE_SPACE)
                .append("#orderId=")
                .append(orderId);
        return stringBuilder.toString();
    }
}

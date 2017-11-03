package com.cronos.dao;

import com.cronos.model.Restaurant;
import com.cronos.model.Transaction;

import java.math.BigDecimal;

/**
 * Created by toshikijahja on 11/2/17.
 */
public class TransactionDao extends BaseDao<Transaction> {

    public TransactionDao(final SessionProvider sessionProvider) {
        super(sessionProvider, Transaction.class);
    }

    public Transaction createTransaction(final int orderId, final int userId, final BigDecimal amount) {
        getSessionProvider().startTransaction();
        final Transaction transaction = new Transaction.Builder()
                .orderId(orderId)
                .userId(userId)
                .amount(amount)
                .status()

    }

    private String buildDescription(final String restaurantName, final BigDecimal amount, final int orderId) {

    }
}

package com.cronos.dao;

/**
 * Created by toshikijahja on 7/29/17.
 */
public class BaseDao<T> {

    private final SessionProvider sessionProvider;
    private final Class clazz;

    public BaseDao(final SessionProvider sessionProvider,
                   final Class clazz) {
        this.sessionProvider = sessionProvider;
        this.clazz = clazz;
    }

    public SessionProvider getSessionProvider() {
        return this.sessionProvider;
    }

    @SuppressWarnings("unchecked")
    public T getById(final int id) {
        return (T) getSessionProvider().getSession().get(clazz, id);
    }
}

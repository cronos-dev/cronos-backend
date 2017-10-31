package com.cronos.dao;

import javax.persistence.Query;
import java.util.List;

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

    @SuppressWarnings("unchecked")
    public List<T> getByIds(final List<Integer> ids) {
        final Query query = sessionProvider.getSession().createQuery("FROM " + clazz.getName() + " WHERE id IN :ids");
        query.setParameter("ids", ids);
        return query.getResultList();
    }
}

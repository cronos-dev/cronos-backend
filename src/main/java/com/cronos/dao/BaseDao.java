package com.cronos.dao;

import org.hibernate.Query;

import java.util.List;

import static com.cronos.Constants.DATA;

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
        final Query query = getSessionProvider().getSession().createQuery("FROM " + clazz.getName() + " WHERE id IN :ids");
        query.setParameterList("ids", ids);
        return query.list();
    }

    @SuppressWarnings("unchecked")
    public List<T> getByField(final String field, final String data) {
        final Query query = getSessionProvider().getSession().createQuery("FROM " + clazz.getName() + " WHERE " + field + " = :" + DATA);
        query.setParameter(DATA, data);
        return query.list();
    }

    @SuppressWarnings("unchecked")
    public List<T> getByField(final String field, final Integer data) {
        final Query query = getSessionProvider().getSession().createQuery("FROM " + clazz.getName() + " WHERE " + field + " = :" + DATA);
        query.setParameter(DATA, data);
        return query.list();
    }

    @SuppressWarnings("unchecked")
    public List<T> getByField(final String field, final Boolean data) {
        final Query query = getSessionProvider().getSession().createQuery("FROM " + clazz.getName() + " WHERE " + field + " = :" + DATA);
        query.setParameter(DATA, data);
        return query.list();
    }


}

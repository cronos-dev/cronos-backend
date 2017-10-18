package com.cronos.dao;

import com.cronos.model.Task;

/**
 * Created by toshikijahja on 7/29/17.
 */
public class TaskDao extends BaseDao<Task> {

    public TaskDao(final SessionProvider sessionProvider) {
        super(sessionProvider, Task.class);
    }

    public void addTask(final Task task) {
        getSessionProvider().startTransaction();
        getSessionProvider().getSession().save(task);
        getSessionProvider().commitTransaction();
    }

}

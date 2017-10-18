package com.cronos.dao;

import com.cronos.model.User;

/**
 * Created by toshikijahja on 6/7/17.
 */
public class UserDao extends BaseDao<User> {

    public UserDao(final SessionProvider sessionProvider) {
        super(sessionProvider, User.class);
    }

    public User addUser(final String firstName, final String lastName) {
        getSessionProvider().startTransaction();
        final User user = new User.Builder().firstName(firstName).lastName(lastName).build();
        getSessionProvider().getSession().save(user);
        getSessionProvider().commitTransaction();
        return user;
    }

//    public void addEmployee(Employee bean){
//        Session session = SessionUtil.getSession();
//        Transaction tx = session.beginTransaction();
//        addEmployee(session,bean);
//        tx.commit();
//        session.close();
//
//    }
//
//    private void addEmployee(Session session, Employee bean){
//        Employee employee = new Employee();
//
//        employee.setName(bean.getName());
//        employee.setAge(bean.getAge());
//
//        session.save(employee);
//    }
//
//    public List<Employee> getEmployees(){
//        Session session = SessionUtil.getSession();
//        Query query = session.createQuery("from Employee");
//        List<Employee> employees =  query.list();
//        session.close();
//        return employees;
//    }
//
//    public int deleteEmployee(int id) {
//        Session session = SessionUtil.getSession();
//        Transaction tx = session.beginTransaction();
//        String hql = "delete from Employee where id = :id";
//        Query query = session.createQuery(hql);
//        query.setInteger("id",id);
//        int rowCount = query.executeUpdate();
//        System.out.println("Rows affected: " + rowCount);
//        tx.commit();
//        session.close();
//        return rowCount;
//    }
//
//    public int updateEmployee(int id, Employee emp){
//        if(id <=0)
//            return 0;
//        Session session = SessionUtil.getSession();
//        Transaction tx = session.beginTransaction();
//        String hql = "update Employee set name = :name, age=:age where id = :id";
//        Query query = session.createQuery(hql);
//        query.setInteger("id",id);
//        query.setString("name",emp.getName());
//        query.setInteger("age",emp.getAge());
//        int rowCount = query.executeUpdate();
//        System.out.println("Rows affected: " + rowCount);
//        tx.commit();
//        session.close();
//        return rowCount;
//    }
}

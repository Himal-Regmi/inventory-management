package com.ismt.inventorymanagement.dao;

import com.ismt.inventorymanagement.entity.User;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import java.util.List;


@Repository
public class UserDaoImpl implements UserDao{
    @Autowired
    private EntityManager entityManager;

    @Override
    public List<User> getAllUser() {
        return null;
    }

    @Override
    public User getUserById(int id) {
        Session session= entityManager.unwrap(Session.class);
        if(id==0){
            return null;
        }
        //retrieve and return user
        User user=session.get(User.class,id);
        return user;
    }

    @Override
    public void save(User user) {
        Session session= entityManager.unwrap(Session.class);
        //save or update the user
        session.saveOrUpdate(user);
    }

    @Override
    public User findUserByEmail(String email) throws UsernameNotFoundException {
        Session session= entityManager.unwrap(Session.class);
        //retrieve user using the email
        Query<User> query= session.createQuery("from User where email = :uEmail",User.class);
        /*
        sql injections can help hacker retrieve information from database using special characters like "*"
        using parameterized query to protect against sql injections.
        */
        query.setParameter("uEmail",email);
        User user=null;
        try {
            user = query.getSingleResult();
        } catch (Exception e) {
            user = null;
        }
        return user;
    }

    @Override
    public void delete(int id) {
        Session session= entityManager.unwrap(Session.class);
        //retrieve user
        User user=session.get(User.class,id);
        //delete user
        session.delete(user);
    }
}

package com.recruitment.crud;

import com.recruitment.common.LoginException;
import com.recruitment.entity.Admin;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Date;
import java.util.List;

/**
 * Created by mcholka on 2014-05-22. Enjoy!
 */
@Stateless
public class AdminFinder {

    @PersistenceContext
    private EntityManager entityManager;

    public Admin findUserByLoginAndPassword(String login, String password) throws LoginException {
        Query query = entityManager.createQuery("" +
                        "SELECT i FROM Admin i " +
                        "WHERE i.login = :login " +
                        "AND i.password = :password"
        );
        query.setParameter("login", login);
        query.setParameter("password", password);

        try {
            return (Admin) query.getSingleResult();
        } catch (NoResultException e){
            throw new LoginException("Admin not found by login: " + login + " and password: " + password);
        }
    }

    @SuppressWarnings("unchecked")
    public List<Admin> findAdminListToLoggedOut() {
        Date now = new Date();
        Query query = entityManager.createQuery("" +
                "SELECT i FROM Admin i " +
                "WHERE i.limitTime < :now");
        query.setParameter("now", now);
        return query.getResultList();
    }
}

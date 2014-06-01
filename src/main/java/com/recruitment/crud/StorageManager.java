package com.recruitment.crud;

import org.apache.log4j.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.io.Serializable;

/**
 * Created by mcholka on 2014-03-25. Enjoy!
 */
@Stateless
public class StorageManager implements Serializable {
    private static final Logger logger = Logger.getLogger(StorageManager.class);

    @PersistenceContext
    private EntityManager entityManager;

    public <T> void persist(T object){
//        logger.info("Persist object: " + object.getClass().getName() + " to db");
        entityManager.persist(object);
        entityManager.flush();
    }

    public <T> T update(T object){
//        logger.info("Update object: " + object.getClass().getName());
        entityManager.merge(object);
        entityManager.flush();
        return object;
    }

    public <T> T findByID(Object id, Class<T> type) {
//        logger.info("Try to find class: " + type + " by id: " + id);
        T object = entityManager.find(type, id);
        checkEmpty(object, id, type);
        return object;
    }

    private <T> void checkEmpty(T object, Object id, Class<T> type) {
        if(object == null){
            throw new NoResultException(type + " not found by ID: " + id);
        }
    }
}

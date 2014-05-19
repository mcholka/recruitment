package com.recruitment.crud;

import com.recruitment.entity.KnowledgeBaseType;
import org.apache.log4j.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by mcholka on 2014-05-16. Enjoy!
 */
@Stateless
public class KnowledgeBaseTypeFinder {
    private static final Logger logger = Logger.getLogger(KnowledgeBaseTypeFinder.class);

    @PersistenceContext
    private EntityManager entityManager;

    public KnowledgeBaseType findByID(String id){
        logger.info("Try to find base type by id: " + id);
        return entityManager.find(KnowledgeBaseType.class, id);
    }
}

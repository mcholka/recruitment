package com.recruitment.crud;

import com.recruitment.entity.KnowledgeBaseType;
import org.apache.log4j.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

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

    @SuppressWarnings("unchecked")
    public List<KnowledgeBaseType> findByQuery(String query, int first, int pageSize) {
        Query entityQuery = entityManager.createQuery(query);
        entityQuery.setFirstResult(first);
        entityQuery.setMaxResults(pageSize);
        return entityQuery.getResultList();
    }
}

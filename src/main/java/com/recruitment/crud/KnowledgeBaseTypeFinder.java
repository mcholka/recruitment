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

    @SuppressWarnings("unchecked")
    public List<KnowledgeBaseType> findAllKnowledgeBaseTypes(){
        Query query = entityManager.createNamedQuery("KnowledgeBaseType.findAllKnowledgeBaseTypes");
        return query.getResultList();
    }
}

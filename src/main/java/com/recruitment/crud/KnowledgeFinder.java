package com.recruitment.crud;

import com.recruitment.common.KnowledgeBaseType;
import com.recruitment.entity.Knowledge;
import org.apache.log4j.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by mcholka on 2014-03-27. Enjoy!
 */
@Stateless
public class KnowledgeFinder {
    private static final Logger logger = Logger.getLogger(KnowledgeFinder.class);

    @PersistenceContext
    private EntityManager entityManager;

    public List<Knowledge> getKnowledgeByBaseType(KnowledgeBaseType baseType){
        Query query = entityManager.createNamedQuery("Knowledge.getKnowledgeByBaseType");
        query.setParameter("baseType", baseType);
        @SuppressWarnings("unchecked")
        List<Knowledge> valueList = query.getResultList();
        logger.info("Found " + valueList.size() + " values by type: " + baseType);
        return valueList;
    }
}

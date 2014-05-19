package com.recruitment.crud;

import com.recruitment.entity.Archetype;
import com.recruitment.entity.Knowledge;
import com.recruitment.entity.KnowledgeBaseType;
import org.apache.log4j.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
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

    public List<Knowledge> getKnowledgeByArchetype(Archetype archetype){
        Query query = entityManager.createNamedQuery("Knowledge.getKnowledgeByArchetype");
        query.setParameter("archetype", archetype);
        @SuppressWarnings("unchecked")
        List<Knowledge> valueList = query.getResultList();
        logger.info("Found " + valueList.size() + " values by archetype: " + archetype.getId());
        return valueList;
    }

    public Knowledge findByArchetypeBaseTypeAndValue(KnowledgeBaseType knowledgeBaseType, Archetype archetype, String value){
        logger.info("Find knowledge by base type: " + knowledgeBaseType.getId() + " " +
                "archetype: " + archetype.getId() + " " +
                "value: " + value);
        Query query = entityManager.createNamedQuery("Knowledge.findByArchetypeBaseTypeAndValue");
        query.setParameter("archetype", archetype);
        query.setParameter("baseType", knowledgeBaseType);
        query.setParameter("value", value);
        try {
            return (Knowledge) query.getSingleResult();
        } catch (NoResultException e){
            return null;
        }
    }
}

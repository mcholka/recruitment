package com.recruitment.crud;

import com.recruitment.entity.Archetype;
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
public class ArchetypeFinder {
    private static final Logger logger = Logger.getLogger(ArchetypeFinder.class);

    @PersistenceContext
    private EntityManager entityManager;

    @SuppressWarnings("unchecked")
    public List<Archetype> findAllArchetypes(){
        Query query = entityManager.createNamedQuery("Archetype.findAllArchetypes");
        return query.getResultList();
    }

    public Archetype findById(String id){
        logger.info("Try to find archetype by id: " + id);
        return entityManager.find(Archetype.class, id);
    }

    @SuppressWarnings("unchecked")
    public List<Archetype> findByQuery(String query, int first, int pageSize) {
        Query entityQuery = entityManager.createQuery(query);
        entityQuery.setFirstResult(first);
        entityQuery.setMaxResults(pageSize);
        return entityQuery.getResultList();
    }
}

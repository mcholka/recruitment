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
        return entityManager.find(Archetype.class, id);
    }
}

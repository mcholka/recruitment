package com.recruitment.crud;

import com.recruitment.entity.Profession;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by mcholka on 2014-03-26. Enjoy!
 */
@Stateless
public class ProfessionFinder {

    @PersistenceContext
    private EntityManager entityManager;

    @SuppressWarnings("unchecked")
    public List<String> getAllProfessions(){
        Query query = entityManager.createNamedQuery("Profession.getAllProfessions");
        return query.getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<Profession> findByQuery(String query, int first, int pageSize) {
        Query entityQuery = entityManager.createQuery(query);
        entityQuery.setFirstResult(first);
        entityQuery.setMaxResults(pageSize);
        return entityQuery.getResultList();
    }
}

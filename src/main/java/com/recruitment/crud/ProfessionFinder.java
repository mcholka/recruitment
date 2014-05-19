package com.recruitment.crud;

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
}

package com.recruitment.crud;

import com.recruitment.common.ProcessStatus;
import com.recruitment.entity.CustomerData;
import org.apache.log4j.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by mcholka on 2014-03-26. Enjoy!
 */
@Stateless
public class CustomerDataFinder {
private static final Logger logger = Logger.getLogger(CustomerDataFinder.class);

    @PersistenceContext
    private EntityManager entityManager;

    public List<CustomerData> getCustomersForDataExtract(){
        Query query = entityManager.createNamedQuery("CustomerData.getCustomersForDataExtract");
        query.setParameter("status", ProcessStatus.NEW);

        @SuppressWarnings("unchecked")
        List<CustomerData> customers = query.getResultList();
        logger.info("Found " + customers.size() + " customers for data extract");
        return customers;
    }
}

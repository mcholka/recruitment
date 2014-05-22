package com.recruitment.crud;

import com.recruitment.common.ProcessStatus;
import com.recruitment.entity.CustomerData;
import org.apache.log4j.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.io.Serializable;
import java.util.List;

/**
 * Created by mcholka on 2014-03-26. Enjoy!
 */
@Stateless
public class CustomerDataFinder implements Serializable {
    private static final Logger logger = Logger.getLogger(CustomerDataFinder.class);

    @PersistenceContext
    private EntityManager entityManager;

    public List<CustomerData> getCustomersByStatus(ProcessStatus processStatus){
        Query query = entityManager.createNamedQuery("CustomerData.getCustomersByStatus");
        query.setParameter("status", processStatus);

        @SuppressWarnings("unchecked")
        List<CustomerData> customers = query.getResultList();
        logger.info("Found " + customers.size() + " customers for data extract");
        return customers;
    }

    @SuppressWarnings("unchecked")
    public List<CustomerData> getAllCustomers(){
        Query query = entityManager.createNamedQuery("CustomerData.getAllCustomers");
        return query.getResultList();
    }
}

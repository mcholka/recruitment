package com.recruitment.crud;

import com.recruitment.common.ProcessStatus;
import com.recruitment.entity.ExtractedData;
import org.apache.log4j.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by mcholka on 2014-04-25. Enjoy!
 */
@Stateless
public class ExtractedDataFinder {
    private static final Logger logger = Logger.getLogger(ExtractedDataFinder.class);

    @PersistenceContext
    private EntityManager entityManager;

    @SuppressWarnings("unchecked")
    public List<ExtractedData> findExtractedDataToFilter(){
        Query query = entityManager.createNamedQuery("ExtractedData.findExtractedDataToFilter");
        query.setParameter("status", ProcessStatus.EXTRACTED);
        return query.getResultList();
    }
}

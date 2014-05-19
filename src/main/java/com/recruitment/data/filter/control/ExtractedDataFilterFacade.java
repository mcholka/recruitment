package com.recruitment.data.filter.control;

import com.recruitment.common.KnowledgeHolder;
import com.recruitment.common.ProcessStatus;
import com.recruitment.crud.StorageManager;
import com.recruitment.data.extract.control.EntityMapper;
import com.recruitment.entity.*;
import org.apache.log4j.Logger;

import javax.inject.Inject;
import java.util.List;


/**
 * Created by mcholka on 2014-04-25. Enjoy!
 */
public class ExtractedDataFilterFacade {
    private static final Logger logger = Logger.getLogger(ExtractedDataFilterFacade.class);

    @Inject
    private EntityMapper entityMapper;
    @Inject
    private ExtractedDataFilter extractedDataFilter;
    @Inject
    private StorageManager storageManager;

    public void matchExtractedDataToProfession(ExtractedData extractedData) {
        logger.info("Filtering extracted data for customer: " + extractedData.getCustomerData().getId());
        List<Knowledge> knowledgeFromProfession = getKnowledgeFromProfession(extractedData);
        KnowledgeHolder knowledgeHolder = processMatch(knowledgeFromProfession, extractedData);

        logger.info("\nFiltered data:\n" + knowledgeHolder.toString());

        FilteredData filteredData = mapping(knowledgeHolder, extractedData);
        persist(filteredData);
        updateCustomerStatus(extractedData.getCustomerData());
        logger.info("Customer filtered");
    }

    private List<Knowledge> getKnowledgeFromProfession(ExtractedData extractedData) {
        CustomerData customerData = extractedData.getCustomerData();
        Profession profession = customerData.getProfession();
        return profession.getKnowledgeList();
    }

    private KnowledgeHolder processMatch(List<Knowledge> knowledgeFromProfession, ExtractedData extractedData) {
        return extractedDataFilter.filter(knowledgeFromProfession, extractedData);
    }

    private FilteredData mapping(KnowledgeHolder knowledgeHolder, ExtractedData extractedData) {
        return entityMapper.mappingToFilteredData(knowledgeHolder, extractedData.getCustomerData());
    }

    private void persist(FilteredData filteredData) {
        storageManager.persist(filteredData);
    }

    private void updateCustomerStatus(CustomerData customerData) {
        customerData.setProcessStatus(ProcessStatus.FILTERED);
    }
}

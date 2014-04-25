package com.recruitment.data.filter.control;

import com.recruitment.common.KnowledgeBaseType;
import com.recruitment.common.KnowledgeCommon;
import com.recruitment.common.ProcessStatus;
import com.recruitment.common.RecruitmentUtils;
import com.recruitment.crud.StorageManager;
import com.recruitment.data.extract.control.KnowledgeCommonMapper;
import com.recruitment.entity.*;
import org.apache.log4j.Logger;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * Created by mcholka on 2014-04-25. Enjoy!
 */
public class ExtractedDataFilterFacade {
    private static final Logger logger = Logger.getLogger(ExtractedDataFilterFacade.class);

    @Inject
    private KnowledgeCommonMapper knowledgeCommonMapper;
    @Inject
    private ExtractedDataFilter extractedDataFilter;
    @Inject
    private StorageManager storageManager;

    public void matchExtractedDataToProfession(ExtractedData extractedData) {
        logger.info("Filtering extracted data for customer: " + extractedData.getCustomerData().getId());
        List<Knowledge> knowledgeFromProfession = getKnowledgeFromProfession(extractedData);
        KnowledgeCommon knowledgeCommon = processMatch(knowledgeFromProfession, extractedData);

        logger.info("\nFiltered: " +
                "\nExperience data: " + knowledgeCommon.getExperience().size() +
                "\nEducation data: " + knowledgeCommon.getEducation().size() +
                "\nSkills data: " + knowledgeCommon.getSkills().size() +
                "\nInterest data: " + knowledgeCommon.getInterest().size());

        FilteredData filteredData = mapping(knowledgeCommon, extractedData);
        persist(filteredData);
        updateCustomerStatus(extractedData.getCustomerData());
        logger.info("Customer filtered");
    }

    private List<Knowledge> getKnowledgeFromProfession(ExtractedData extractedData) {
        CustomerData customerData = extractedData.getCustomerData();
        Profession profession = customerData.getProfession();
        return profession.getKnowledgeList();
    }

    private KnowledgeCommon processMatch(List<Knowledge> knowledgeFromProfession, ExtractedData extractedData) {
        return extractedDataFilter.filter(knowledgeFromProfession, extractedData);
    }

    private FilteredData mapping(KnowledgeCommon knowledgeCommon, ExtractedData extractedData) {
        return knowledgeCommonMapper.mappingToFilteredData(knowledgeCommon, extractedData.getCustomerData());
    }

    private void persist(FilteredData filteredData) {
        storageManager.persist(filteredData);
    }

    private void updateCustomerStatus(CustomerData customerData) {
        customerData.setProcessStatus(ProcessStatus.FILTERED);
    }
}

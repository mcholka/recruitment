package com.recruitment.data.extract.control;

import com.recruitment.common.KnowledgeCommon;
import com.recruitment.common.ProcessStatus;
import com.recruitment.crud.StorageManager;
import com.recruitment.entity.CustomerData;
import com.recruitment.entity.ExtractedData;

import javax.inject.Inject;
import java.io.IOException;

/**
 * Created by mcholka on 2014-03-27. Enjoy!
 */
public class CustomerExtractFacade {

    @Inject
    CustomerDataExtractor customerDataExtractor;
    @Inject
    KnowledgeCommonMapper knowledgeCommonMapper;
    @Inject
    StorageManager storageManager;


    public void processPerson(CustomerData customerData) throws IOException {
        KnowledgeCommon extractedKnowledge = extractData(customerData);
        ExtractedData extractedData = mappingToExtractedData(extractedKnowledge, customerData);
        store(extractedData);
        updateCustomerStatus(customerData);
    }

    private KnowledgeCommon extractData(CustomerData customerData) throws IOException {
        return customerDataExtractor.extractData(customerData);
    }

    private ExtractedData mappingToExtractedData(KnowledgeCommon extractedKnowledge, CustomerData customerData) {
        return knowledgeCommonMapper.mappingToExtractedData(extractedKnowledge, customerData);
    }

    private void store(ExtractedData extractedData) {
        storageManager.persist(extractedData);
    }

    private void updateCustomerStatus(CustomerData customerData) {
        customerData.setProcessStatus(ProcessStatus.EXTRACTED);
    }
}

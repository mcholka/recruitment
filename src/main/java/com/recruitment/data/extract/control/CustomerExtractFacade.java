package com.recruitment.data.extract.control;

import com.recruitment.common.KnowledgeHolder;
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
    EntityMapper entityMapper;
    @Inject
    StorageManager storageManager;

    public void processPerson(CustomerData customerData) throws IOException {
        KnowledgeHolder extractedKnowledge = extractData(customerData);
        ExtractedData extractedData = mappingToExtractedData(extractedKnowledge, customerData);
        store(extractedData);
        updateCustomerStatus(customerData);
    }

    private KnowledgeHolder extractData(CustomerData customerData) throws IOException {
        return customerDataExtractor.extractData(customerData);
    }

    private ExtractedData mappingToExtractedData(KnowledgeHolder extractedKnowledge, CustomerData customerData) {
        return entityMapper.mappingToExtractedData(extractedKnowledge, customerData);
    }

    private void store(ExtractedData extractedData) {
        storageManager.persist(extractedData);
    }

    private void updateCustomerStatus(CustomerData customerData) {
        customerData.setProcessStatus(ProcessStatus.EXTRACTED);
    }
}

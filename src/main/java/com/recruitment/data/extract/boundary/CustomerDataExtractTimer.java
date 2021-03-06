package com.recruitment.data.extract.boundary;

import com.recruitment.common.ProcessStatus;
import com.recruitment.crud.CustomerDataFinder;
import com.recruitment.data.extract.control.CustomerExtractFacade;
import com.recruitment.entity.CustomerData;
import org.apache.log4j.Logger;

import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import java.util.List;

/**
 * Created by mcholka on 2014-03-26. Enjoy!
 */
@Singleton
@Startup
public class CustomerDataExtractTimer {
    private static final Logger logger = Logger.getLogger(CustomerDataExtractTimer.class);

    @Inject
    private CustomerDataFinder customerDataFinder;
    @Inject
    private CustomerExtractFacade customerExtractFacade;

    @Schedule(persistent = false, hour = "*", minute = "*/1")
    public synchronized void extractDocuments(){
        logger.info("FileExtractorTimer start");
        List<CustomerData> customers = getCustomersToExtractData();

        for(CustomerData customerData : customers){
            extractData(customerData);
        }
        logger.info("FileExtractorTimer end");
    }

    private List<CustomerData> getCustomersToExtractData() {
        return customerDataFinder.getCustomersByStatus(ProcessStatus.NEW);
    }

    private void extractData(CustomerData customerData) {
        try {
            customerExtractFacade.processPerson(customerData);
        } catch (Exception e){
            logger.error("Problem with extract customer: " + customerData.getId(), e);
        }
    }
}

package com.recruitment.data.rating.boundary;

import com.recruitment.common.ProcessStatus;
import com.recruitment.crud.CustomerDataFinder;
import com.recruitment.data.rating.control.CustomerRatingFacade;
import com.recruitment.entity.CustomerData;
import com.recruitment.entity.FilteredData;
import org.apache.log4j.Logger;

import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import java.util.List;

/**
 * Created by mcholka on 2014-04-09. Enjoy!
 */
@Singleton
@Startup
public class CustomerDataRatingTimer {
    private static final Logger logger = Logger.getLogger(CustomerDataRatingTimer.class);

    @Inject
    CustomerDataFinder customerDataFinder;
    @Inject
    CustomerRatingFacade customerRatingFacade;

    @Schedule(persistent = false, hour = "*", minute = "*/1", second = "40")
    public synchronized void verify(){
        logger.info("FilteredDataVerifier start");
        List<CustomerData> customerDataList = findCustomersForRating();
        for(CustomerData customerData : customerDataList){
            processRating(customerData.getFilteredData());
        }
        logger.info("FilteredDataVerifier end");
    }

    private List<CustomerData> findCustomersForRating() {
        return customerDataFinder.getCustomersByStatus(ProcessStatus.FILTERED);
    }

    private void processRating(FilteredData filteredData) {
        customerRatingFacade.processRating(filteredData);
    }
}

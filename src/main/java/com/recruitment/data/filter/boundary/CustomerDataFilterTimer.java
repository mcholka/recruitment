package com.recruitment.data.filter.boundary;

import com.recruitment.common.ProcessStatus;
import com.recruitment.crud.CustomerDataFinder;
import com.recruitment.data.filter.control.ExtractedDataFilterFacade;
import com.recruitment.entity.CustomerData;
import com.recruitment.entity.ExtractedData;
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
public class CustomerDataFilterTimer {
    private static final Logger logger = Logger.getLogger(CustomerDataFilterTimer.class);

    @Inject
    CustomerDataFinder customerDataFinder;
    @Inject
    private ExtractedDataFilterFacade extractedDataFilterFacade;

    @Schedule(persistent = false, hour = "*", minute = "*/1", second = "20")
    public synchronized void filterData(){
        logger.info("DataFilterTimer start");
        List<CustomerData> customerDataList = getExtractedDataToFilter();
        for(CustomerData customerData : customerDataList){
            filter(customerData.getExtractedData());
        }
        logger.info("DataFilterTimer end");
    }

    private List<CustomerData> getExtractedDataToFilter() {
        return customerDataFinder.getCustomersByStatus(ProcessStatus.EXTRACTED);
    }

    private void filter(ExtractedData extractedData) {
        extractedDataFilterFacade.matchExtractedDataToProfession(extractedData);
    }
}

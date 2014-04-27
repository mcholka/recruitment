package com.recruitment.data.filter.boundary;

import com.recruitment.crud.ExtractedDataFinder;
import com.recruitment.data.filter.control.ExtractedDataFilterFacade;
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
public class DataFilterTimer {
    private static final Logger logger = Logger.getLogger(DataFilterTimer.class);

    @Inject
    private ExtractedDataFinder extractedDataFinder;
    @Inject
    private ExtractedDataFilterFacade extractedDataFilterFacade;

    @Schedule(persistent = false, hour = "*", minute = "*/1", second = "20")
    public synchronized void filterData(){
        logger.info("DataFilterTimer start");
        List<ExtractedData> extractedDataList = getExtractedDataToFilter();
        for(ExtractedData extractedData : extractedDataList){
            filter(extractedData);
        }
        logger.info("DataFilterTimer end");
    }

    private List<ExtractedData> getExtractedDataToFilter() {
        return extractedDataFinder.findExtractedDataToFilter();
    }

    private void filter(ExtractedData extractedData) {
        extractedDataFilterFacade.matchExtractedDataToProfession(extractedData);
    }
}

package com.recruitment.data.rating.boundary;

import org.apache.log4j.Logger;

import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;

/**
 * Created by mcholka on 2014-04-09. Enjoy!
 */
@Singleton
@Startup
public class FilteredDataVerifier {
    private static final Logger logger = Logger.getLogger(FilteredDataVerifier.class);

    @Schedule(persistent = false, hour = "*", minute = "*/1", second = "40")
    public synchronized void verify(){
        logger.info("FilteredDataVerifier start");
        logger.info("FilteredDataVerifier end");
    }
}

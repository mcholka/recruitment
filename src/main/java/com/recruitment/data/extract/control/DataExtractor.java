package com.recruitment.data.extract.control;

import com.recruitment.common.RecruitmentUtils;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mcholka on 2014-03-27. Enjoy!
 */
public class DataExtractor {
    private static final Logger logger = Logger.getLogger(DataExtractor.class);

    public List<String> extractData(String document, List<String> interestedValues){
        List<String> foundValues = new ArrayList<>();
        for(String interestingValue : interestedValues){
            String value = tryToFindValue(document, interestingValue);
            if(!RecruitmentUtils.emptyString(value)){
                foundValues.add(value);
            }
        }
        logger.info("Found in doc " + foundValues.size() + " interesting values");

        return foundValues;
    }

    private String tryToFindValue(String document, String interestingValue) {
        logger.info("Try to find value: " + interestingValue);
        if(document.contains(interestingValue)){
            logger.info("Found without any changes! Value: " + interestingValue);
            return interestingValue;
        }
        if(document.toUpperCase().contains(interestingValue.toUpperCase())){
            logger.info("Found with only upperCase! Value: " + interestingValue);
            return interestingValue;
        }
        String formattedDocument = document.toUpperCase().replaceAll("[^A-Z0-9\\s]", "");
        String formattedInterestingValue = interestingValue.toUpperCase().replaceAll("[^A-Z0-9\\s]", "");

        if(formattedDocument.contains(formattedInterestingValue)) {
            logger.info("Found before formatted! Value: " + interestingValue);
            return interestingValue;
        }
        logger.info("Value not found...");
        return null;
    }

}

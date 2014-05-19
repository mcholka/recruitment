package com.recruitment.data.extract.control;

import com.recruitment.common.KnowledgeHolder;
import com.recruitment.entity.CustomerData;
import com.recruitment.entity.ExtractedData;
import com.recruitment.entity.FilteredData;
import com.recruitment.entity.RatedData;

import java.math.BigDecimal;

/**
 * Created by mcholka on 2014-03-27. Enjoy!
 */
public class EntityMapper {

    public ExtractedData mappingToExtractedData(KnowledgeHolder knowledgeHolder, CustomerData customerData){
        ExtractedData extractedData = new ExtractedData();
        extractedData.setValue(knowledgeHolder.toString());
        extractedData.setCustomerData(customerData);
        return extractedData;
    }

    public FilteredData mappingToFilteredData(KnowledgeHolder knowledgeHolder, CustomerData customerData){
        FilteredData filteredData = new FilteredData();
        filteredData.setValue(knowledgeHolder.toString());
        filteredData.setCustomerData(customerData);
        return filteredData;
    }

    public RatedData mappingToRatedData(BigDecimal points, CustomerData customerData){
        RatedData ratedData = new RatedData();
        ratedData.setSumPoints(points);
        ratedData.setCustomerData(customerData);
        return ratedData;
    }}

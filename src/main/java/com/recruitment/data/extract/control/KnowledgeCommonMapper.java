package com.recruitment.data.extract.control;

import com.recruitment.common.KnowledgeCommon;
import com.recruitment.entity.CustomerData;
import com.recruitment.entity.ExtractedData;
import com.recruitment.entity.FilteredData;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by mcholka on 2014-03-27. Enjoy!
 */
public class KnowledgeCommonMapper {

    public ExtractedData mappingToExtractedData(KnowledgeCommon knowledgeCommon, CustomerData customerData){
        ExtractedData extractedData = new ExtractedData();
        extractedData.setExperience(StringUtils.join(knowledgeCommon.getExperience(), ';'));
        extractedData.setEducation(StringUtils.join(knowledgeCommon.getEducation(), ';'));
        extractedData.setSkills(StringUtils.join(knowledgeCommon.getSkills(), ';'));
        extractedData.setInterest(StringUtils.join(knowledgeCommon.getInterest(), ';'));
        extractedData.setCustomerData(customerData);
        return extractedData;
    }

    public FilteredData mappingToFilteredData(KnowledgeCommon knowledgeCommon, CustomerData customerData){
        FilteredData filteredData = new FilteredData();
        filteredData.setExperience(StringUtils.join(knowledgeCommon.getExperience(), ';'));
        filteredData.setEducation(StringUtils.join(knowledgeCommon.getEducation(), ';'));
        filteredData.setSkills(StringUtils.join(knowledgeCommon.getSkills(), ';'));
        filteredData.setInterest(StringUtils.join(knowledgeCommon.getInterest(), ';'));
        filteredData.setCustomerData(customerData);
        return filteredData;
    }
}

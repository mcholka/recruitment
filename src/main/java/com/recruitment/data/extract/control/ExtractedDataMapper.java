package com.recruitment.data.extract.control;

import com.recruitment.common.KnowledgeCommon;
import com.recruitment.entity.CustomerData;
import com.recruitment.entity.ExtractedData;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by mcholka on 2014-03-27. Enjoy!
 */
public class ExtractedDataMapper {

    public ExtractedData mappingToExtractedData(KnowledgeCommon knowledgeCommon, CustomerData customerData){
        ExtractedData extractedData = new ExtractedData();
        extractedData.setExperience(StringUtils.join(knowledgeCommon.getExperience(), ';'));
        extractedData.setEducation(StringUtils.join(knowledgeCommon.getEducation(), ';'));
        extractedData.setSkills(StringUtils.join(knowledgeCommon.getSkills(), ';'));
        extractedData.setInterest(StringUtils.join(knowledgeCommon.getInterest(), ';'));
        extractedData.setCustomerData(customerData);
        return extractedData;
    }
}

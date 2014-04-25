package com.recruitment.data.filter.control;

import com.recruitment.common.KnowledgeBaseType;
import com.recruitment.common.KnowledgeCommon;
import com.recruitment.common.RecruitmentUtils;
import com.recruitment.entity.ExtractedData;
import com.recruitment.entity.Knowledge;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by mcholka on 2014-04-25. Enjoy!
 */
public class ExtractedDataFilter {

    public KnowledgeCommon filter(List<Knowledge> knowledgeFromProfession, ExtractedData extractedData){
        KnowledgeCommon knowledgeCommon = new KnowledgeCommon();
        knowledgeCommon.setExperience(filterData(KnowledgeBaseType.EXPERIENCE, knowledgeFromProfession, extractedData.getExperience()));
        knowledgeCommon.setEducation(filterData(KnowledgeBaseType.EDUCATION, knowledgeFromProfession, extractedData.getEducation()));
        knowledgeCommon.setSkills(filterData(KnowledgeBaseType.SKILLS, knowledgeFromProfession, extractedData.getSkills()));
        knowledgeCommon.setInterest(filterData(KnowledgeBaseType.INTEREST, knowledgeFromProfession, extractedData.getInterest()));
        return knowledgeCommon;
    }

    private List<String> filterData(KnowledgeBaseType knowledgeBaseType, List<Knowledge> knowledgeFromProfession, String value) {
        if(RecruitmentUtils.emptyString(value)){
            return Collections.emptyList();
        }
        String[] values = value.split(";");
        List<Knowledge> professionKnowledge = filterProfessionValuesByBaseType(knowledgeBaseType, knowledgeFromProfession);
        return filterExtractedCustomerValues(values, professionKnowledge);
    }

    private List<Knowledge> filterProfessionValuesByBaseType(KnowledgeBaseType knowledgeBaseType, List<Knowledge> knowledgeFromProfession) {
        List<Knowledge> professionKnowledge = new ArrayList<>();
        for(Knowledge knowledge : knowledgeFromProfession){
            if(knowledgeBaseType.equals(knowledge.getKnowledgeBaseType())){
                professionKnowledge.add(knowledge);
            }
        }
        return professionKnowledge;
    }

    private List<String> filterExtractedCustomerValues(String[] values, List<Knowledge> professionKnowledge) {
        List<String> filteredValues = new ArrayList<>();
        for(String value : values) {
            for(Knowledge knowledge : professionKnowledge){
                if(value.equals(knowledge.getValue())){
                    filteredValues.add(value);
                    break;
                }
            }
        }
        return filteredValues;
    }
}

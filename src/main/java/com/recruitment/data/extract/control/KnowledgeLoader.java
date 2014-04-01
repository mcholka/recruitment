package com.recruitment.data.extract.control;

import com.recruitment.common.KnowledgeBaseType;
import com.recruitment.common.KnowledgeCommon;
import com.recruitment.crud.KnowledgeFinder;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.List;

/**
 * Created by mcholka on 2014-03-27. Enjoy!
 */
public class KnowledgeLoader {

    @Inject
    KnowledgeFinder knowledgeFinder;

    private KnowledgeCommon knowledgeCommon;

    @PostConstruct
    public void init(){
        knowledgeCommon = new KnowledgeCommon();
        knowledgeCommon.setExperience(knowledgeFinder.getKnowledgeByBaseType(KnowledgeBaseType.EXPERIENCE));
        knowledgeCommon.setEducation(knowledgeFinder.getKnowledgeByBaseType(KnowledgeBaseType.EDUCATION));
        knowledgeCommon.setSkills(knowledgeFinder.getKnowledgeByBaseType(KnowledgeBaseType.SKILLS));
        knowledgeCommon.setInterest(knowledgeFinder.getKnowledgeByBaseType(KnowledgeBaseType.INTEREST));
    }

    public List<String> getAllExperience() {
        return knowledgeCommon.getExperience();
    }

    public List<String> getAllEducation() {
        return knowledgeCommon.getEducation();
    }

    public List<String> getAllSkills() {
        return knowledgeCommon.getSkills();
    }

    public List<String> getAllInterest() {
        return knowledgeCommon.getInterest();
    }
}

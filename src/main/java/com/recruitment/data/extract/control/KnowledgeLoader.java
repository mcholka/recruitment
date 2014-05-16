package com.recruitment.data.extract.control;

import com.recruitment.common.KnowledgeBaseType;
import com.recruitment.crud.KnowledgeFinder;
import com.recruitment.entity.Knowledge;
import org.apache.log4j.Logger;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.List;

/**
 * Created by mcholka on 2014-03-27. Enjoy!
 */
public class KnowledgeLoader {
    private static final Logger logger = Logger.getLogger(KnowledgeLoader.class);

    @Inject
    KnowledgeFinder knowledgeFinder;

    private List<Knowledge> experienceList;
    private List<Knowledge> educationList;
    private List<Knowledge> skillList;
    private List<Knowledge> interestList;

    @PostConstruct
    public void init(){
        logger.info("init KnowledgeLoader");
        experienceList = knowledgeFinder.getKnowledgeByBaseType(KnowledgeBaseType.EXPERIENCE);
        educationList = knowledgeFinder.getKnowledgeByBaseType(KnowledgeBaseType.EDUCATION);
        skillList = knowledgeFinder.getKnowledgeByBaseType(KnowledgeBaseType.SKILLS);
        interestList = knowledgeFinder.getKnowledgeByBaseType(KnowledgeBaseType.INTEREST);
    }

    public List<Knowledge> getExperienceList() {
        return experienceList;
    }

    public List<Knowledge> getEducationList() {
        return educationList;
    }

    public List<Knowledge> getSkillList() {
        return skillList;
    }

    public List<Knowledge> getInterestList() {
        return interestList;
    }
}

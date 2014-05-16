package com.recruitment.data.filter.control;

import com.recruitment.common.KnowledgeHolder;
import com.recruitment.common.RecruitmentUtils;
import com.recruitment.crud.ArchetypeFinder;
import com.recruitment.crud.KnowledgeFinder;
import com.recruitment.entity.Archetype;
import com.recruitment.entity.ExtractedData;
import com.recruitment.entity.Knowledge;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by mcholka on 2014-04-25. Enjoy!
 */
public class ExtractedDataFilter {

    @Inject
    KnowledgeFinder knowledgeFinder;
    @Inject
    ArchetypeFinder archetypeFinder;

    public KnowledgeHolder filter(List<Knowledge> knowledgeFromProfession, ExtractedData extractedData){
        KnowledgeHolder knowledgeHolder = new KnowledgeHolder();
        List<String> foundValues;
        List<Archetype> archetypes = getAllArchetypes();
        for(Archetype archetype : archetypes){
            foundValues = filterData(archetype, knowledgeFromProfession, extractedData.getValue());
            for(String value : foundValues){
                knowledgeHolder.appendFullProcessed(value);
            }
        }
        return knowledgeHolder;
    }

    private List<Archetype> getAllArchetypes() {
        return archetypeFinder.findAllArchetypes();
    }

    private List<String> filterData(Archetype archetype, List<Knowledge> knowledgeFromProfession, String value) {
        if(RecruitmentUtils.emptyString(value)){
            return Collections.emptyList();
        }
        String[] values = value.split(";");
        List<Knowledge> professionKnowledge = filterProfessionValuesByArchetype(archetype, knowledgeFromProfession);
        return filterExtractedCustomerValues(values, professionKnowledge);
    }

    private List<Knowledge> filterProfessionValuesByArchetype(Archetype archetype, List<Knowledge> knowledgeFromProfession) {
        List<Knowledge> professionKnowledge = new ArrayList<>();
        for(Knowledge knowledge : knowledgeFromProfession){
            if(archetype.equals(knowledge.getArchetype())){
                professionKnowledge.add(knowledge);
            }
        }
        return professionKnowledge;
    }

    private List<String> filterExtractedCustomerValues(String[] values, List<Knowledge> professionKnowledge) {
        List<String> filteredValues = new ArrayList<>();
        for(String value : values) {
            for(Knowledge knowledge : professionKnowledge){
                if(contains(value, knowledge)){
                    filteredValues.add(value);
                    break;
                }
            }
        }
        return filteredValues;
    }

    private boolean contains(String value, Knowledge knowledge) {
        return value.contains(knowledge.getValue()) &&
                value.contains(knowledge.getKnowledgeBaseType().getId()) &&
                value.contains(knowledge.getArchetype().getId());
    }
}

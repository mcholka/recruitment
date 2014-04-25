package com.recruitment.common;

import java.util.Arrays;
import java.util.List;

/**
 * Created by mcholka on 2014-03-26. Enjoy!
 */
public enum KnowledgeBaseType {

    EXPERIENCE(Arrays.asList(Archetype.POSITION, Archetype.FIRM, Archetype.RESPONSIBILITY)),
    EDUCATION(Arrays.asList(Archetype.UNIWERSITY, Archetype.POLYTECHNIC)),
    SKILLS(Arrays.asList(Archetype.TECHNICAL, Archetype.OTHER)),
    INTEREST(Arrays.asList(Archetype.OTHER));

    private KnowledgeBaseType(List<Archetype> archetypes){
        this.archetypes = archetypes;
    }

    private List<Archetype> archetypes;

    public List<Archetype> getArchetypes(){
        return archetypes;
    }
}

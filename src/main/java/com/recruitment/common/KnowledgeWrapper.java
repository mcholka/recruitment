package com.recruitment.common;

import com.recruitment.entity.Affix;
import com.recruitment.entity.Archetype;
import com.recruitment.entity.Knowledge;
import com.recruitment.entity.KnowledgeBaseType;
import org.apache.log4j.Logger;

import java.math.BigDecimal;

/**
 * Created by mcholka on 2014-05-18. Enjoy!
 */
public class KnowledgeWrapper {
    private static final Logger logger = Logger.getLogger(KnowledgeWrapper.class);

    private Archetype archetype;
    private KnowledgeBaseType knowledgeBaseType;
    private Knowledge value;
    private Affix prefix;
    private Affix suffix;

    public BigDecimal sumPoints(){
        BigDecimal points = BigDecimal.ZERO;
        points = points.
                add(archetype.getPoints()).
                add(knowledgeBaseType.getPoints()).
                add(value.getPoints());
        if(prefix != null){
            points = points.add(prefix.getPoints());
        }
        if(suffix != null){
            points = points.add(suffix.getPoints());
        }
        return points;
    }

    public Archetype getArchetype() {
        return archetype;
    }

    public void setArchetype(Archetype archetype) {
        this.archetype = archetype;
    }

    public KnowledgeBaseType getKnowledgeBaseType() {
        return knowledgeBaseType;
    }

    public void setKnowledgeBaseType(KnowledgeBaseType knowledgeBaseType) {
        this.knowledgeBaseType = knowledgeBaseType;
    }

    public Knowledge getValue() {
        return value;
    }

    public void setValue(Knowledge value) {
        this.value = value;
    }

    public Affix getPrefix() {
        return prefix;
    }

    public void setPrefix(Affix prefix) {
        this.prefix = prefix;
    }

    public Affix getSuffix() {
        return suffix;
    }

    public void setSuffix(Affix suffix) {
        this.suffix = suffix;
    }
}

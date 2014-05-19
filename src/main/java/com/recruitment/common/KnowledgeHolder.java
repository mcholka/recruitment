package com.recruitment.common;

import com.recruitment.entity.Archetype;
import com.recruitment.entity.KnowledgeBaseType;


/**
 * Created by mcholka on 2014-03-27. Enjoy!
 */
public class KnowledgeHolder {

    public KnowledgeHolder() {
        values = new StringBuilder();
    }

    private StringBuilder values;

    public void appendFullProcessed(String value){
        values.append(value).
                append(";");
    }

    public void append(String value, Archetype archetype) {
        append(value, archetype, archetype.getKnowledgeBaseType());
    }

    public void append(String value, Archetype archetype, KnowledgeBaseType knowledgeBaseType) {
        values.append("[").
                append(knowledgeBaseType.getId()).
                append("]{").
                append(archetype.getId()).
                append("}").
                append(value).
                append(";");
    }

    public String toString(){
        String finalValue = values.toString();
        if(finalValue.endsWith(";")){
            finalValue = finalValue.substring(0, finalValue.length() - 1);
        }
        return finalValue;
    }
}
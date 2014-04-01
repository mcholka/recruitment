package com.recruitment.entity;

import com.recruitment.common.Archetype;
import com.recruitment.common.KnowledgeBaseType;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by mcholka on 2014-03-26. Enjoy!
 */
@Entity
@NamedQueries({
        @NamedQuery(name = "Knowledge.getKnowledgeByBaseType", query = "SELECT i.value FROM Knowledge i WHERE i.knowledgeBaseType = :baseType")
})
public class Knowledge implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "knowledgeGenerator")
    @SequenceGenerator(name = "knowledgeGenerator", sequenceName = "knowledge_id_seq", allocationSize = 1)
    private Long id;

    private String value;

    @Enumerated(EnumType.STRING)
    private KnowledgeBaseType knowledgeBaseType;

    @Enumerated(EnumType.STRING)
    private Archetype archetype;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public KnowledgeBaseType getKnowledgeBaseType() {
        return knowledgeBaseType;
    }

    public void setKnowledgeBaseType(KnowledgeBaseType knowledgeBaseType) {
        this.knowledgeBaseType = knowledgeBaseType;
    }

    public Archetype getArchetype() {
        return archetype;
    }

    public void setArchetype(Archetype archetype) {
        if(knowledgeBaseType == null || !knowledgeBaseType.getArchetypes().contains(archetype)){
            throw new IllegalArgumentException("Archetype: " + archetype + " can't be stored to knowledge with base type: " + knowledgeBaseType);
        }
        this.archetype = archetype;
    }
}

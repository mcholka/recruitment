package com.recruitment.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by mcholka on 2014-03-26. Enjoy!
 */
@Entity
@Table(uniqueConstraints =  {
        @UniqueConstraint( columnNames = {"knowledgeBaseType", "value"})
})
@NamedQueries({
        @NamedQuery(name = "Knowledge.getKnowledgeByBaseType", query = "SELECT i FROM Knowledge i WHERE i.knowledgeBaseType.id = :baseType"),
        @NamedQuery(name = "Knowledge.getKnowledgeByArchetype", query = "SELECT i FROM Knowledge i WHERE i.archetype  = :archetype")
})
public class Knowledge implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "knowledgeGenerator")
    @SequenceGenerator(name = "knowledgeGenerator", sequenceName = "knowledge_id_seq", allocationSize = 1)
    private Long id;

    private String value;

    @ManyToOne
    private KnowledgeBaseType knowledgeBaseType;

    @ManyToOne
    private Archetype archetype;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Affix> affixes;

    private BigDecimal points;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Affix> getAffixes() {
        return affixes;
    }

    public void setAffixes(List<Affix> affixes) {
        this.affixes = affixes;
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

    public BigDecimal getPoints() {
        return points;
    }

    public void setPoints(BigDecimal points) {
        this.points = points;
    }

    public Archetype getArchetype() {
        return archetype;
    }

    public void setArchetype(Archetype archetype) {
        this.archetype = archetype;
    }
}

package com.recruitment.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by mcholka on 2014-05-16. Enjoy!
 */
@Entity
@NamedQueries({
        @NamedQuery(name = "KnowledgeBaseType.findAllKnowledgeBaseTypes", query = "" +
                "SELECT i FROM KnowledgeBaseType i"
        )
})
public class KnowledgeBaseType implements Serializable {
    @Id
    private String id;

    private Date createTime;

    private BigDecimal points;

    @OneToMany
    List<Archetype> archetypes;

    @PrePersist
    public void prePersist(){
        createTime = new Date();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public BigDecimal getPoints() {
        return points;
    }

    public void setPoints(BigDecimal points) {
        this.points = points;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public List<Archetype> getArchetypes() {
        return archetypes;
    }

    public void setArchetypes(List<Archetype> archetypes) {
        this.archetypes = archetypes;
    }
}

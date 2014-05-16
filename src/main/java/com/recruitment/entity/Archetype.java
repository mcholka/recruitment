package com.recruitment.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by mcholka on 2014-05-16. Enjoy!
 */
@Entity
@NamedQueries({
        @NamedQuery(name = "Archetype.findAllArchetypes", query = "" +
                "SELECT i FROM Archetype i"
        )
})
public class Archetype implements Serializable {
    @Id
    private String id;

    private Date createTime;

    private BigDecimal points;

    @ManyToOne
    private KnowledgeBaseType knowledgeBaseType;

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

    public KnowledgeBaseType getKnowledgeBaseType() {
        return knowledgeBaseType;
    }

    public void setKnowledgeBaseType(KnowledgeBaseType knowledgeBaseType) {
        this.knowledgeBaseType = knowledgeBaseType;
    }
}

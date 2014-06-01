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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Archetype archetype = (Archetype) o;

        if (createTime != null ? !createTime.equals(archetype.createTime) : archetype.createTime != null) return false;
        if (id != null ? !id.equals(archetype.id) : archetype.id != null) return false;
        if (knowledgeBaseType != null ? !knowledgeBaseType.equals(archetype.knowledgeBaseType) : archetype.knowledgeBaseType != null)
            return false;
        if (points != null ? !points.equals(archetype.points) : archetype.points != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (points != null ? points.hashCode() : 0);
        result = 31 * result + (knowledgeBaseType != null ? knowledgeBaseType.hashCode() : 0);
        return result;
    }
}

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
        @NamedQuery(name = "KnowledgeBaseType.getAll", query = "" +
                "SELECT i FROM KnowledgeBaseType i"
        )
})
public class KnowledgeBaseType implements Serializable {
    @Id
    private String id;

    private Date createTime;

    private BigDecimal points;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        KnowledgeBaseType that = (KnowledgeBaseType) o;

        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (points != null ? !points.equals(that.points) : that.points != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (points != null ? points.hashCode() : 0);
        return result;
    }
}

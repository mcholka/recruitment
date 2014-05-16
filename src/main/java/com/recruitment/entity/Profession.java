package com.recruitment.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by mcholka on 2014-03-25. Enjoy!
 */
@Entity
@NamedQueries({
        @NamedQuery(name = "Profession.getAllProfessions", query = "SELECT i.id FROM Profession i")
})
public class Profession implements Serializable {

    @Id
    private String id;

    private String description;

    private Date createTime;

    @ManyToMany
    private List<Knowledge> knowledgeList;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public List<Knowledge> getKnowledgeList() {
        return knowledgeList;
    }

    public void setKnowledgeList(List<Knowledge> knowledgeList) {
        this.knowledgeList = knowledgeList;
    }
}

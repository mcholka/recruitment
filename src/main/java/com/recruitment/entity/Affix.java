package com.recruitment.entity;

import com.recruitment.common.AffixType;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by mcholka on 2014-05-15. Enjoy!
 */
@Entity
public class Affix implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "affixGenerator")
    @SequenceGenerator(name = "affixGenerator", sequenceName = "affix_id_seq", allocationSize = 1)
    private Long id;

    private String value;

    private boolean required;

    @Enumerated(EnumType.STRING)
    private AffixType type;

    private BigDecimal points;

    private Date createTime;

    @PrePersist
    public void prePersist(){
        createTime = new Date();
    }

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

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public AffixType getType() {
        return type;
    }

    public void setType(AffixType type) {
        this.type = type;
    }

    public BigDecimal getPoints() {
        return points;
    }

    public void setPoints(BigDecimal points) {
        this.points = points;
    }

    public Date getCreateTime() {
        return createTime;
    }
}

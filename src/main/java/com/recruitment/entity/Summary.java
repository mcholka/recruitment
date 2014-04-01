package com.recruitment.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by mcholka on 2014-03-25. Enjoy!
 */
@Entity
public class Summary implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "summaryGenerator")
    @SequenceGenerator(name = "summaryGenerator", sequenceName = "summary_id_seq", allocationSize = 1)
    private Long id;

    @OneToOne
    private CustomerData customerData;

    private BigDecimal keywordRelevance;

    private BigDecimal educationRelevance;

    private BigDecimal technicalRelevance;

    private BigDecimal summaryRelevance;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CustomerData getCustomerData() {
        return customerData;
    }

    public void setCustomerData(CustomerData customerData) {
        this.customerData = customerData;
    }

    public BigDecimal getKeywordRelevance() {
        return keywordRelevance;
    }

    public void setKeywordRelevance(BigDecimal keywordRelevance) {
        this.keywordRelevance = keywordRelevance;
    }

    public BigDecimal getEducationRelevance() {
        return educationRelevance;
    }

    public void setEducationRelevance(BigDecimal educationRelevance) {
        this.educationRelevance = educationRelevance;
    }

    public BigDecimal getTechnicalRelevance() {
        return technicalRelevance;
    }

    public void setTechnicalRelevance(BigDecimal technicalRelevance) {
        this.technicalRelevance = technicalRelevance;
    }

    public BigDecimal getSummaryRelevance() {
        return summaryRelevance;
    }

    public void setSummaryRelevance(BigDecimal summaryRelevance) {
        this.summaryRelevance = summaryRelevance;
    }
}

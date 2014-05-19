package com.recruitment.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by mcholka on 2014-03-25. Enjoy!
 */
@Entity
public class RatedData implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "summaryGenerator")
    @SequenceGenerator(name = "summaryGenerator", sequenceName = "summary_id_seq", allocationSize = 1)
    private Long id;

    @OneToOne
    private CustomerData customerData;

    private BigDecimal sumPoints;

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

    public BigDecimal getSumPoints() {
        return sumPoints;
    }

    public void setSumPoints(BigDecimal sumPoints) {
        this.sumPoints = sumPoints;
    }
}

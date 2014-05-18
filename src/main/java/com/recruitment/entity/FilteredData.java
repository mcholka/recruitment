package com.recruitment.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by mcholka on 2014-04-25. Enjoy!
 */
@Entity
public class FilteredData implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "filteredDataGenerator")
    @SequenceGenerator(name = "filteredDataGenerator", sequenceName = "filtereddata_id_seq", allocationSize = 1)
    private Long id;

    @OneToOne
    private CustomerData customerData;

    @Column(length = 10000)
    private String value;

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

    public CustomerData getCustomerData() {
        return customerData;
    }

    public void setCustomerData(CustomerData customerData) {
        this.customerData = customerData;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

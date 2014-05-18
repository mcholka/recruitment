package com.recruitment.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by mcholka on 2014-03-26. Enjoy!
 */
@Entity
@NamedQueries({
        @NamedQuery(name = "ExtractedData.findExtractedDataToFilter", query = "" +
                "SELECT i FROM ExtractedData i " +
                "WHERE i.customerData.processStatus = :status"
        )
})
public class ExtractedData implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "extractedDataGenerator")
    @SequenceGenerator(name = "extractedDataGenerator", sequenceName = "extracteddata_id_seq", allocationSize = 1)
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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Date getCreateTime() {
        return createTime;
    }
}

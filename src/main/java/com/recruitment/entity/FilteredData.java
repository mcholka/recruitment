package com.recruitment.entity;

import javax.persistence.*;
import java.io.Serializable;

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

    private String experience;
    private String education;
    private String skills;
    private String interest;

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

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }
}

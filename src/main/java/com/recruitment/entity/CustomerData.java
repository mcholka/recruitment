package com.recruitment.entity;

import com.recruitment.common.ProcessStatus;
import com.recruitment.common.VerifyStatus;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by mcholka on 2014-03-23. Enjoy!
 */
@Entity
@NamedQueries({
        @NamedQuery(name = "CustomerData.getCustomersByStatus", query = "" +
                "SELECT i FROM CustomerData i " +
                "WHERE i.processStatus = :status"
        ),
        @NamedQuery( name = "CustomerData.getAllCustomers", query = "SELECT i FROM CustomerData i")
})
public class CustomerData implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customerDataGenerator")
    @SequenceGenerator(name = "customerDataGenerator", sequenceName = "customerdata_id_seq", allocationSize = 1)
    private Long id;

    private Date createTime;

    @Version
    private Date lastModifiedTime;

    private String firstName;

    private String lastName;

    @ManyToOne
    private Profession profession;

    @OneToOne(mappedBy = "customerData")
    private ExtractedData extractedData;

    @OneToOne(mappedBy = "customerData")
    private FilteredData filteredData;

    @OneToOne(mappedBy = "customerData")
    private RatedData ratedData;

    @Enumerated(EnumType.STRING)
    private ProcessStatus processStatus;

    @Column(length = 10000000)
    private byte[] storedCv;

    private String email;

    private String fileName;

    @Enumerated(EnumType.STRING)
    private VerifyStatus verifyStatus;

    @PrePersist
    public void init(){
        createTime = new Date();
        lastModifiedTime = new Date();
        processStatus = ProcessStatus.NEW;
        verifyStatus = VerifyStatus.NOT_VERIFIED;
    }

    @PreUpdate
    public void preUpdate(){
        lastModifiedTime = new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Date getLastModifiedTime() {
        return lastModifiedTime;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Profession getProfession() {
        return profession;
    }

    public void setProfession(Profession profession) {
        this.profession = profession;
    }

    public ProcessStatus getProcessStatus() {
        return processStatus;
    }

    public void setProcessStatus(ProcessStatus processStatus) {
        this.processStatus = processStatus;
    }

    public byte[] getStoredCv() {
        return storedCv;
    }

    public void setStoredCv(byte[] storedCv) {
        this.storedCv = storedCv;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }


    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

    public ExtractedData getExtractedData() {
        return extractedData;
    }

    public void setExtractedData(ExtractedData extractedData) {
        this.extractedData = extractedData;
    }

    public RatedData getRatedData() {
        return ratedData;
    }

    public void setRatedData(RatedData ratedData) {
        this.ratedData = ratedData;
    }

    public FilteredData getFilteredData() {
        return filteredData;
    }

    public void setFilteredData(FilteredData filteredData) {
        this.filteredData = filteredData;
    }

    public VerifyStatus getVerifyStatus() {
        return verifyStatus;
    }

    public void setVerifyStatus(VerifyStatus verifyStatus) {
        this.verifyStatus = verifyStatus;
    }
}

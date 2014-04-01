package com.recruitment.entity;

import com.recruitment.common.Status;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by mcholka on 2014-03-23. Enjoy!
 */
@Entity
@NamedQueries({
        @NamedQuery(name = "CustomerData.getCustomersForDataExtract", query = "" +
                "SELECT i FROM CustomerData i " +
                "WHERE i.status = :status"
        )
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

    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(length = 1000000)
    private byte[] storedCv;

    private String email;

    private String fileName;

    @PrePersist
    public void init(){
        createTime = new Date();
        lastModifiedTime = new Date();
        status = Status.NEW;
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
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
}

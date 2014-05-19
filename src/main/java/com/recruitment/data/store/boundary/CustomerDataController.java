package com.recruitment.data.store.boundary;

import com.recruitment.common.FileStoreException;
import com.recruitment.common.RecruitmentUtils;
import com.recruitment.crud.ProfessionFinder;
import com.recruitment.crud.StorageManager;
import com.recruitment.data.store.control.CustomerDataFactory;
import com.recruitment.data.store.control.FileCreator;
import com.recruitment.data.store.control.SelectItemComparator;
import com.recruitment.entity.CustomerData;
import org.apache.log4j.Logger;
import org.primefaces.model.UploadedFile;

import javax.faces.application.FacesMessage;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by mcholka on 2014-03- Enjoy!
 */
@Named
@ViewScoped
public class CustomerDataController implements Serializable {
    private static final Logger logger = Logger.getLogger(CustomerDataController.class);

    @Inject
    FileCreator fileCreator;
    @Inject
    StorageManager storageManager;
    @Inject
    CustomerDataFactory customerDataFactory;
    @Inject
    ProfessionFinder professionFinder;

    private String firstName;
    private String lastName;
    private String profession;
    private String email;
    private UploadedFile file;

    public void storeData() throws IOException {
        logger.info("Hello " + lastName + " " + firstName + "! Try to store file: " + file.getFileName());
        try {
            CustomerData customerData = getCustomerData();
            String fileName = storeToFile(file.getFileName(), customerData.getStoredCv());
            customerData.setFileName(fileName);
            storeToDataBase(customerData);
            RecruitmentUtils.redirect("/confirm.recruitment");
        } catch (Exception e){
            logger.error(e.getMessage(), e);
            RecruitmentUtils.logMessage("Nie można zapisać danych, spróbuj ponownie", FacesMessage.SEVERITY_ERROR);
        }
    }

    public List<SelectItem> getProfessions(){
        List<SelectItem> items = new ArrayList<>();
        List<String> professionsNames = getProfessionsFromDb();
        for(String profession: professionsNames) {
            items.add(new SelectItem(profession, profession));
        }
        Collections.sort(items, new SelectItemComparator());
        items.add(0, new SelectItem("", ""));
        return items;
    }

    private List<String> getProfessionsFromDb() {
        return professionFinder.getAllProfessions();
    }

    private CustomerData getCustomerData() {
        return customerDataFactory.buildCustomerData(firstName, lastName, profession, email, file);
    }

    private String storeToFile(String fileName, byte[] cv) throws FileStoreException {
        return fileCreator.persistFile(fileName, cv);
    }

    private void storeToDataBase(CustomerData customerData) throws FileStoreException {
        storageManager.persist(customerData);
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

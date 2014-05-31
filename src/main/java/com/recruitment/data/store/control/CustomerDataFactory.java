package com.recruitment.data.store.control;

import com.recruitment.crud.StorageManager;
import com.recruitment.entity.CustomerData;
import com.recruitment.entity.Profession;
import org.apache.commons.io.IOUtils;
import org.primefaces.model.UploadedFile;

import javax.inject.Inject;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by mcholka on 2014-03-25. Enjoy!
 */
public class CustomerDataFactory {

    @Inject
    StorageManager storageManager;

    public CustomerData buildCustomerData(String firstName, String lastName, String professionName, String email, UploadedFile file){
        Profession profession = findByName(professionName);
        byte[] cv = getBytes(file);
        CustomerData customerData = new CustomerData();
        customerData.setFirstName(firstName);
        customerData.setLastName(lastName);
        customerData.setStoredCv(cv);
        customerData.setProfession(profession);
        customerData.setEmail(email);
        return customerData;
    }

    private Profession findByName(String professionName) {
        return storageManager.findByID(professionName, Profession.class);
    }

    private byte[] getBytes(UploadedFile file) {
        try (InputStream inputStream = file.getInputstream()){
            return IOUtils.toByteArray(inputStream);
        } catch (IOException e) {
            throw new IllegalArgumentException(e.getMessage(), e);
        }
    }
}

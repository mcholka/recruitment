package com.recruitment.admin.customer;

import com.recruitment.common.RecruitmentUtils;
import com.recruitment.common.VerifyStatus;
import com.recruitment.crud.StorageManager;
import com.recruitment.entity.CustomerData;
import org.apache.log4j.Logger;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mcholka on 2014-05-22. Enjoy!
 */
@Named
@ViewScoped
public class CustomerController implements Serializable {
    private static final Logger logger = Logger.getLogger(CustomerController.class);
    @Inject
    private CustomerDataModel customerDataModel;
    @Inject
    private StorageManager storageManager;

    private StreamedContent streamedContent;

    public void loadPDF(CustomerData customerData){
        logger.info("load pdf for customer: " + customerData.getId());
        InputStream iS = new ByteArrayInputStream(customerData.getStoredCv());
        streamedContent = new DefaultStreamedContent(iS, RecruitmentUtils.REQUIRED_FILE_FORMAT, customerData.getFileName());
    }

    public List<SelectItem> getVerifyStatusList() {
        List<SelectItem> items = new ArrayList<SelectItem>();
        for(VerifyStatus verifyStatus: VerifyStatus.values()) {
            items.add(new SelectItem(verifyStatus, verifyStatus.name())) ;
        }
        return items;
    }

    public void update(CustomerData customerData){
        storageManager.update(customerData);
    }

    public StreamedContent getStreamedContent() {
        return streamedContent;
    }

    public void setStreamedContent(StreamedContent streamedContent) {
        this.streamedContent = streamedContent;
    }

    public CustomerDataModel getCustomerDataModel() {
        return customerDataModel;
    }

    public void setCustomerDataModel(CustomerDataModel customerDataModel) {
        this.customerDataModel = customerDataModel;
    }
}

package com.recruitment.admin.customer;

import com.recruitment.crud.CustomerDataFinder;
import com.recruitment.entity.CustomerData;
import org.apache.log4j.Logger;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.List;

/**
 * Created by mcholka on 2014-05-22. Enjoy!
 */
@Named
@SessionScoped
public class CustomerController implements Serializable {
    private static final Logger logger = Logger.getLogger(CustomerController.class);

    @Inject
    private CustomerDataFinder customerDataFinder;

    private CustomerData customerData;
    private StreamedContent streamedContent;

    public List<CustomerData> getAllCustomers(){
        return customerDataFinder.getAllCustomers();
    }

    public void loadPDF(){
        logger.info("load pdf for customer: " + customerData.getId());
        logger.info("Length of content: " +     customerData.getStoredCv().length);
        InputStream iS = new ByteArrayInputStream(customerData.getStoredCv());
        streamedContent = new DefaultStreamedContent(iS, "application/pdf");
    }

    public CustomerData getCustomerData() {
        return customerData;
    }

    public void setCustomerData(CustomerData customerData) {
        this.customerData = customerData;
    }

    public StreamedContent getStreamedContent() {
        return streamedContent;
    }

    public void setStreamedContent(StreamedContent streamedContent) {
        this.streamedContent = streamedContent;
    }
}

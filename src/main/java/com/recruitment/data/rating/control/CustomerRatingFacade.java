package com.recruitment.data.rating.control;

import com.recruitment.common.KnowledgeWrapper;
import com.recruitment.common.KnowledgeWrapperFactory;
import com.recruitment.common.ProcessStatus;
import com.recruitment.crud.StorageManager;
import com.recruitment.data.extract.control.EntityMapper;
import com.recruitment.entity.CustomerData;
import com.recruitment.entity.FilteredData;
import com.recruitment.entity.RatedData;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mcholka on 2014-05-18. Enjoy!
 */
public class CustomerRatingFacade {

    @Inject
    KnowledgeWrapperFactory knowledgeWrapperFactory;
    @Inject
    EntityMapper entityMapper;
    @Inject
    StorageManager storageManager;

    public void processRating(FilteredData filteredData){
        List<KnowledgeWrapper> knowledgeWrappers = mappingStringToWrappers(filteredData.getValue());
        BigDecimal points = sumPoints(knowledgeWrappers);
        RatedData ratedData = buildRatedData(points, filteredData.getCustomerData());
        store(ratedData);
        updateStatus(ratedData.getCustomerData());
    }

    private List<KnowledgeWrapper> mappingStringToWrappers(String value) {
        String[] values = value.split(";");
        List<KnowledgeWrapper> knowledgeWrappers = new ArrayList<>();
        for(String valueToWrap : values){
            knowledgeWrappers.add(buildWrapper(valueToWrap));
        }
        return knowledgeWrappers;
    }

    private KnowledgeWrapper buildWrapper(String value) {
        return knowledgeWrapperFactory.mappingToWrapper(value);
    }

    private BigDecimal sumPoints(List<KnowledgeWrapper> knowledgeWrappers) {
        BigDecimal sum = BigDecimal.ZERO;
        for(KnowledgeWrapper knowledgeWrapper : knowledgeWrappers){
            sum = sum.add(knowledgeWrapper.sumPoints());
        }
        return sum;
    }

    private RatedData buildRatedData(BigDecimal points, CustomerData customerData) {
        return entityMapper.mappingToRatedData(points, customerData);
    }

    private void store(RatedData ratedData) {
        storageManager.persist(ratedData);
    }

    private void updateStatus(CustomerData customerData) {
        customerData.setProcessStatus(ProcessStatus.RATED);
    }
}

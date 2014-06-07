package com.recruitment.admin.customer;

import com.recruitment.common.KnowledgeWrapper;
import com.recruitment.common.KnowledgeWrapperFactory;
import com.recruitment.common.RecruitmentUtils;
import com.recruitment.entity.CustomerData;
import com.recruitment.entity.FilteredData;
import org.apache.log4j.Logger;
import org.primefaces.model.tagcloud.DefaultTagCloudItem;
import org.primefaces.model.tagcloud.DefaultTagCloudModel;
import org.primefaces.model.tagcloud.TagCloudModel;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by mcholka on 2014-06-07. Enjoy!
 */
public class CloudModelBuilder {
    private static final Logger logger = Logger.getLogger(CloudModelBuilder.class);

    @Inject
    private KnowledgeWrapperFactory knowledgeWrapperFactory;

    private Random random = new Random();

    private static final int MIN = 1;
    private static final int MAX = 10;

    public TagCloudModel build(CustomerData customerData){
        FilteredData filteredData = customerData.getFilteredData();
        if(filteredData == null || RecruitmentUtils.emptyString(filteredData.getValue())){
            return noDataFound();
        }
        String[] values = filteredData.getValue().split(";");
        List<KnowledgeWrapper> knowledgeWrappers = new ArrayList<>();
        for(String value : values){
            knowledgeWrappers.add(knowledgeWrapperFactory.mappingToWrapper(value));
        }
        List<DefaultTagCloudItem> defaultTagCloudItems = new ArrayList<>();
        TagCloudModel model = new DefaultTagCloudModel();
        for(KnowledgeWrapper knowledgeWrapper : knowledgeWrappers){
            defaultTagCloudItems.add(buildItem(knowledgeWrapper));
        }
        for(DefaultTagCloudItem item : defaultTagCloudItems){
            model.addTag(item);
        }
        return model;
    }

    private TagCloudModel noDataFound() {
        DefaultTagCloudItem empty = new DefaultTagCloudItem("Brak Danych...", 1);
        TagCloudModel model = new DefaultTagCloudModel();
        model.addTag(empty);
        return model;
    }

    private DefaultTagCloudItem buildItem(KnowledgeWrapper knowledgeWrapper) {
        String value = knowledgeWrapper.getValue().getValue();
        if(knowledgeWrapper.getPrefix() != null) {
            value = knowledgeWrapper.getPrefix().getValue() + " " + value;
        }
        if(knowledgeWrapper.getSuffix() != null) {
            value = value + " " + knowledgeWrapper.getSuffix().getValue();
        }
        Integer randomInt = random.nextInt(MAX - MIN) + MIN;
        logger.info("Ädd value " + value + " " + randomInt);
        return new DefaultTagCloudItem(value, randomInt);
    }
}

package com.recruitment.admin.customer;

import com.recruitment.common.RecruitmentUtils;
import com.recruitment.crud.CustomerDataFinder;
import com.recruitment.entity.CustomerData;
import org.apache.log4j.Logger;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SelectableDataModel;
import org.primefaces.model.SortOrder;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by mcholka on 2014-05-31. Enjoy!
 */
public class CustomerDataModel extends LazyDataModel<CustomerData> implements SelectableDataModel<CustomerData> {
    private static final Logger logger = Logger.getLogger(CustomerDataModel.class);

    @Inject
    private CustomerDataFinder customerDataFinder;

    private List<CustomerData> customerDataList = new ArrayList<>();

    @Override
    public List<CustomerData> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String,String> filters) {
        String query;
        if(RecruitmentUtils.emptyString(sortField)) {
            query = "SELECT i FROM CustomerData i ORDER BY i.createTime DESC";
        } else if(SortOrder.ASCENDING.equals(sortOrder)){
            query = "SELECT i FROM CustomerData i ORDER BY " + sortField + " " + sortOrder.name().substring(0, 3);
        } else {
            query = "SELECT i FROM CustomerData i ORDER BY " + sortField + " " + sortOrder.name().substring(0, 4);
        }
        logger.info("Search by query: " + query + " First: " + first + " PageSize: " + pageSize);

        customerDataList = customerDataFinder.findByQuery(query, first, pageSize);

        setRowCount(customerDataList.size());
        return customerDataList;
    }

    @Override
    public Object getRowKey(CustomerData row) {
        return row.getId();
    }

    @Override
    public CustomerData getRowData(String rowKey) {
        Long id = Long.parseLong(rowKey);
        for(CustomerData row: customerDataList) {
            if (row.getId().equals(id)) {
                return row;
            }
        }
        return null;
    }
}


package com.recruitment.admin.knowledge;

import com.recruitment.common.RecruitmentUtils;
import com.recruitment.crud.KnowledgeFinder;
import com.recruitment.entity.Knowledge;
import org.apache.log4j.Logger;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SelectableDataModel;
import org.primefaces.model.SortOrder;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by mcholka on 2014-06-01. Enjoy!
 */
public class KnowledgeDataModel extends LazyDataModel<Knowledge> implements SelectableDataModel<Knowledge> {
    private static final Logger logger = Logger.getLogger(KnowledgeDataModel.class);

    @Inject
    private KnowledgeFinder knowledgeFinder;

    private List<Knowledge> knowledges = new ArrayList<>();

    @Override
    public List<Knowledge> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String,String> filters) {
        String query = buildQuery(sortField, sortOrder);
        logger.info("Search by query: " + query + " First: " + first + " PageSize: " + pageSize);

        knowledges = knowledgeFinder.findByQuery(query, first, pageSize);

        setRowCount(knowledges.size());
        return knowledges;
    }

    private String buildQuery(String sortField, SortOrder sortOrder) {
        if(RecruitmentUtils.emptyString(sortField)) {
            return "SELECT i FROM Knowledge i ORDER BY i.createTime DESC";
        }
        if(SortOrder.ASCENDING.equals(sortOrder)){
            return "SELECT i FROM Knowledge i ORDER BY " + sortField + " " + sortOrder.name().substring(0, 3);
        }
        return "SELECT i FROM Knowledge i ORDER BY " + sortField + " " + sortOrder.name().substring(0, 4);
    }

    @Override
    public Object getRowKey(Knowledge row) {
        return row.getId();
    }

    @Override
    public Knowledge getRowData(String rowKey) {
        Long id = Long.parseLong(rowKey);

        for(Knowledge row: knowledges) {
            if (row.getId().equals(id)) {
                return row;
            }
        }
        return null;
    }
}

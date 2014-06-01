package com.recruitment.admin.basetype;

import com.recruitment.common.RecruitmentUtils;
import com.recruitment.crud.KnowledgeBaseTypeFinder;
import com.recruitment.entity.KnowledgeBaseType;
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
public class BaseTypeDataModel extends LazyDataModel<KnowledgeBaseType> implements SelectableDataModel<KnowledgeBaseType> {
    private static final Logger logger = Logger.getLogger(BaseTypeDataModel.class);

    @Inject
    private KnowledgeBaseTypeFinder knowledgeBaseTypeFinder;

    private List<KnowledgeBaseType> knowledgeBaseTypes = new ArrayList<>();

    @Override
    public List<KnowledgeBaseType> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String,String> filters) {
        String query = buildQuery(sortField, sortOrder);
        logger.info("Search by query: " + query + " First: " + first + " PageSize: " + pageSize);

        knowledgeBaseTypes = knowledgeBaseTypeFinder.findByQuery(query, first, pageSize);

        setRowCount(knowledgeBaseTypes.size());
        return knowledgeBaseTypes;
    }

    private String buildQuery(String sortField, SortOrder sortOrder) {
        if(RecruitmentUtils.emptyString(sortField)) {
            return "SELECT i FROM KnowledgeBaseType i ORDER BY i.createTime DESC";
        }
        if(SortOrder.ASCENDING.equals(sortOrder)){
            return "SELECT i FROM KnowledgeBaseType i ORDER BY " + sortField + " " + sortOrder.name().substring(0, 3);
        }
        return "SELECT i FROM KnowledgeBaseType i ORDER BY " + sortField + " " + sortOrder.name().substring(0, 4);
    }

    @Override
    public Object getRowKey(KnowledgeBaseType row) {
        return row.getId();
    }

    @Override
    public KnowledgeBaseType getRowData(String rowKey) {
        for(KnowledgeBaseType row: knowledgeBaseTypes) {
            if (row.getId().equals(rowKey)) {
                return row;
            }
        }
        return null;
    }
}

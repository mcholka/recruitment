package com.recruitment.admin.archetype;

import com.recruitment.common.RecruitmentUtils;
import com.recruitment.crud.ArchetypeFinder;
import com.recruitment.entity.Archetype;
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
public class ArchetypeDataModel extends LazyDataModel<Archetype> implements SelectableDataModel<Archetype> {
    private static final Logger logger = Logger.getLogger(ArchetypeDataModel.class);

    @Inject
    private ArchetypeFinder archetypeFinder;

    private List<Archetype> archetypeList = new ArrayList<>();

    @Override
    public List<Archetype> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String,String> filters) {
        String query = buildQuery(sortField, sortOrder);
        logger.info("Search by query: " + query + " First: " + first + " PageSize: " + pageSize);

        archetypeList = archetypeFinder.findByQuery(query, first, pageSize);

        setRowCount(archetypeList.size());
        return archetypeList;
    }

    private String buildQuery(String sortField, SortOrder sortOrder) {
        if(RecruitmentUtils.emptyString(sortField)) {
            return "SELECT i FROM Archetype i ORDER BY i.createTime DESC";
        }
        if(SortOrder.ASCENDING.equals(sortOrder)){
            return "SELECT i FROM Archetype i ORDER BY " + sortField + " " + sortOrder.name().substring(0, 3);
        }
        return "SELECT i FROM Archetype i ORDER BY " + sortField + " " + sortOrder.name().substring(0, 4);
    }

    @Override
    public Object getRowKey(Archetype row) {
        return row.getId();
    }

    @Override
    public Archetype getRowData(String rowKey) {
        for(Archetype row: archetypeList) {
            if (row.getId().equals(rowKey)) {
                return row;
            }
        }
        return null;
    }
}

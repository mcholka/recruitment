package com.recruitment.admin.profession;

import com.recruitment.common.RecruitmentUtils;
import com.recruitment.crud.ProfessionFinder;
import com.recruitment.entity.Profession;
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
public class ProfessionDataModel extends LazyDataModel<Profession> implements SelectableDataModel<Profession> {
    private static final Logger logger = Logger.getLogger(ProfessionDataModel.class);

    @Inject
    private ProfessionFinder professionFinder;

    private List<Profession> professions = new ArrayList<>();

    @Override
    public List<Profession> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String,String> filters) {
        String query = buildQuery(sortField, sortOrder);
        logger.info("Search by query: " + query + " First: " + first + " PageSize: " + pageSize);

        professions = professionFinder.findByQuery(query, first, pageSize);

        setRowCount(professions.size());
        return professions;
    }

    private String buildQuery(String sortField, SortOrder sortOrder) {
        if(RecruitmentUtils.emptyString(sortField)) {
            return "SELECT i FROM Profession i ORDER BY i.createTime DESC";
        }
        if(SortOrder.ASCENDING.equals(sortOrder)){
            return "SELECT i FROM Profession i ORDER BY " + sortField + " " + sortOrder.name().substring(0, 3);
        }
        return "SELECT i FROM Profession i ORDER BY " + sortField + " " + sortOrder.name().substring(0, 4);
    }

    @Override
    public Object getRowKey(Profession row) {
        return row.getId();
    }

    @Override
    public Profession getRowData(String rowKey) {
        for(Profession row: professions) {
            if (row.getId().equals(rowKey)) {
                return row;
            }
        }
        return null;
    }
}

package com.recruitment.admin.knowledge;

import com.recruitment.common.RecruitmentUtils;
import com.recruitment.crud.StorageManager;
import com.recruitment.entity.Knowledge;
import org.primefaces.event.RowEditEvent;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

/**
 * Created by mcholka on 2014-06-01. Enjoy!
 */
@Named
@ViewScoped
public class KnowledgeController implements Serializable {
    @Inject
    private KnowledgeDataModel knowledgeDataModel;
    @Inject
    private StorageManager storageManager;

    public void onRowEdit(RowEditEvent event){
        Knowledge knowledge = (Knowledge) event.getObject();
        storageManager.update(knowledge);
        RecruitmentUtils.logMessage("Wartość zaktualizowana");
    }

    public KnowledgeDataModel getKnowledgeDataModel() {
        return knowledgeDataModel;
    }

    public void setKnowledgeDataModel(KnowledgeDataModel knowledgeDataModel) {
        this.knowledgeDataModel = knowledgeDataModel;
    }
}

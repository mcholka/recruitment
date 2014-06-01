package com.recruitment.admin.basetype;

import com.recruitment.common.RecruitmentUtils;
import com.recruitment.crud.StorageManager;
import com.recruitment.entity.KnowledgeBaseType;
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
public class BaseTypeController implements Serializable {
    @Inject
    private BaseTypeDataModel baseTypeDataModel;
    @Inject
    private StorageManager storageManager;

    public void onRowEdit(RowEditEvent event){
        KnowledgeBaseType knowledgeBaseType = (KnowledgeBaseType) event.getObject();
        storageManager.update(knowledgeBaseType);
        RecruitmentUtils.logMessage("Typ bazowy " + knowledgeBaseType.getId() + " zaktualizowany");
    }

    public BaseTypeDataModel getBaseTypeDataModel() {
        return baseTypeDataModel;
    }

    public void setBaseTypeDataModel(BaseTypeDataModel baseTypeDataModel) {
        this.baseTypeDataModel = baseTypeDataModel;
    }
}

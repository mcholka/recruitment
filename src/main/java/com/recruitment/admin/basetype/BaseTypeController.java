package com.recruitment.admin.basetype;

import com.recruitment.common.RecruitmentUtils;
import com.recruitment.crud.KnowledgeBaseTypeFinder;
import com.recruitment.crud.StorageManager;
import com.recruitment.entity.KnowledgeBaseType;
import org.primefaces.event.RowEditEvent;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
    @Inject
    private KnowledgeBaseTypeFinder knowledgeBaseTypeFinder;

    private KnowledgeBaseType knowledgeBaseType;

    @PostConstruct
    public void init(){
        knowledgeBaseType = new KnowledgeBaseType();
    }

    public void storeNew(){
        if(validate(knowledgeBaseType)){
            knowledgeBaseType.setId(knowledgeBaseType.getId().toUpperCase());
            storageManager.persist(knowledgeBaseType);
            RecruitmentUtils.logMessage("Typ bazowy został zapisany poprawnie");
        } else {
            RecruitmentUtils.logMessage("Błąd, wypełnij pola poprawnie", FacesMessage.SEVERITY_ERROR);
        }
    }

    private boolean validate(KnowledgeBaseType knowledgeBaseType) {
        return !RecruitmentUtils.emptyString(knowledgeBaseType.getId()) &&
                knowledgeBaseType.getPoints() != null;
    }

    public void onRowEdit(RowEditEvent event){
        KnowledgeBaseType knowledgeBaseType = (KnowledgeBaseType) event.getObject();
        storageManager.update(knowledgeBaseType);
        RecruitmentUtils.logMessage("Typ bazowy " + knowledgeBaseType.getId() + " zaktualizowany");
    }

    public List<SelectItem> getBaseTypes() {
        List<SelectItem> items = new ArrayList<>();
        List<KnowledgeBaseType> knowledgeBaseTypes = getAllKnowledgeBaseTypes();
        for(KnowledgeBaseType knowledgeBaseType: knowledgeBaseTypes) {
            items.add(new SelectItem(knowledgeBaseType, knowledgeBaseType.getId())) ;
        }
        return items;
    }

    private List<KnowledgeBaseType> getAllKnowledgeBaseTypes() {
        return knowledgeBaseTypeFinder.getAll();
    }

    public BaseTypeDataModel getBaseTypeDataModel() {
        return baseTypeDataModel;
    }

    public void setBaseTypeDataModel(BaseTypeDataModel baseTypeDataModel) {
        this.baseTypeDataModel = baseTypeDataModel;
    }

    public KnowledgeBaseType getKnowledgeBaseType() {
        return knowledgeBaseType;
    }

    public void setKnowledgeBaseType(KnowledgeBaseType knowledgeBaseType) {
        this.knowledgeBaseType = knowledgeBaseType;
    }
}

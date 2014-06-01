package com.recruitment.admin.profession;

import com.recruitment.common.RecruitmentUtils;
import com.recruitment.crud.StorageManager;
import com.recruitment.entity.Profession;
import org.primefaces.event.RowEditEvent;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

/**
 * Created by mcholka on 2014-06-01. Enjoy!
 */
@Named
@ViewScoped
public class ProfessionController implements Serializable {
    @Inject
    private ProfessionDataModel professionDataModel;
    @Inject
    private StorageManager storageManager;

    @PostConstruct
    public void init(){
        profession = new Profession();
    }

    private Profession profession;

    public void storeNew(){
        if(validate(profession)){
            storageManager.persist(profession);
            RecruitmentUtils.logMessage("Profesja została zapisana poprawnie");
        } else {
            RecruitmentUtils.logMessage("Błąd, wypełnij pola poprawnie", FacesMessage.SEVERITY_ERROR);
        }
    }

    private boolean validate(Profession profession) {
        return !RecruitmentUtils.emptyString(profession.getId()) &&
                !RecruitmentUtils.emptyString(profession.getDescription());
    }

    public void onRowEdit(RowEditEvent event){
        Profession profession = (Profession) event.getObject();
        storageManager.update(profession);
        RecruitmentUtils.logMessage("Profesja " + profession.getId() + " zaktualizowana");
    }

    public ProfessionDataModel getProfessionDataModel() {
        return professionDataModel;
    }

    public void setProfessionDataModel(ProfessionDataModel professionDataModel) {
        this.professionDataModel = professionDataModel;
    }

    public Profession getProfession() {
        return profession;
    }

    public void setProfession(Profession profession) {
        this.profession = profession;
    }
}

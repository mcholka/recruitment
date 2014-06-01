package com.recruitment.admin.profession;

import com.recruitment.common.RecruitmentUtils;
import com.recruitment.crud.StorageManager;
import com.recruitment.entity.Profession;
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
public class ProfessionController implements Serializable {
    @Inject
    private ProfessionDataModel professionDataModel;
    @Inject
    private StorageManager storageManager;

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
}

package com.recruitment.admin.profession;

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

    public ProfessionDataModel getProfessionDataModel() {
        return professionDataModel;
    }

    public void setProfessionDataModel(ProfessionDataModel professionDataModel) {
        this.professionDataModel = professionDataModel;
    }
}

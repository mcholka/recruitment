package com.recruitment.admin.basetype;

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

    public BaseTypeDataModel getBaseTypeDataModel() {
        return baseTypeDataModel;
    }

    public void setBaseTypeDataModel(BaseTypeDataModel baseTypeDataModel) {
        this.baseTypeDataModel = baseTypeDataModel;
    }
}

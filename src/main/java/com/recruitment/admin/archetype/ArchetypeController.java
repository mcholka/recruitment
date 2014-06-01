package com.recruitment.admin.archetype;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

/**
 * Created by mcholka on 2014-06-01. Enjoy!
 */
@Named
@ViewScoped
public class ArchetypeController implements Serializable {

    @Inject
    private ArchetypeDataModel archetypeDataModel;

    public ArchetypeDataModel getArchetypeDataModel() {
        return archetypeDataModel;
    }

    public void setArchetypeDataModel(ArchetypeDataModel archetypeDataModel) {
        this.archetypeDataModel = archetypeDataModel;
    }
}

package com.recruitment.admin.archetype;

import com.recruitment.common.RecruitmentUtils;
import com.recruitment.crud.StorageManager;
import com.recruitment.entity.Archetype;
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
public class ArchetypeController implements Serializable {

    @Inject
    private ArchetypeDataModel archetypeDataModel;
    @Inject
    private StorageManager storageManager;

    public void onRowEdit(RowEditEvent event){
        Archetype archetype = (Archetype) event.getObject();
        storageManager.update(archetype);
        RecruitmentUtils.logMessage("Archetyp " + archetype.getId() + " zaktualizowany");
    }

    public ArchetypeDataModel getArchetypeDataModel() {
        return archetypeDataModel;
    }

    public void setArchetypeDataModel(ArchetypeDataModel archetypeDataModel) {
        this.archetypeDataModel = archetypeDataModel;
    }
}

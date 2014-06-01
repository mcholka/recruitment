package com.recruitment.admin.archetype;

import com.recruitment.common.RecruitmentUtils;
import com.recruitment.crud.ArchetypeFinder;
import com.recruitment.crud.StorageManager;
import com.recruitment.entity.Archetype;
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
public class ArchetypeController implements Serializable {

    @Inject
    private ArchetypeDataModel archetypeDataModel;
    @Inject
    private StorageManager storageManager;
    @Inject
    private ArchetypeFinder archetypeFinder;

    @PostConstruct
    public void init(){
        archetype = new Archetype();
    }

    private Archetype archetype;

    public void storeNew(){
        if(validate(archetype)){
            archetype.setId(archetype.getId().toUpperCase());
            storageManager.persist(archetype);
            RecruitmentUtils.logMessage("Archetyp został zapisany poprawnie");
        } else {
            RecruitmentUtils.logMessage("Błąd, wypełnij pola poprawnie", FacesMessage.SEVERITY_ERROR);
        }
    }

    private boolean validate(Archetype archetype) {
        return !RecruitmentUtils.emptyString(archetype.getId()) &&
                archetype.getKnowledgeBaseType() != null &&
                archetype.getPoints() != null;
    }

    public void onRowEdit(RowEditEvent event){
        Archetype archetype = (Archetype) event.getObject();
        storageManager.update(archetype);
        RecruitmentUtils.logMessage("Archetyp " + archetype.getId() + " zaktualizowany");
    }

    public List<SelectItem> getArchetypes() {
        List<SelectItem> items = new ArrayList<>();
        List<Archetype> allArchetypes = getAllArchetypes();
        for(Archetype archetype: allArchetypes) {
            items.add(new SelectItem(archetype, archetype.getId())) ;
        }
        return items;
    }

    private List<Archetype> getAllArchetypes() {
        return archetypeFinder.findAllArchetypes();
    }

    public ArchetypeDataModel getArchetypeDataModel() {
        return archetypeDataModel;
    }

    public void setArchetypeDataModel(ArchetypeDataModel archetypeDataModel) {
        this.archetypeDataModel = archetypeDataModel;
    }

    public Archetype getArchetype() {
        return archetype;
    }

    public void setArchetype(Archetype archetype) {
        this.archetype = archetype;
    }
}

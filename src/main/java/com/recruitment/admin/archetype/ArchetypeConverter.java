package com.recruitment.admin.archetype;

import com.recruitment.crud.StorageManager;
import com.recruitment.entity.Archetype;

import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

/**
 * Created by mcholka on 2014-06-01. Enjoy!
 */
@RequestScoped
@FacesConverter("ArchetypeConverter")
public class ArchetypeConverter implements Converter {
    @Inject
    private StorageManager storageManager;

    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
        try {
            return storageManager.findByID(value, Archetype.class);
        } catch (Exception e){
            return null;
        }
    }

    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {
        try {
            return ((Archetype) value).getId();
        } catch (Exception e) {
            return null;
        }
    }
}

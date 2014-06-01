package com.recruitment.admin.knowledge;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

/**
 * Created by mcholka on 2014-06-01. Enjoy!
 */
@Named
@ViewScoped
public class KnowledgeController implements Serializable {
    @Inject
    private KnowledgeDataModel knowledgeDataModel;

    public KnowledgeDataModel getKnowledgeDataModel() {
        return knowledgeDataModel;
    }

    public void setKnowledgeDataModel(KnowledgeDataModel knowledgeDataModel) {
        this.knowledgeDataModel = knowledgeDataModel;
    }
}

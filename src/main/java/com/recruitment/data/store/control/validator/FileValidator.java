package com.recruitment.data.store.control.validator;

import com.recruitment.common.RecruitmentUtils;
import org.primefaces.model.UploadedFile;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * Created by mcholka on 2014-03-25. Enjoy!
 */
@FacesValidator("FileValidator")
public class FileValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        checkFile(value);
        UploadedFile file = (UploadedFile) value;
        checkFileEmpty(file);
        checkFileType(file);
    }

    private void checkFile(Object value) {
        if(!(value instanceof UploadedFile)){
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Błąd wewnętrzny, spróbuj niebawem ponownie", null));
        }
    }

    private void checkFileEmpty(UploadedFile file) {
        if(file == null || RecruitmentUtils.emptyString(file.getFileName())){
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Plik nie może być pusty", null));
        }
    }

    private void checkFileType(UploadedFile file) {
        if(!RecruitmentUtils.REQUIRED_FILE_FORMAT.equals(file.getContentType())){
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Błędny format pliku, prześlij proszę plik w formacie PDF", null));
        }
    }
}

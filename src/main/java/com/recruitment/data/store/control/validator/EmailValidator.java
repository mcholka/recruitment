package com.recruitment.data.store.control.validator;

import com.recruitment.common.RecruitmentUtils;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

/**
 * Created by mcholka on 2014-03-27. Enjoy!
 */

@FacesValidator("EmailValidator")
public class EmailValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        checkEmpty(value);
        checkType(value);
        checkCorrectEmail((String)value);
    }

    private void checkEmpty(Object value) {
        if(value == null){
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Adres email nie może być pusty", null));
        }
    }

    private void checkType(Object value) {
        if(!(value instanceof String)){
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Błąd wewnętrzny, spróbuj niebawem ponownie", null));
        }
    }

    private void checkCorrectEmail(String value) {
        try {
            InternetAddress email = new InternetAddress(value);
            email.validate();
        } catch (AddressException e) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Błędny adres email", null));
        }
    }
}

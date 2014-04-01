package com.recruitment.common;

import org.apache.log4j.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.io.IOException;

/**
 * Created by mcholka on 2014-03-23. Enjoy!
 */
public class RecruitmentUtils {
    private static final Logger logger = Logger.getLogger(RecruitmentUtils.class);

    public static final String FILE_STORE_PATH = "C:\\workspace\\OutputFiles\\";
    public static final String REQUIRED_FILE_FORMAT = "application/pdf";
    public static final String DEFAULT_FILE_SUFFIX = ".pdf";

    public static void logMessage(String message){
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, message, message));
    }

    public static void logMessage(String message, FacesMessage.Severity severity){
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, message, message));
    }

    public static boolean emptyString(String message) {
        return message == null || ("").equals(message.trim());
    }

    public static void redirect(String url){
        try {
            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
            ec.redirect(ec.getRequestContextPath() + url);
            FacesContext.getCurrentInstance().responseComplete();
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }
}

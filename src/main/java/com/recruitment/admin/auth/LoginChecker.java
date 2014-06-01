package com.recruitment.admin.auth;

import com.recruitment.common.RecruitmentUtils;
import com.recruitment.crud.StorageManager;
import com.recruitment.entity.Admin;
import org.apache.log4j.Logger;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by mcholka on 2014-05-22. Enjoy!
 */
@Named
@SessionScoped
public class LoginChecker implements Serializable {
    private static final Logger logger = Logger.getLogger(LoginChecker.class);

    @Inject
    private StorageManager storageManager;

    private Admin admin;

    private static final Integer SESSION_SCOPE = 5;

    public void loginUser(Admin admin){
        logger.info("Mark user as logged " + admin.getLogin());
        this.admin = admin;
        Date limitTime = getLimitTime();
        admin.setLoginTime(new Date());
        admin.setLimitTime(limitTime);
        admin.setLoggedIn(true);
        storageManager.update(admin);
    }

    public boolean isLoggedIn(){
        if(admin == null){
            logger.info("Null admin!");
            RecruitmentUtils.redirect("/admin/login.recruitment");
            return false;
        }

        admin = storageManager.findByID(admin.getLogin(), Admin.class);

        if(!admin.isLoggedIn()){
            logger.info("Admin logged out!");
            RecruitmentUtils.redirect("/admin/login.recruitment");
            return false;
        }
        Date now = new Date();
        if(now.after(admin.getLimitTime())){
            logger.info("Limit time exceeded!");
            loggedOut();
            return false;
        }
        return true;
    }

    public void loggedOut(){
        logger.info("Logged out!");

        admin.setLoggedIn(false);
        admin.setLimitTime(null);
        storageManager.update(admin);
        admin = null;
        RecruitmentUtils.redirect("/admin/login.recruitment");
    }

    private Date getLimitTime() {
        Calendar limit = Calendar.getInstance();
        limit.add(Calendar.MINUTE, SESSION_SCOPE);
        return limit.getTime();
    }
}

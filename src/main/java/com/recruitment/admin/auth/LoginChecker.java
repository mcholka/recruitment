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
    private Date limitTime;

    public void loginUser(Admin admin){
        logger.info("Mark user as logged " + admin.getLogin());
        this.admin = admin;
        this.limitTime = getLimitTime();
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
        if(!admin.isLoggedIn()){
            RecruitmentUtils.redirect("/admin/login.recruitment");
            logger.info("Admin logged out!");
        }
        Date now = new Date();
        if(now.after(limitTime)){
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
        limitTime = null;
        RecruitmentUtils.redirect("/admin/login.recruitment");
    }

    private Date getLimitTime() {
        Calendar limit = Calendar.getInstance();
        limit.add(Calendar.MINUTE, 5);
        return limit.getTime();
    }
}

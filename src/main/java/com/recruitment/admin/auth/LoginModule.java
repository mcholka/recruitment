package com.recruitment.admin.auth;

import com.recruitment.common.LoginException;
import com.recruitment.common.MD5;
import com.recruitment.common.RecruitmentUtils;
import com.recruitment.crud.AdminFinder;
import com.recruitment.entity.Admin;
import org.apache.log4j.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

/**
 * Created by mcholka on 2014-05-22. Enjoy!
 */
@Named
@ViewScoped
public class LoginModule implements Serializable {
    private static final Logger logger = Logger.getLogger(LoginModule.class);

    @Inject
    private AdminFinder adminFinder;
    @Inject
    private MD5 md5;
    @Inject
    private LoginChecker loginChecker;

    private String login;
    private String password;

    public void loginAdmin(){
        try {
            logger.info("Try to login user: " + login + " pass " + password);
            Admin admin = matchAdmin();
            loginAdmin(admin);
            RecruitmentUtils.redirect("/admin/customer/customer-list.recruitment");
        } catch (LoginException e) {
            RecruitmentUtils.logMessage("Wrong login or password!", FacesMessage.SEVERITY_ERROR);
        }
    }

    private void loginAdmin(Admin admin) {
        loginChecker.loginUser(admin);
    }

    private Admin matchAdmin() throws LoginException {
        return adminFinder.findUserByLoginAndPassword(login, password);
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = md5.getMd5(password);
    }
}

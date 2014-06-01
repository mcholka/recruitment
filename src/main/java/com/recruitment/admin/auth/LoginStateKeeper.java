package com.recruitment.admin.auth;

import com.recruitment.crud.AdminFinder;
import com.recruitment.crud.StorageManager;
import com.recruitment.entity.Admin;
import org.apache.log4j.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.*;
import javax.inject.Inject;
import java.util.List;

/**
 * Created by mcholka on 2014-06-01. Enjoy!
 */
@Singleton
@Startup
public class LoginStateKeeper {
    private static final Logger logger = Logger.getLogger(LoginStateKeeper.class);

    private static final Integer INTERVAL = 10000;

    @PostConstruct
    public void init() {
        logger.info("Init LoginStateKeeper");
        timerService.createIntervalTimer(INTERVAL, INTERVAL, new TimerConfig(null, false));
    }

    @Resource
    private TimerService timerService;

    @Inject
    private AdminFinder adminFinder;
    @Inject
    private StorageManager storageManager;

    @Timeout
    public synchronized void execute() {
        List<Admin> admins = findAdminsToLoggedOut();
        for(Admin admin : admins){
            loggedOut(admin);
        }
    }

    private void loggedOut(Admin admin) {
        admin.setLoggedIn(false);
        admin.setLimitTime(null);
        storageManager.update(admin);
    }

    private List<Admin> findAdminsToLoggedOut() {
        return adminFinder.findAdminListToLoggedOut();
    }
}

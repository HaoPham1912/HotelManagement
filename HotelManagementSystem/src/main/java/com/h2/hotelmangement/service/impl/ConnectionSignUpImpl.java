package com.h2.hotelmangement.service.impl;

import com.h2.hotelmangement.Request.AppUserDAO;
import com.h2.hotelmangement.entity.AppUser;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;

public class ConnectionSignUpImpl implements ConnectionSignUp {

    private AppUserDAO appUserDAO;

    public ConnectionSignUpImpl(AppUserDAO appUserDAO) {
        this.appUserDAO = appUserDAO;
    }

    // After logging in social networking.
    // This method will be called to create a corresponding App_User record
    // if it does not already exist.
    @Override
    public String execute(Connection<?> connection) {

        AppUser account = appUserDAO.createAppUser(connection);
        return account.getUserName();
    }

}

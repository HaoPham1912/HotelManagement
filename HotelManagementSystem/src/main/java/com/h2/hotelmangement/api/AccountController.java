package com.h2.hotelmangement.api;

import com.h2.hotelmangement.entity.AccountEntity;
import com.h2.hotelmangement.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AccountController {

    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public void save(AccountEntity accountEntity){
        accountService.saveAccount(accountEntity);
    }
}

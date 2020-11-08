package com.h2.hotelmangement.api;

import com.h2.hotelmangement.entity.Account;
import com.h2.hotelmangement.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("register")
    public String save(@RequestBody Account account) {

        return "index";
    }
}

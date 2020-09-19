package com.h2.hotelmangement.service.impl;

import com.h2.hotelmangement.entity.AccountEntity;
import com.h2.hotelmangement.repository.AccountRepository;
import com.h2.hotelmangement.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;


    @Override
    public void save(AccountEntity accountEntity) {
         accountRepository.save(accountEntity);
    }
}

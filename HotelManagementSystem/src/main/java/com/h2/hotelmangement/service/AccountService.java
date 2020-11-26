package com.h2.hotelmangement.service;

import com.h2.hotelmangement.entity.Account;

import java.util.List;


public interface AccountService {
    Account getAccountByUsername(String username);

    void save(Account account);

    List<Account> findAll();

    void delete(Long accountId);
}

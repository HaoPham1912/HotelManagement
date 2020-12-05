package com.h2.hotelmangement.service;

import com.h2.hotelmangement.entity.Account;

import java.util.List;
import java.util.Optional;


public interface AccountService {
    Account getAccountByUsername(String username);

    Optional<Account> getAccountByAccountId(Long accountId);

    void save(Account account);

    List<Account> findAll();

    void delete(Long accountId);
}

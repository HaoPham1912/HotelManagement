package com.h2.hotelmangement.service.impl;

import com.h2.hotelmangement.entity.Account;
import com.h2.hotelmangement.repository.AccountRepository;
import com.h2.hotelmangement.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Account getAccountByUsername(String username) {
        return accountRepository.findAccountByUsername(username);
    }

    @Override
    public Optional<Account> getAccountByAccountId(Long accountId) {
        return accountRepository.findById(accountId);
    }

    @Override
    public void save(Account account) {
        accountRepository.save(account);
    }

    @Override
    public List<Account> findAll() {
        return accountRepository.findAll();
    }


    @Override
    public void delete(Long accountId) {
        accountRepository.deleteById(accountId);
    }

}

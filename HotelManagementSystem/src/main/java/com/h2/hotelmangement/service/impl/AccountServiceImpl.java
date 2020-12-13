package com.h2.hotelmangement.service.impl;

import com.h2.hotelmangement.common.util.ModelMapperUtil;
import com.h2.hotelmangement.entity.Account;
import com.h2.hotelmangement.model.dto.AccountDTO;
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
    public Optional<AccountDTO> getAccountByUsername(String username) {
        Optional<Account> account = accountRepository.findAccountByUsername(username);
        if (account.isPresent()) {
            return Optional.ofNullable(ModelMapperUtil.map(account, new AccountDTO()));
        }
        return Optional.empty();
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

package com.h2.hotelmangement.service.impl;

import com.h2.hotelmangement.common.util.ModelMapperUtil;
import com.h2.hotelmangement.entity.Account;
import com.h2.hotelmangement.model.dto.AccountDTO;
import com.h2.hotelmangement.repository.AccountRepository;
import com.h2.hotelmangement.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public Page<Account> findAll(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Account> accountPage = accountRepository.findAll(pageable);
        return accountPage;
    }


    @Override
    public void delete(Long accountId) throws Exception {
        Account account = accountRepository.getOne(accountId);
        if(account != null){
            Boolean accountStatus = account.getStatus();
            account.setStatus(!accountStatus);
            accountRepository.save(account);
        }else{
            throw new Exception("Can noi find account with id "+accountId);
        }
    }

    @Override
    public Page<Account> getAllAccountPagination(String username, int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Account> accountPage = accountRepository.findAllByUsernameContains(username, pageable);
        return accountPage;
    }

}

package com.h2.hotelmangement.service;

import com.h2.hotelmangement.entity.Account;
import com.h2.hotelmangement.model.dto.AccountDTO;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;


public interface AccountService {
    Optional<AccountDTO> getAccountByUsername(String username);

    Optional<Account> getAccountByAccountId(Long accountId);

    void save(Account account);

    Page<Account> findAll(int pageNo, int pageSize);

    void delete(Long accountId) throws Exception;

    Page<Account> getAllAccountPagination(String username, int pageNo, int pageSize);

    Account findAccountByUsername(String username);

}

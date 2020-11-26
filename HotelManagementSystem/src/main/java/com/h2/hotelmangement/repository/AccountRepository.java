package com.h2.hotelmangement.repository;

import com.h2.hotelmangement.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findAccountByUsername(String username);
}

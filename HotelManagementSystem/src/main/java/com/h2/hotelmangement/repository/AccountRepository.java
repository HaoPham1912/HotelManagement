package com.h2.hotelmangement.repository;

import com.h2.hotelmangement.entity.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;


@Repository
@Transactional
public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findAccountByUsername(String username);

    Page<Account> findAllByUsernameContains(String username, Pageable pageable);

    Account findByUsername(String username);
}

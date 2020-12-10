package com.h2.hotelmangement.service.impl;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import com.h2.hotelmangement.entity.Account;
import com.h2.hotelmangement.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Transactional
    public UserDetails loadUserDetails(String username, String password) throws UsernameNotFoundException {

        Optional<Account> accountEntity = accountRepository.findAccountByUsername(username);
        if (accountEntity.isPresent() && passwordEncoder.matches(password, accountEntity.get().getPassword())) {
            Set<String> roles = accountEntity.get().getRoles().stream().map(role -> role.getRoleName()).collect(Collectors.toSet());
            Set<SimpleGrantedAuthority> authorities = roles.stream().map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toSet());

            User userDetails = new User(accountEntity.get().getUsername(), "", authorities);
            userDetails.eraseCredentials();
            return userDetails;

        } else {
            throw new UsernameNotFoundException("User name is incorrect" + username);
        }
    }

}

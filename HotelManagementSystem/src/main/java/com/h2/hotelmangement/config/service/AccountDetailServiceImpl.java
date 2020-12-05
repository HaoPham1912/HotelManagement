package com.h2.hotelmangement.config.service;

import com.h2.hotelmangement.entity.Account;
import com.h2.hotelmangement.entity.Role;
import com.h2.hotelmangement.jwt.JwtAuthenticationFilter;
import com.h2.hotelmangement.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class AccountDetailServiceImpl implements UserDetailsService {

    @Autowired
    AccountService accountService;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<Account> account = Optional.ofNullable(accountService.getAccountByUsername(userName));
        if (account.isPresent()) {
            ArrayList<GrantedAuthority> grantedAuthorities = (ArrayList<GrantedAuthority>) account.get().getRoles().stream()
                    .map(AccountDetailServiceImpl::getRole)
                    .collect(Collectors.toList());

            return new User(account.get().getUsername(), account.get().getPassword(), grantedAuthorities);
        }
        throw new UsernameNotFoundException(userName);
    }

    private static GrantedAuthority getRole(Role role) {
        return new SimpleGrantedAuthority(role.getRoleName());
    }
}

package com.h2.hotelmangement.service.impl;

import java.util.*;
import java.util.stream.Collectors;

import com.h2.hotelmangement.Request.AppUserDAO;
import com.h2.hotelmangement.entity.Account;
import com.h2.hotelmangement.entity.AppUser;
import com.h2.hotelmangement.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
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
    private AppUserDAO appUserDAO;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Transactional
    public UserDetails loadUserDetails(String username, String password) throws UsernameNotFoundException {

        Optional<Account> accountEntity = accountRepository.findAccountByUsername(username);
//        if (accountEntity.isPresent() && passwordEncoder.matches(password, accountEntity.get().getPassword())) {
        if (accountEntity.isPresent() && password.equals(accountEntity.get().getPassword())) {
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

    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        System.out.println("UserDetailsServiceImpl.loadUserByUsername=" + userName);

        AppUser appUser = this.appUserDAO.findAppUserByUserName(userName);

        if (appUser == null) {
            System.out.println("User not found! " + userName);
            throw new UsernameNotFoundException("User " + userName + " was not found in the database");
        }

        System.out.println("Found User: " + appUser);

        //Set Role
        List<String> roleNames = null;

//        // [ROLE_USER, ROLE_ADMIN,..]
//        List<String> roleNames = this.appRoleDAO.getRoleNames(appUser.getUserId());

        List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
        if (roleNames != null) {
            for (String role : roleNames) {
                // ROLE_USER, ROLE_ADMIN,..
                GrantedAuthority authority = new SimpleGrantedAuthority(role);
                grantList.add(authority);
            }
        }

        SocialUserDetailsImpl userDetails = new SocialUserDetailsImpl(appUser, roleNames);

        return userDetails;
    }

}

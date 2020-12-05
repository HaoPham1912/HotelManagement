package com.h2.hotelmangement.api;

import com.h2.hotelmangement.entity.Account;
import com.h2.hotelmangement.jwt.JwtTokenProvider;
import com.h2.hotelmangement.payload.LoginResponse;
import com.h2.hotelmangement.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/login")
public class AuthorizationAPI {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    AccountService accountService;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @PostMapping("")
    public LoginResponse login(@RequestParam(required = true) String userName,
                               @RequestParam(required = true) String password) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userName,
                        password
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return handleLoginResponse(authentication);
    }

    private LoginResponse handleLoginResponse(Authentication authentication) {
        Account account = accountService.getAccountByUsername(authentication.getName());
        String jwt = jwtTokenProvider.generateToken(account.getAccountId());
        String refreshToken = jwtTokenProvider.generateRefreshToken(account.getAccountId());
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setAuthorities((Collection<GrantedAuthority>) authentication.getAuthorities());
        loginResponse.setUserName(authentication.getName());
        loginResponse.setAccessToken(jwt);
        loginResponse.setRefreshToken(refreshToken);
        loginResponse.setId(account.getAccountId());
        loginResponse.setSocialAccount(false);

        return loginResponse;
    }
}

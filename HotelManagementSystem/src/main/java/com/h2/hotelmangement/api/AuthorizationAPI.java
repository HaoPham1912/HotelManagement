package com.h2.hotelmangement.api;

import com.h2.hotelmangement.entity.Account;
import com.h2.hotelmangement.jwt.JwtTokenProvider;
import com.h2.hotelmangement.model.dto.AccountDTO;
import com.h2.hotelmangement.payload.LoginResponse;
import com.h2.hotelmangement.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
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

    @RequestMapping(value = "/sid", method = RequestMethod.GET,
            produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<InputStreamResource> getImage(@RequestParam(required = true) String imgId, HttpSession session) throws IOException {

        ServletContext context = session.getServletContext();
        String path = context.getRealPath("images");

        InputStream imgFile = new FileInputStream(path + "/" + imgId + ".jpg");

        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(new InputStreamResource(imgFile));
    }

    @PostMapping("")
    public LoginResponse login(@RequestBody AccountDTO accountDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        accountDTO.getUsername(),
                        accountDTO.getPassword()
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

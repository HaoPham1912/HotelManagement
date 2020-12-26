package com.h2.hotelmangement.api;

import com.h2.hotelmangement.Request.LoginRequest;
import com.h2.hotelmangement.common.util.CommonConstants;
import com.h2.hotelmangement.common.util.ResponseTemplate;
import com.h2.hotelmangement.entity.Account;
import com.h2.hotelmangement.entity.Role;
import com.h2.hotelmangement.model.dto.AccountDTO;
import com.h2.hotelmangement.payload.LoginResponse;
import com.h2.hotelmangement.service.AccountService;
import com.h2.hotelmangement.service.RoleService;
import com.h2.hotelmangement.service.impl.UserDetailsServiceImpl;
import com.h2.hotelmangement.common.util.TokenParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@RestController
@RequestMapping("/")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AuthorizationAPI {

//    @Autowired
//    AuthenticationManager authenticationManager;

    @Autowired
    AccountService accountService;

    @Autowired
    RoleService roleService;

//    @Autowired
//    JwtTokenProvider jwtTokenProvider;

    @Autowired
    UserDetailsServiceImpl userDetailsServiceImpl;

    @Autowired
    TokenParser tokenParser;

    @PostMapping("/login")
    public ResponseTemplate authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        System.out.println(loginRequest.getUserName() + " " +loginRequest.getPass());
        UserDetails userDetails = userDetailsServiceImpl.loadUserDetails(loginRequest.getUserName(),
                loginRequest.getPass());
        System.out.println("User detail "+ userDetails.getUsername());
        ResponseTemplate responseTemplate = new ResponseTemplate();
        if (null != userDetails) {
            String token = tokenParser.generateToken(userDetails);
            Map<String, Object> items = new HashMap<>();
            items.put("accessToken", token);
            items.put("user", userDetails.getUsername());
            responseTemplate.setData(items);
            responseTemplate.setStatus(HttpStatus.OK.value());
            return responseTemplate;
        }
        responseTemplate.setStatus(HttpStatus.BAD_REQUEST.value());
        responseTemplate.setData("message","Email or passsword is correct");
        return responseTemplate;
    }

    private LoginResponse handleLoginResponse(Authentication authentication) {
        LoginResponse loginResponse = new LoginResponse();
        Optional<AccountDTO> account = accountService.getAccountByUsername(authentication.getName());
        if(account.isPresent()){
//            String jwt = tokenParser.generateToken(account.get().getAccountId());
//            String refreshToken = tokenParser.generateRefreshToken(account.get().getAccountId());
            loginResponse.setAuthorities((Collection<GrantedAuthority>) authentication.getAuthorities());
            loginResponse.setUserName(authentication.getName());
//            loginResponse.setAccessToken(jwt);
//            loginResponse.setRefreshToken(refreshToken);
            loginResponse.setId(account.get().getAccountId());
            loginResponse.setSocialAccount(false);
        }

        return loginResponse;
    }

    @PostMapping("/register")
    public ResponseEntity<HttpStatus> register(@RequestBody LoginRequest loginRequest){
        Account account = new Account();
        account.setUsername(loginRequest.getUserName());
        account.setPassword(loginRequest.getPass());
        Role role = roleService.findByRoleName("ADMIN");
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(role);
        account.setRoles(roleSet);
        accountService.save(account);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}

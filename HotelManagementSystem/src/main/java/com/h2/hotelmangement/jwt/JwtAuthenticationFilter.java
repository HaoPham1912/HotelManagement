package com.h2.hotelmangement.jwt;

import com.h2.hotelmangement.entity.Account;
import com.h2.hotelmangement.entity.Role;
import com.h2.hotelmangement.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final String AUTHORIZATION = "Authorization";

    private static final String BEARER = "Bearer";

    private static final String INVALID_JWT = "INVALID_JWT";


    private static final List<String> unauthorizedURL = new ArrayList<>(Arrays.asList("/login"));


    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    AccountService accountService;



    private String getJWTViaRequest(HttpServletRequest requets) {
        String bearerToken = requets.getHeader(AUTHORIZATION);
        if (StringUtils.isEmpty(bearerToken) || !bearerToken.startsWith(BEARER)) {
            return INVALID_JWT;
        }
        return bearerToken.substring(7);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String currentUrl = request.getRequestURI();
       // filterChain.doFilter(request,response);
        if (checkAuthorizedURL(currentUrl)) {
            String jwt = getJWTViaRequest(request);
            if (jwtTokenProvider.validateToken(jwt)) {
                Long accountId = jwtTokenProvider.getUserIdFromJWT(jwt);
                Optional<Account> account = accountService.getAccountByAccountId(accountId);
                if (account.isPresent()) {
                    ArrayList<GrantedAuthority> grantedAuthorities = (ArrayList<GrantedAuthority>) account.get().getRoles().stream()
                            .map(JwtAuthenticationFilter::getRole)
                            .collect(Collectors.toList());
                    UserDetails userDetails = new User(account.get().getUsername(),
                            account.get().getPassword(),
                            grantedAuthorities);
                    UsernamePasswordAuthenticationToken authenticationToken =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    filterChain.doFilter(request,response);
                } else {
                    response.sendError(403);
                }
            }
        } else {
            filterChain.doFilter(request,response);
        }
    }

    private static GrantedAuthority getRole(Role role) {
        return new SimpleGrantedAuthority(role.getRoleName());
    }

    private boolean checkAuthorizedURL(String url) {
        return Objects.isNull(unauthorizedURL.stream().filter(url::startsWith).findFirst().orElse(null));
    }
}

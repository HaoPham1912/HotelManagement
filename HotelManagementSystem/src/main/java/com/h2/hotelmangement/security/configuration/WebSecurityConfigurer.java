package com.h2.hotelmangement.security.configuration;

import com.h2.hotelmangement.security.error.UnauthorizedEntryPoint;
import com.h2.hotelmangement.security.filter.ValidateTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter  {

    @Autowired
    private UnauthorizedEntryPoint unauthorizedEntryPoint;

    @Autowired
    private ValidateTokenFilter validateTokenFilter;

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        // don't need CSRF for api
        httpSecurity.cors().and().csrf().disable()
                // handle unauthorized
                .exceptionHandling().authenticationEntryPoint(unauthorizedEntryPoint).and()
                // make sure we use stateless session, session won't be used to  store user's state.
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                // all other requests need to be authenticated
                .authorizeRequests().antMatchers("/login","/login/register","/customerUnauthenticated/**","/api/branch/location").permitAll()
                                    .anyRequest().authenticated();


        // Add a filter to validate the tokens with every request
        httpSecurity.addFilterBefore(validateTokenFilter, UsernamePasswordAuthenticationFilter.class);
    }

}

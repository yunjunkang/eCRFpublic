package com.evertri.ecrf.config;

import com.evertri.ecrf.authentication.JwtAuthenticationFilter;
import com.evertri.ecrf.authentication.TokenUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
@Configuration
//@EnableWebSecurity
public class SecurityConfig {

    private final TokenUtil tokenUtil;

    public SecurityConfig(TokenUtil tokenUtil) {
        this.tokenUtil = tokenUtil;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests().anyRequest().permitAll();
        return http.build();

//        http.csrf().disable()
//                .authorizeRequests()
//                .anyRequest().authenticated()
//                .and()
//                .addFilterBefore(new JwtAuthenticationFilter(tokenUtil), UsernamePasswordAuthenticationFilter.class)
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}

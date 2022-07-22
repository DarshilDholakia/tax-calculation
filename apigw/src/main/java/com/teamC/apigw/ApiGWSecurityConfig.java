package com.teamC.apigw;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class ApiGWSecurityConfig {

    @Bean
    public MapReactiveUserDetailsService userDetailsService() {
        UserDetails user = User
                .withDefaultPasswordEncoder()
                .username("User")
                .password("Password")
                .roles("USER")
                .build();
        return new MapReactiveUserDetailsService(user);
    }

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {

        http
                .csrf().disable()
                .authorizeExchange()
                .pathMatchers("/person/**").permitAll()
                .pathMatchers("/incomes/**").permitAll()
                .pathMatchers("/tax-calculations/**").permitAll()
                .anyExchange().authenticated()
                .and()
                .httpBasic();
        return http.build();
    }
}

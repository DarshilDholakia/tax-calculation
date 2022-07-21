package com.teamC.apigw;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class ApiGWSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("User").password("{noop}Password").roles("USER");
    }

    @Override protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests().antMatchers("/person/**", "incomes/**", "/tax-calculations/**").fullyAuthenticated().and
                        ().httpBasic().and().csrf().disable(); }
}

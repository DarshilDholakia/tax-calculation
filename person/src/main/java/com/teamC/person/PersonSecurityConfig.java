package com.teamC.person;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.NoOpPasswordEncoder;

//@Configuration
//@EnableWebSecurity
//public class PersonSecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication().withUser("User").password("{noop}Password").roles("USER");
////        auth.inMemoryAuthentication().withUser("Basant").password("Password2").roles("USER");
//    }
//
//    @Override protected void configure(HttpSecurity http) throws Exception {
//    http.csrf().disable()
//            .authorizeRequests().antMatchers("/person/**").fullyAuthenticated().and
//    ().httpBasic().and().csrf().disable(); }
//}

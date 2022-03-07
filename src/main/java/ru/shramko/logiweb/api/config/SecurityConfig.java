package ru.shramko.logiweb.api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.shramko.logiweb.service.PersonDetailService;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final PersonDetailService personDetailService;
    private final LogiwebAuthenticationSuccessHandler logiwebAuthenticationSuccessHandler;

    @Autowired
    public SecurityConfig(PersonDetailService personDetailService,
                          LogiwebAuthenticationSuccessHandler logiwebAuthenticationSuccessHandler) {
        this.personDetailService = personDetailService;
        this.logiwebAuthenticationSuccessHandler = logiwebAuthenticationSuccessHandler;
    }

    public SecurityConfig(boolean disableDefaults, PersonDetailService personDetailService, LogiwebAuthenticationSuccessHandler logiwebAuthenticationSuccessHandler) {
        super(disableDefaults);
        this.personDetailService = personDetailService;
        this.logiwebAuthenticationSuccessHandler = logiwebAuthenticationSuccessHandler;
    }

    @Autowired
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(personDetailService)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                    .antMatchers("/crm/**").hasRole("MANAGER")
                    .antMatchers("/profile/**").hasRole("DRIVER")
                    .antMatchers("/h2-console/*").permitAll()
                    .and()
                  .formLogin()
                    .successHandler(logiwebAuthenticationSuccessHandler);
    }

}

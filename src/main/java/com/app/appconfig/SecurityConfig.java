/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.appconfig;

import com.app.sec.auth.CustomAbstractAuthenticationProcessingFilter;
import com.app.sec.auth.CustomLoginUrlAuthenticationEntryPoint;
import com.app.sec.auth.CustomSimpleUrlAuthenticationFailureHandler;
import com.app.sec.auth.CustomSimpleUrlAuthenticationSuccessHandler;
import com.app.sec.auth.CustomSimpleUrlLogoutSuccessHandler;
import com.app.sec.auth.CustomUsernamePasswordAuthenticationFilter;
import com.app.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 *
 * @author sandeep.kumar
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter  {

    @Bean
    public UserDetailsService dbUserAuthService() {
        UserDetailsService s = new UserDetailsServiceImpl();
        return s;
    }
    
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider dap = new DaoAuthenticationProvider();
        dap.setUserDetailsService(dbUserAuthService());
        return dap;
    }
    
    @Bean(name = "authenticationManager")
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {    
        auth.userDetailsService(dbUserAuthService());
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //.httpBasic()
                //.authenticationEntryPoint(loginUrlAuthEntryPoint())
                //.and()
                .exceptionHandling()
                .authenticationEntryPoint(loginUrlAuthEntryPoint())
                .accessDeniedPage("/accessdenied")
                .and()
                .addFilterBefore(customUsernamePasswordAuthFilter(), UsernamePasswordAuthenticationFilter.class)
                .logout()
                .invalidateHttpSession(true)
                .logoutUrl("/do_logout")
                .deleteCookies("JSESSIONID")
                .logoutSuccessHandler(logoutHandler())
                .and()
                .authorizeRequests()
                .antMatchers("/login").access("permitAll")
                .antMatchers("/logout").access("permitAll")
                .antMatchers("/accessdenied").access("permitAll")
                .antMatchers("/h2-console").access("permitAll")
                .antMatchers("/**").access("isAuthenticated()")
                .and()
                .csrf()
                .and()
                .sessionManagement()
                .invalidSessionUrl("/login")
                .maximumSessions(1)
                .expiredUrl("/login")
                .and()
                ;
    }
    
    @Bean
    public DefaultRedirectStrategy redirectStrategy() {
        DefaultRedirectStrategy drs = new DefaultRedirectStrategy();
        return drs;
    }
    
    @Bean
    public CustomLoginUrlAuthenticationEntryPoint loginUrlAuthEntryPoint() {
        CustomLoginUrlAuthenticationEntryPoint p = new CustomLoginUrlAuthenticationEntryPoint("/login");
        return p;
    }
    
    @Bean
    public CustomSimpleUrlAuthenticationFailureHandler authFailureHandler() {
        CustomSimpleUrlAuthenticationFailureHandler h = new CustomSimpleUrlAuthenticationFailureHandler();
        h.setRedirectStrategy(redirectStrategy());
        h.setDefaultFailureUrl("/accessdenied");
        h.setUseForward(true);
        return h;
    }
    
    @Bean
    public CustomSimpleUrlAuthenticationSuccessHandler authSuccessHandler() {
        CustomSimpleUrlAuthenticationSuccessHandler h = new CustomSimpleUrlAuthenticationSuccessHandler();
        h.setDefaultTargetUrl("/home");
        h.setRedirectStrategy(redirectStrategy());
        return h;
    }
    
    @Bean
    public CustomSimpleUrlLogoutSuccessHandler logoutHandler() {
        CustomSimpleUrlLogoutSuccessHandler h = new CustomSimpleUrlLogoutSuccessHandler();
        h.setDefaultTargetUrl("/logout");
        return h;
    }
    
    @Bean
    public CustomUsernamePasswordAuthenticationFilter customUsernamePasswordAuthFilter() throws Exception {
        CustomUsernamePasswordAuthenticationFilter f = new CustomUsernamePasswordAuthenticationFilter();
        f.setAuthenticationManager(authenticationManagerBean());
        f.setAuthenticationFailureHandler(authFailureHandler());
        f.setAuthenticationSuccessHandler(authSuccessHandler());
        f.setFilterProcessesUrl("/do_login");
        f.setPostOnly(true);
        f.setPasswordParameter("password");
        f.setUsernameParameter("username");
        return f;
    }
    
    @Bean
    public CustomAbstractAuthenticationProcessingFilter customAuthProcessingFilter() throws Exception {
        CustomAbstractAuthenticationProcessingFilter f = new CustomAbstractAuthenticationProcessingFilter("/do_login");
        f.setAuthenticationManager(authenticationManagerBean());
        f.setAuthenticationFailureHandler(authFailureHandler());
        f.setAuthenticationSuccessHandler(authSuccessHandler());
        f.setFilterProcessesUrl("/do_login");
        return f;
    }
    
}

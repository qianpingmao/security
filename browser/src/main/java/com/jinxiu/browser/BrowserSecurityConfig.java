package com.jinxiu.browser;

import com.jinxiu.browser.filter.CheckCodeFilter;
import com.jinxiu.browser.filter.SmsCodeFilter;
import com.jinxiu.properties.SecuritysProperties;
import com.jinxiu.sms.MobileAuthenticationFilter;
import com.jinxiu.sms.MobileAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.security.Provider;

@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SecuritysProperties securityProperties;

    @Autowired
    private AuthenticationSuccessHandler successHandler;

    @Autowired
    private AuthenticationFailureHandler failureHandler;

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        CheckCodeFilter checkCodeFilter = new CheckCodeFilter((AuthenFailureHandler) failureHandler);
        SmsCodeFilter smsCodeFilter = new SmsCodeFilter((AuthenFailureHandler)failureHandler);

        MobileAuthenticationFilter mobileAuthenticationFilter = new MobileAuthenticationFilter();
        mobileAuthenticationFilter.setAuthenticationManager(this.getApplicationContext().getBean(AuthenticationManager.class));
        mobileAuthenticationFilter.setAuthenticationSuccessHandler(successHandler);
        mobileAuthenticationFilter.setAuthenticationFailureHandler(failureHandler);

        MobileAuthenticationProvider mobileAuthenticationProvider = new MobileAuthenticationProvider();
        mobileAuthenticationProvider.setUserDetailsService(userDetailsService);

        http.formLogin()
                .loginPage("/browser/authentication")
                .loginProcessingUrl("/signin")
                .successHandler(successHandler)
                .failureHandler(failureHandler)
                .and()
                .authorizeRequests()
                .mvcMatchers("/browser/authentication","/imageCode","/smsCode","/authentication/mobile",securityProperties.getBrowser().getLoginPage()).permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .csrf()
                .disable()
                .addFilterBefore(checkCodeFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(smsCodeFilter,checkCodeFilter.getClass())
                .addFilterAfter(mobileAuthenticationFilter,UsernamePasswordAuthenticationFilter.class)
                .authenticationProvider(mobileAuthenticationProvider);
    }
}
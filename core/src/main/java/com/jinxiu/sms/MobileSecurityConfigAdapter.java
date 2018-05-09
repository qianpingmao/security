package com.jinxiu.sms;

import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;

public class MobileSecurityConfigAdapter extends SecurityConfigurerAdapter<DefaultSecurityFilterChain,HttpSecurity> {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        MobileAuthenticationProvider provider = new MobileAuthenticationProvider();

        http.authenticationProvider(provider);
    }
}

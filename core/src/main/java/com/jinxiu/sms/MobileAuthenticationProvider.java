package com.jinxiu.sms;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public class MobileAuthenticationProvider implements AuthenticationProvider {

    private UserDetailsService userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        MobileAuthenticationToken token = (MobileAuthenticationToken) authentication;
        UserDetails userDetails = userDetailsService.loadUserByUsername("mobile:" + token.getPrincipal());
        if (userDetails == null) {
            throw new InternalAuthenticationServiceException("该手机号码不存在");
        }
        MobileAuthenticationToken authenticationToken = new MobileAuthenticationToken(userDetails,userDetails.getAuthorities());
        return authenticationToken;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return MobileAuthenticationToken.class.isAssignableFrom(authentication);
    }

    public UserDetailsService getUserDetailsService() {
        return userDetailsService;
    }

    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
}

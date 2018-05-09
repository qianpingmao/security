package com.jinxiu.browser;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jinxiu.properties.BrowserProperties;
import com.jinxiu.properties.SecuritysProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthenSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private SecuritysProperties securitysProperties;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                        Authentication authentication) throws IOException, ServletException {


        if (BrowserProperties.LoginType.JSON.equals(securitysProperties.getBrowser().getLoginType())) {
            httpServletResponse.setContentType("application/json;charset=UTF-8");
            httpServletResponse.getWriter().write(objectMapper.writeValueAsString(authentication.getPrincipal()));
        } else {
            super.onAuthenticationSuccess(httpServletRequest,httpServletResponse,authentication);
        }
    }
}

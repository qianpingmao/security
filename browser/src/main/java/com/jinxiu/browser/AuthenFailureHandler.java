package com.jinxiu.browser;


import com.jinxiu.properties.BrowserProperties;
import com.jinxiu.properties.SecuritysProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthenFailureHandler extends SimpleUrlAuthenticationFailureHandler {


    @Autowired
    private SecuritysProperties securitysProperties;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {
        if (BrowserProperties.LoginType.JSON.equals(securitysProperties.getBrowser().getLoginType())) {
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(exception.getMessage());

        } else {
            super.onAuthenticationFailure(request, response, exception);
        }
    }
}

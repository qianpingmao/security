package com.jinxiu.browser.controller;


import com.jinxiu.properties.BrowserProperties;
import com.jinxiu.properties.SecuritysProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/browser")
public class AuthenticationController {

    private HttpSessionRequestCache cache = new HttpSessionRequestCache();

    private RedirectStrategy strategy = new DefaultRedirectStrategy();

    @Autowired
    private SecuritysProperties securityProperties;

    @RequestMapping("/authentication")
    @ResponseBody
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String authentication(HttpServletRequest request, HttpServletResponse response) throws IOException {
        SavedRequest savedRequest = cache.getRequest(request, response);
        if (savedRequest != null) {
            String redirectUrl = savedRequest.getRedirectUrl();
        }

        if (BrowserProperties.LoginType.HTML.equals(securityProperties.getBrowser().getLoginType())) {
            strategy.sendRedirect(request,response,securityProperties.getBrowser().getLoginPage());
        }
        return "需要引导至登陆页";
    }
}

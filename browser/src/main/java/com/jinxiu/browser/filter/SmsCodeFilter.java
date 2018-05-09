package com.jinxiu.browser.filter;

import com.jinxiu.browser.AuthenFailureHandler;
import com.jinxiu.browser.exception.CheckCodeException;
import com.jinxiu.browser.model.ImageCode;
import com.jinxiu.browser.model.ValidateCode;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.Objects;

public class SmsCodeFilter extends OncePerRequestFilter {

    private AuthenFailureHandler authenFailureHandler;


    public SmsCodeFilter(AuthenFailureHandler authenFailureHandler) {
        this.authenFailureHandler = authenFailureHandler;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        if (Objects.equals(httpServletRequest.getRequestURI(),"/authentication/mobile") &&
                "POST".equalsIgnoreCase(httpServletRequest.getMethod())) {
            try {
                checkCode(httpServletRequest);
            } catch (CheckCodeException e) {
                authenFailureHandler.onAuthenticationFailure(httpServletRequest,httpServletResponse,e);
                return;
            }
        }

        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }

    private void checkCode(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session == null) {
            throw new CheckCodeException("session失效");
        }
        ValidateCode validateCode = (ValidateCode) session.getAttribute("SMS_CODE");
        if (validateCode == null) {
            throw new CheckCodeException("验证码不存在");
        }
        if (StringUtils.isEmpty(request.getParameter("smsCode"))) {
            throw new CheckCodeException("验证码为空");
        }
        if (new Date().after(validateCode.getExpireTime())) {
            throw new CheckCodeException("验证码已过期");
        }
        if (!Objects.equals(validateCode.getCode(),request.getParameter("smsCode"))) {
            throw new CheckCodeException("验证码不正确");
        }
        session.removeAttribute("SMS_CODE");
    }

}

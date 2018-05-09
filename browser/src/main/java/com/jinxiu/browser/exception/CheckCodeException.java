package com.jinxiu.browser.exception;

import org.springframework.security.core.AuthenticationException;

public class CheckCodeException extends AuthenticationException {

    public CheckCodeException(String msg) {
        super(msg);
    }
}

package com.jinxiu.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Component
@ConfigurationProperties(prefix = "jinxiu.security")
public class SecuritysProperties {

    private BrowserProperties browser = new BrowserProperties();

    private ValidCodeProperties code = new ValidCodeProperties();

    public BrowserProperties getBrowser() {
        return browser;
    }

    public void setBrowser(BrowserProperties browser) {
        this.browser = browser;
    }

    public ValidCodeProperties getCode() {
        return code;
    }

    public void setCode(ValidCodeProperties code) {
        this.code = code;
    }
}

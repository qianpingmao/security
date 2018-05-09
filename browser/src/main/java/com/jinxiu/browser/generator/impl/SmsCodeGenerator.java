package com.jinxiu.browser.generator.impl;

import com.jinxiu.browser.generator.ValidateCodeGenerator;
import com.jinxiu.browser.model.ValidateCode;
import com.jinxiu.properties.SecuritysProperties;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("smsCodeGenerator")
public class SmsCodeGenerator implements ValidateCodeGenerator {

    @Autowired
    private SecuritysProperties securitysProperties;

    @Override
    public ValidateCode generate() {
        String code = RandomStringUtils.randomNumeric(securitysProperties.getCode().getSmsCodeProperties().getLength());
        return new ValidateCode(code,securitysProperties.getCode().getSmsCodeProperties().getExpire());
    }
}

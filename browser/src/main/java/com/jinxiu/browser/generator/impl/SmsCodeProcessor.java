package com.jinxiu.browser.generator.impl;

import com.jinxiu.browser.model.ValidateCode;
import com.jinxiu.browser.sms.SmsCodeSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

@Component
public class SmsCodeProcessor extends AbstractValidateCodeProcessor {

    @Autowired
    private SmsCodeSender smsCodeSender;

    @Override
    void send(ServletWebRequest request, ValidateCode validateCode) {
        smsCodeSender.send(request.getParameter("mobile"),validateCode.getCode());
    }
}

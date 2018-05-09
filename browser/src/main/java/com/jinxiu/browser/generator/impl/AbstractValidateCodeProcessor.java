package com.jinxiu.browser.generator.impl;

import com.jinxiu.browser.generator.ValidateCodeGenerator;
import com.jinxiu.browser.generator.ValidateCodeProcessor;
import com.jinxiu.browser.model.ImageCode;
import com.jinxiu.browser.model.ValidateCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.ServletWebRequest;

import java.io.IOException;
import java.util.Map;

public abstract class AbstractValidateCodeProcessor implements ValidateCodeProcessor {

    @Autowired
    private Map<String,ValidateCodeGenerator> validateCodeGenerators;

    @Override
    public void create(ServletWebRequest request) throws Exception {
        ValidateCode validateCode = generate(request);
        save(request,validateCode);
        send(request,validateCode);
    }

    private ValidateCode generate(ServletWebRequest request) {
        String type = (String)request.getAttribute("type", ServletWebRequest.SCOPE_REQUEST);
        ValidateCodeGenerator generator = validateCodeGenerators.get(type + "Generator");
        return generator.generate();
    }


    private void save(ServletWebRequest request,ValidateCode validateCode) {
        String keyName = "SMS_CODE";
        if (validateCode instanceof ImageCode) {
            keyName = "IMAGE_CODE";
        }
        request.setAttribute(keyName,validateCode,ServletWebRequest.SCOPE_SESSION);
    }

    abstract void send(ServletWebRequest request,ValidateCode validateCode) throws IOException;
}

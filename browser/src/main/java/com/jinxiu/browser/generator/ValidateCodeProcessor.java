package com.jinxiu.browser.generator;

import org.springframework.web.context.request.ServletWebRequest;

public interface ValidateCodeProcessor {

    void create(ServletWebRequest request) throws Exception;
}

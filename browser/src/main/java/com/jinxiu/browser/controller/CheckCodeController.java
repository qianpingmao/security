package com.jinxiu.browser.controller;

import com.jinxiu.browser.generator.ValidateCodeGenerator;
import com.jinxiu.browser.generator.ValidateCodeProcessor;
import com.jinxiu.browser.model.ImageCode;
import com.jinxiu.browser.model.ValidateCode;
import com.jinxiu.browser.sms.SmsCodeSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Controller
public class CheckCodeController {


    @Autowired
    private Map<String,ValidateCodeProcessor> validateCodeProcessors;

    @RequestMapping("/imageCode")
    public void getImageCode(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ServletWebRequest webRequest = new ServletWebRequest(request,response);
        webRequest.setAttribute("type","imageCode",ServletWebRequest.SCOPE_REQUEST);
        validateCodeProcessors.get("imageCodeProcessor").create(webRequest);
    }


    @RequestMapping("/smsCode")
    public void getSmsCode(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ServletWebRequest webRequest = new ServletWebRequest(request,response);
        webRequest.setAttribute("type","smsCode",ServletWebRequest.SCOPE_REQUEST);

        validateCodeProcessors.get("smsCodeProcessor").create(webRequest);

    }



}

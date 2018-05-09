package com.jinxiu.browser.sms;

public class DefaultSmsCodeSender implements SmsCodeSender {
    @Override
    public void send(String mobile, String code) {
        System.out.println("向" + mobile + "发送验证码:" + code);
    }
}

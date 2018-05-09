package com.jinxiu.browser.generator;


import com.jinxiu.browser.generator.impl.ImageCodeGenerator;
import com.jinxiu.browser.sms.DefaultSmsCodeSender;
import com.jinxiu.browser.sms.SmsCodeSender;
import com.jinxiu.properties.SecuritysProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GeneratorConfig {

    @Autowired
    private SecuritysProperties securitysProperties;

    @Bean(name = "imageCodeGenerator")
    @ConditionalOnMissingBean(name = "imageCodeGenerator")
    public ValidateCodeGenerator getValidCodeGenerator() {
        ImageCodeGenerator imageCodeGenerator = new ImageCodeGenerator(securitysProperties);
        return imageCodeGenerator;
    }

    @Bean
    @ConditionalOnMissingBean(SmsCodeSender.class)
    public SmsCodeSender getSmsCodeSender() {
        return new DefaultSmsCodeSender();
    }
}

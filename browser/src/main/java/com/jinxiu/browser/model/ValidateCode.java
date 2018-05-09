package com.jinxiu.browser.model;

import org.apache.commons.lang.time.DateUtils;

import java.awt.image.BufferedImage;
import java.util.Date;

public class ValidateCode {

    private String code;

    private Date expireTime;

    public ValidateCode(String code,int seconds) {
        this.code = code;
        this.expireTime = DateUtils.addSeconds(new Date(),seconds);

    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }
}

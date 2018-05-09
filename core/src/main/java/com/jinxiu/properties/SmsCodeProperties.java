package com.jinxiu.properties;

public class SmsCodeProperties {
    private int length = 6;
    private int expire = 60;

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getExpire() {
        return expire;
    }

    public void setExpire(int expire) {
        this.expire = expire;
    }
}

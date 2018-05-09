package com.jinxiu.properties;

public class BrowserProperties {

    private String loginPage = "/signin.html";

    private LoginType loginType = LoginType.HTML;

    public String getLoginPage() {
        return loginPage;
    }

    public void setLoginPage(String loginPage) {
        this.loginPage = loginPage;
    }


    public LoginType getLoginType() {
        return loginType;
    }

    public void setLoginType(LoginType loginType) {
        this.loginType = loginType;
    }

    public static enum LoginType {
        HTML,JSON;
    }
}

package com.wxapp.requestentity.other;

import java.io.Serializable;

/**
 *  *             "account": "xxx",
 *  *             "pwd": "xxx",
 *  *             "a16_data62": "xxx"
 */
public class LoginUser implements Serializable {
    private String account;
    private String pwd;
    private String a16_data62;

    public LoginUser( ) {
    }

    public LoginUser(String account, String pwd, String a16_data62) {
        this.account = account;
        this.pwd = pwd;
        this.a16_data62 = a16_data62;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getA16_data62() {
        return a16_data62;
    }

    public void setA16_data62(String a16_data62) {
        this.a16_data62 = a16_data62;
    }
}

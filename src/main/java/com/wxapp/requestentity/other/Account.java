package com.wxapp.requestentity.other;

import java.io.Serializable;

public class Account implements Serializable {
    private String account;
    private String password;
    private String a16Data64;

    public Account( ) {
    }

    public Account(String account, String password, String a16Data64) {
        this.account = account;
        this.password = password;
        this.a16Data64 = a16Data64;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getA16Data64() {
        return a16Data64;
    }

    public void setA16Data64(String a16Data64) {
        this.a16Data64 = a16Data64;
    }
}

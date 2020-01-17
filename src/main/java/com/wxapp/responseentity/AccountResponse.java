package com.wxapp.responseentity;

import com.wxapp.responseentity.other.ResponseAddAccountData;

import java.io.Serializable;

public class AccountResponse implements Serializable {
    private ResponseAddAccountData data;
    private Integer code;
    private String msg;

    public AccountResponse( ) {
    }

    public AccountResponse(ResponseAddAccountData data, Integer code, String msg) {
        this.data = data;
        this.code = code;
        this.msg = msg;
    }

    public ResponseAddAccountData getData() {
        return data;
    }

    public void setData(ResponseAddAccountData data) {
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}

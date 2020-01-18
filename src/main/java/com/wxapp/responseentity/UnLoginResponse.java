package com.wxapp.responseentity;

import com.wxapp.responseentity.other.ResponseUnLoginData;

import java.io.Serializable;

public class UnLoginResponse implements Serializable {
    //{
    //"code": 0,0：全部成功，-1：全部失败，1：有成功有失败 <number>
    //-"data": {...<object>
    //-"success": [退出成功的账号<array>
    //""
    //],
    //-"error": [退出失败的账号<array>
    //""
    //]
    //},
    //"msg": "描述"状态描述 <string>
    //}
    private Integer code;
    private ResponseUnLoginData data;
    private String msg;

    public UnLoginResponse( ) {
    }

    public UnLoginResponse(Integer code, ResponseUnLoginData data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public ResponseUnLoginData getData() {
        return data;
    }

    public void setData(ResponseUnLoginData data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}

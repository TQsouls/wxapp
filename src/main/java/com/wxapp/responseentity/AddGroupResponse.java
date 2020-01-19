package com.wxapp.responseentity;

import com.wxapp.responseentity.other.ResponseOneGroupUrl;

import java.io.Serializable;
import java.util.List;

public class AddGroupResponse implements Serializable {
    private int code;
    private String msg;
    private List<ResponseOneGroupUrl> data;

    public AddGroupResponse( ) {
    }

    public AddGroupResponse(int code, String msg, List<ResponseOneGroupUrl> data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<ResponseOneGroupUrl> getData() {
        return data;
    }

    public void setData(List<ResponseOneGroupUrl> data) {
        this.data = data;
    }
}

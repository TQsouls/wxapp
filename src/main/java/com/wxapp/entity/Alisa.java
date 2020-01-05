package com.wxapp.entity;

import java.io.Serializable;

//设置微信号时使用
public class Alisa implements Serializable {
    private String alisa;
    private String wxId;

    public Alisa( ) {
    }

    public Alisa(String alisa, String wxId) {
        this.alisa = alisa;
        this.wxId = wxId;
    }

    public String getAlisa() {
        return alisa;
    }

    public void setAlisa(String alisa) {
        this.alisa = alisa;
    }

    public String getWxId() {
        return wxId;
    }

    public void setWxId(String wxId) {
        this.wxId = wxId;
    }
}

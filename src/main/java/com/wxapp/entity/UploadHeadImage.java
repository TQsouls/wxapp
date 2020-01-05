package com.wxapp.entity;

import java.io.Serializable;

public class UploadHeadImage implements Serializable {
    private String imgUrl;
    private String wxId;

    public UploadHeadImage( ) {
    }

    public UploadHeadImage(String imgUrl, String wxId) {
        this.imgUrl = imgUrl;
        this.wxId = wxId;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getWxId() {
        return wxId;
    }

    public void setWxId(String wxId) {
        this.wxId = wxId;
    }
}

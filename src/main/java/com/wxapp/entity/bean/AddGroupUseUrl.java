package com.wxapp.entity.bean;

public class AddGroupUseUrl {
    private String url;
    private String wxId;

    public AddGroupUseUrl( ) {
    }

    public AddGroupUseUrl(String url, String wxId) {
        this.url = url;
        this.wxId = wxId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getWxId() {
        return wxId;
    }

    public void setWxId(String wxId) {
        this.wxId = wxId;
    }
}

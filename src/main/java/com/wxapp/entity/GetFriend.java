package com.wxapp.entity;

public class GetFriend {
    private String wxId;
    private String type;

    public GetFriend( ) {
    }

    public GetFriend(String wxId, String type) {
        this.wxId = wxId;
        this.type = type;
    }

    public String getWxId() {
        return wxId;
    }

    public void setWxId(String wxId) {
        this.wxId = wxId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

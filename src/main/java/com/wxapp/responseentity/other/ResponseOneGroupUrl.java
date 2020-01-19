package com.wxapp.responseentity.other;

import java.io.Serializable;

public class ResponseOneGroupUrl implements Serializable {
    private String url;
    private int groupAmount;
    private ResponseOneGroup groupMemberFrom;

    public ResponseOneGroupUrl( ) {
    }

    public ResponseOneGroupUrl(String url, int groupAmount, ResponseOneGroup groupMemberFrom) {
        this.url = url;
        this.groupAmount = groupAmount;
        this.groupMemberFrom = groupMemberFrom;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getGroupAmount() {
        return groupAmount;
    }

    public void setGroupAmount(int groupAmount) {
        this.groupAmount = groupAmount;
    }

    public ResponseOneGroup getGroupMemberFrom() {
        return groupMemberFrom;
    }

    public void setGroupMemberFrom(ResponseOneGroup groupMemberFrom) {
        this.groupMemberFrom = groupMemberFrom;
    }
}

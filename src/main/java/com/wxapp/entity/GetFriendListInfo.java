package com.wxapp.entity;

import java.io.Serializable;

public class GetFriendListInfo implements Serializable {
    private String wxId;
    private int currentWxcontactSeq;
    private int currentChatRoomContactSeq;

    public GetFriendListInfo( ) {
    }

    public GetFriendListInfo(String wxId, int currentWxcontactSeq, int currentChatRoomContactSeq) {
        this.wxId = wxId;
        this.currentWxcontactSeq = currentWxcontactSeq;
        this.currentChatRoomContactSeq = currentChatRoomContactSeq;
    }

    public String getWxId() {
        return wxId;
    }

    public void setWxId(String wxId) {
        this.wxId = wxId;
    }

    public int getCurrentWxcontactSeq() {
        return currentWxcontactSeq;
    }

    public void setCurrentWxcontactSeq(int currentWxcontactSeq) {
        this.currentWxcontactSeq = currentWxcontactSeq;
    }

    public int getCurrentChatRoomContactSeq() {
        return currentChatRoomContactSeq;
    }

    public void setCurrentChatRoomContactSeq(int currentChatRoomContactSeq) {
        this.currentChatRoomContactSeq = currentChatRoomContactSeq;
    }
}

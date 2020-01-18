package com.wxapp.entity.bean;

import java.util.List;

public class AddGroupMember {
    private String chatRoomName;
    private List<String> toWxIds;
    private String wxId;

    public AddGroupMember( ) {
    }

    public AddGroupMember(String chatRoomName, List<String> toWxIds, String wxId) {
        this.chatRoomName = chatRoomName;
        this.toWxIds = toWxIds;
        this.wxId = wxId;
    }

    public String getChatRoomName() {
        return chatRoomName;
    }

    public void setChatRoomName(String chatRoomName) {
        this.chatRoomName = chatRoomName;
    }

    public List<String> getToWxIds() {
        return toWxIds;
    }

    public void setToWxIds(List<String> toWxIds) {
        this.toWxIds = toWxIds;
    }

    public String getWxId() {
        return wxId;
    }

    public void setWxId(String wxId) {
        this.wxId = wxId;
    }

    @Override
    public String toString() {
        return "AddGroupMember{" +
                "chatRoomName='" + chatRoomName + '\'' +
                ", toWxIds=" + toWxIds +
                ", wxId='" + wxId + '\'' +
                '}';
    }
}

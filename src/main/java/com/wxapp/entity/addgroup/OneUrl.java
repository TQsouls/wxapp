package com.wxapp.entity.addgroup;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OneUrl implements Serializable {
    //群聊的url
    private String url;
    //分配的号池
    public List<String> wxIds;
    //分配号池子的好友列表
    public Map<String,List<String>> friendList;
    public int currentCount;

    public OneUrl() {
    }

    public OneUrl(String url) {
        this.url = url;
        wxIds = new ArrayList<>();
        friendList = new HashMap<>();
        //微信最少3个人拉一个群聊
        currentCount = 3;
    }

    public OneUrl(List<String> wxIds, Map<String, List<String>> friendList) {
        this.wxIds = wxIds;
        this.friendList = friendList;
    }

    public List<String> getWxIds() {
        return wxIds;
    }

    public void setWxIds(List<String> wxIds) {
        this.wxIds = wxIds;
    }

    public Map<String, List<String>> getFriendList() {
        return friendList;
    }

    public void setFriendList(Map<String, List<String>> friendList) {
        this.friendList = friendList;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "OneUrl{" +
                "url='" + url + '\'' +
                ", wxIds=" + wxIds +
                ", friendList=" + friendList +
                ", currentCount=" + currentCount +
                '}';
    }
}

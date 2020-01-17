package com.wxapp.requestentity;

import com.wxapp.requestentity.other.Account;

import java.util.List;

public class AddAccountRequest {

    private List<Account> list;
    private Integer userId;
    private Integer groupId;

    public AddAccountRequest( ) {
    }

    public AddAccountRequest(List<Account> list, Integer userId, Integer groupId) {
        this.list = list;
        this.userId = userId;
        this.groupId = groupId;
    }

    public List<Account> getList() {
        return list;
    }

    public void setList(List<Account> list) {
        this.list = list;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }
}

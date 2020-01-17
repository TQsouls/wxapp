package com.wxapp.entity.bean;

import java.util.List;

public class AddGroupMember {
    private String groupName;
    private List<String> toWxIds;
    private String wxId;

    public AddGroupMember( ) {
    }

    public AddGroupMember(String groupName, List<String> toWxIds, String wxId) {
        this.groupName = groupName;
        this.toWxIds = toWxIds;
        this.wxId = wxId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
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
}

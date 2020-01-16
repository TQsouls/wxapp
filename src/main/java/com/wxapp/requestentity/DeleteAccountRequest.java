package com.wxapp.requestentity;

import java.util.List;

//删除账号请求
public class DeleteAccountRequest {

    private Integer groupId;
    private List<String> wxids;
    private Integer requestType;

    public DeleteAccountRequest( ) {
    }

    public DeleteAccountRequest(Integer groupId, List<String> wxids, Integer requestType) {
        this.groupId = groupId;
        this.wxids = wxids;
        this.requestType = requestType;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public List<String> getWxids() {
        return wxids;
    }

    public void setWxids(List<String> wxids) {
        this.wxids = wxids;
    }

    public Integer getRequestType() {
        return requestType;
    }

    public void setRequestType(Integer requestType) {
        this.requestType = requestType;
    }
}

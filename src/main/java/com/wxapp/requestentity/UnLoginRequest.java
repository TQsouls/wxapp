package com.wxapp.requestentity;

import java.io.Serializable;
import java.util.List;

public class UnLoginRequest implements Serializable {
//    {
//        "wxids": [
//        "1001",
//                "1002"
//    ],
//        "group_id": "",
//            "request_type": 0
//    }

    private List<String> wxids;
    private Integer groupId;
    private Integer requestType;

    public UnLoginRequest( ) {
    }

    public UnLoginRequest(List<String> wxids, Integer groupId, Integer requestType) {
        this.wxids = wxids;
        this.groupId = groupId;
        this.requestType = requestType;
    }

    public List<String> getWxids() {
        return wxids;
    }

    public void setWxids(List<String> wxids) {
        this.wxids = wxids;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getRequestType() {
        return requestType;
    }

    public void setRequestType(Integer requestType) {
        this.requestType = requestType;
    }
}

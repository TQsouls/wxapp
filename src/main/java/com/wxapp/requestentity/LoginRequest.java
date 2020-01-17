package com.wxapp.requestentity;

import com.wxapp.requestentity.other.LoginUser;

import java.io.Serializable;
import java.util.List;

/**
 * {
 *     "list": [
 *         {
 *             "account": "xxx",
 *             "pwd": "xxx",
 *             "a16_data62": "xxx"
 *         },
 *         {
 *             "account": "xxx",
 *             "pwd": "xxx",
 *             "a16_data62": "xxx"
 *         }
 *     ],
 *     "group_id": "1001",
 *     "request_type": 0
 * }
 */
public class LoginRequest implements Serializable {
    private List<LoginUser> list;
    private String group_id;
    private String request_type;

    public LoginRequest() {
    }

    public LoginRequest(List<LoginUser> list, String group_id, String request_type) {
        this.list = list;
        this.group_id = group_id;
        this.request_type = request_type;
    }

    public List<LoginUser> getList() {
        return list;
    }

    public void setList(List<LoginUser> list) {
        this.list = list;
    }

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

    public String getRequest_type() {
        return request_type;
    }

    public void setRequest_type(String request_type) {
        this.request_type = request_type;
    }
}

package com.wxapp.responseentity;

import com.wxapp.responseentity.other.ResponseUserData;

import java.io.Serializable;

/**
 * {
 * "code": 0,状态码。0：成功，-1：失败 <number>
 * -"data": {返回的数据（登录成功就返回该账号的数据，失败则不用返回）<object>
    * -"success": [...<array>
                 * -{
                     * "account": "",... <string>
                     * "account_pwd": "",... <string>
                     * "account_62_a16": "",... <string>
                     * "tag_name": "",... <string>
                     * "tag_id": "",... <string>
                     * "account_isValid": "",... <string>
                     * "account_state": "",... <string>
                     * "account_friendCount": "",... <string>
                     * "account_wxid": "",... <string>
                     * "group_name": "",... <string>
                     * "group_id": "",... <string>
                     * "user_id": ""... <string>
                 * }
             * ],
         * -"error": [...<array>
         * "1003"
         * ]
 * },
 * "msg": "描述"返回的状态描述 <string>
 * }
 */
public class LoginResponse implements Serializable {
    private String code;
    private ResponseUserData data;
    private String msg;

    public LoginResponse( ) {
    }

    public LoginResponse(String code, ResponseUserData data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public ResponseUserData getData() {
        return data;
    }

    public void setData(ResponseUserData data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}

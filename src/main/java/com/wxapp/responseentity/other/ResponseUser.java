package com.wxapp.responseentity.other;

import java.io.Serializable;

/**
 *                  * -{
 *                      * "account": "",... <string>
 *                      * "account_pwd": "",... <string>
 *                      * "account_62_a16": "",... <string>
 *                      * "tag_name": "",... <string>
 *                      * "tag_id": "",... <string>
 *                      * "account_isValid": "",... <string>
 *                      * "account_state": "",... <string>
 *                      * "account_friendCount": "",... <string>
 *                      * "account_wxid": "",... <string>
 *                      * "group_name": "",... <string>
 *                      * "group_id": "",... <string>
 *                      * "user_id": ""... <string>
 *                  * }
 */
public class ResponseUser implements Serializable {
    // private String account;
    //  private String accountPwd;
    //  private String account62A16;
    //  private String accountTag;
    //  private String accountIsValid;
    //  private String accountState;
    //  private long accountFriendCount;
    //  private String accountWxid;
    //  private long userId;

    private String account;
    private String account_pwd;
    private String account_62_a16;
    private String tag_name;
    private String tag_id;
    private String account_isValid;
    private String account_state;
    private String account_friendCount;
    private String account_wxid;
    private String group_name;
    private String group_id;
    private String user_id;

    public ResponseUser( ) {
    }

    public ResponseUser(String account, String account_pwd, String account_62_a16, String tag_name, String tag_id, String account_isValid, String account_state, String account_friendCount, String account_wxid, String group_name, String group_id, String user_id) {
        this.account = account;
        this.account_pwd = account_pwd;
        this.account_62_a16 = account_62_a16;
        this.tag_name = tag_name;
        this.tag_id = tag_id;
        this.account_isValid = account_isValid;
        this.account_state = account_state;
        this.account_friendCount = account_friendCount;
        this.account_wxid = account_wxid;
        this.group_name = group_name;
        this.group_id = group_id;
        this.user_id = user_id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getAccount_pwd() {
        return account_pwd;
    }

    public void setAccount_pwd(String account_pwd) {
        this.account_pwd = account_pwd;
    }

    public String getAccount_62_a16() {
        return account_62_a16;
    }

    public void setAccount_62_a16(String account_62_a16) {
        this.account_62_a16 = account_62_a16;
    }

    public String getTag_name() {
        return tag_name;
    }

    public void setTag_name(String tag_name) {
        this.tag_name = tag_name;
    }

    public String getTag_id() {
        return tag_id;
    }

    public void setTag_id(String tag_id) {
        this.tag_id = tag_id;
    }

    public String getAccount_isValid() {
        return account_isValid;
    }

    public void setAccount_isValid(String account_isValid) {
        this.account_isValid = account_isValid;
    }

    public String getAccount_state() {
        return account_state;
    }

    public void setAccount_state(String account_state) {
        this.account_state = account_state;
    }

    public String getAccount_friendCount() {
        return account_friendCount;
    }

    public void setAccount_friendCount(String account_friendCount) {
        this.account_friendCount = account_friendCount;
    }

    public String getAccount_wxid() {
        return account_wxid;
    }

    public void setAccount_wxid(String account_wxid) {
        this.account_wxid = account_wxid;
    }

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}

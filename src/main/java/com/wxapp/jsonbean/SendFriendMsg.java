/**
  * Copyright 2019 bejson.com 
  */
package com.wxapp.jsonbean;

import java.io.Serializable;

/**
 * Auto-generated: 2019-12-31 15:56:46
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class SendFriendMsg implements Serializable {

    private String msg;
    private Account account;

    public SendFriendMsg( ) {
    }

    public SendFriendMsg(String msg, Account account) {
        this.msg = msg;
        this.account = account;
    }

    public void setMsg(String msg) {
         this.msg = msg;
     }
     public String getMsg() {
         return msg;
     }

    public void setAccount(Account account) {
         this.account = account;
     }
     public Account getAccount() {
         return account;
     }

}
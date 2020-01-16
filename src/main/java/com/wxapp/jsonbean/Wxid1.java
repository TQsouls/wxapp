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
public class Wxid1 implements Serializable {

    private String enterTime;
    private String phone;
    private String nickname;

    public Wxid1( ) {
    }

    public Wxid1(String enterTime, String phone, String nickname) {
        this.enterTime = enterTime;
        this.phone = phone;
        this.nickname = nickname;
    }

    public void setEnterTime(String enterTime) {
         this.enterTime = enterTime;
     }
     public String getEnterTime() {
         return enterTime;
     }

    public void setPhone(String phone) {
         this.phone = phone;
     }
     public String getPhone() {
         return phone;
     }

    public void setNickname(String nickname) {
         this.nickname = nickname;
     }
     public String getNickname() {
         return nickname;
     }

}
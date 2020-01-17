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
public class Account implements Serializable {

    private String from_wxid;


    public Account( ) {
    }

    public Account(String from_wxid) {
        this.from_wxid = from_wxid;
    }

    public void setfrom_wxid(String from_wxid) {
         this.from_wxid = from_wxid;
     }
     public String getfrom_wxid() {
         return from_wxid;
     }


}
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
public class JsonRootBean implements Serializable {

    private User_id user_id;

    public JsonRootBean( ) {
    }

    public JsonRootBean(User_id user_id) {
        this.user_id = user_id;
    }

    public User_id getUser_id() {
        return user_id;
    }

    public void setUser_id(User_id user_id) {
        this.user_id = user_id;
    }
}
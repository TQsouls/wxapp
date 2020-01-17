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
public class InviteGroup implements Serializable {

    private GroupURL groupURL;

    public InviteGroup( ) {
    }

    public InviteGroup(GroupURL groupURL) {
        this.groupURL = groupURL;
    }

    public void setGroupURL(GroupURL groupURL) {
         this.groupURL = groupURL;
     }
     public GroupURL getGroupURL() {
         return groupURL;
     }

}
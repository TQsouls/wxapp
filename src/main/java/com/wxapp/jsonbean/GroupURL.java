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
public class GroupURL implements Serializable {

    private String groupMemberCount;
    private Members members;

    public GroupURL( ) {
    }

    public GroupURL(String groupMemberCount, Members members) {
        this.groupMemberCount = groupMemberCount;
        this.members = members;
    }

    public void setGroupMemberCount(String groupMemberCount) {
         this.groupMemberCount = groupMemberCount;
     }
     public String getGroupMemberCount() {
         return groupMemberCount;
     }

    public void setMembers(Members members) {
         this.members = members;
     }
     public Members getMembers() {
         return members;
     }

}
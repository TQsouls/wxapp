/**
  * Copyright 2019 bejson.com 
  */
package com.wxapp.jsonbean;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Auto-generated: 2019-12-31 15:56:46
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class FriendCounter implements Serializable {

    private Integer friendCount;
    private List<String> friendWxids;

    public FriendCounter( ) {
    }

    public FriendCounter(Integer friendCount, List<String> friendWxids) {
        this.friendCount = friendCount;
        this.friendWxids = friendWxids;
    }

    public Integer getFriendCount() {
        return friendCount;
    }

    public void setFriendCount(Integer friendCount) {
        this.friendCount = friendCount;
    }

    public List<String> getFriendWxids() {
        return friendWxids;
    }

    public void setFriendWxids(List<String> friendWxids) {
        this.friendWxids = friendWxids;
    }
}
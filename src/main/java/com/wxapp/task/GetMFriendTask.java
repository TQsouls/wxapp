package com.wxapp.task;

import com.wxapp.api.friend.FriendAction;
import com.wxapp.entity.GetFriend;

import java.util.concurrent.Callable;

public class GetMFriendTask implements Callable<String> {

    private GetFriend getFriend;
    private FriendAction friendAction;

    public GetMFriendTask( FriendAction friendAction,GetFriend getFriend) {
        this.getFriend = getFriend;
        this.friendAction = friendAction;
    }

    @Override
    public String call() throws Exception {
        String mFriend = friendAction.getMFriend(getFriend);
        return mFriend;
    }
}

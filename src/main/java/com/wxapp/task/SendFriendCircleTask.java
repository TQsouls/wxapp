package com.wxapp.task;

import com.wxapp.api.friendcycle.FriendCircleApi;
import com.wxapp.entity.FriendCircle;

import java.util.concurrent.Callable;

public class SendFriendCircleTask implements Callable<String> {

    private FriendCircleApi friendCircleApi;
    private FriendCircle friendCircle;

    public SendFriendCircleTask(FriendCircleApi friendCircleApi, FriendCircle friendCircle) {
        this.friendCircleApi = friendCircleApi;
        this.friendCircle = friendCircle;
    }

    @Override
    public String call() throws Exception {
        String status = friendCircleApi.sendFriendCircle(friendCircle);
        return status;
    }
}

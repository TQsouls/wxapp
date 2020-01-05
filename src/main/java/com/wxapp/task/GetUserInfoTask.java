package com.wxapp.task;

import com.wxapp.api.user.UserOperating;

import java.util.concurrent.Callable;

public class GetUserInfoTask implements Callable<String> {

    private UserOperating userOperating;
    private String wxId;


    public GetUserInfoTask(UserOperating userOperating, String wxId) {
        this.userOperating = userOperating;
        this.wxId = wxId;
    }

    @Override
    public String call() throws Exception {
        String userInfo = userOperating.getUserInfo(wxId);
        return userInfo;
    }
}

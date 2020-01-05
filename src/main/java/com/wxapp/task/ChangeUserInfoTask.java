package com.wxapp.task;

import com.wxapp.api.user.UserOperating;
import com.wxapp.entity.User;

import java.util.concurrent.Callable;

public class ChangeUserInfoTask implements Callable<String> {
    private UserOperating userOperating;
    private User user;

    public ChangeUserInfoTask(UserOperating userOperating, User user) {
        this.userOperating = userOperating;
        this.user = user;
    }

    @Override
    public String call() throws Exception {
        String updateProfileResult = userOperating.updateProfile(user);
        return updateProfileResult;
    }
}

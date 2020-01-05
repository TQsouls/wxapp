package com.wxapp.task;

import com.wxapp.api.user.UserOperating;
import com.wxapp.entity.Alisa;

import java.util.concurrent.Callable;

public class SetAlisaTask implements Callable<String> {
    private UserOperating userOperating;
    private Alisa alisa;

    public SetAlisaTask(UserOperating userOperating, Alisa alisa) {
        this.userOperating = userOperating;
        this.alisa = alisa;
    }

    @Override
    public String call() throws Exception {
        String setAlisaResult = userOperating.setAlisa(alisa);
        return setAlisaResult;
    }
}

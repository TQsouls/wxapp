package com.wxapp.task;

import com.alibaba.fastjson.JSON;
import com.wxapp.api.login.UnLoginApi;

import java.util.concurrent.Callable;

public class UnLoginTask implements Callable<String> {
    private UnLoginApi unLoginApi;
    private String wxid;

    public UnLoginTask(UnLoginApi unLoginApi, String wxid) {
        this.unLoginApi = unLoginApi;
        this.wxid = wxid;
    }

    @Override
    public String call() throws Exception {
        String unLoginResult = unLoginApi.unLogin(wxid);
        String isSuccess = JSON.parseObject(unLoginResult).get("Success").toString();
        System.out.println(isSuccess);
        return isSuccess+wxid;
    }
}

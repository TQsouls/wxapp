package com.wxapp.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wxapp.api.login.A16Login;
import com.wxapp.api.login.Data62Login;
import com.wxapp.entity.Data62User;
import com.wxapp.requestentity.LoginRequest;
import com.wxapp.requestentity.other.LoginUser;
import com.wxapp.responseentity.LoginResponse;
import com.wxapp.responseentity.other.ResponseUser;
import com.wxapp.responseentity.other.ResponseUserData;
import com.wxapp.task.A16LoginTask;
import com.wxapp.task.Data62LoginTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/**
 * 最终版本的 controller
 * 根据接口文档实现
 */
@RestController
public class FinallyController {

    @Autowired
    A16Login a16Login;
    @Autowired
    Data62Login data62Login;


    @Autowired
    ExecutorService executorService;

    @PostMapping("/api/account/loginMulti")
    public String loginMulti(LoginRequest loginRequest){
        List<LoginUser> list = loginRequest.getList();

        List<Future<String>> futureList = new ArrayList<>();
        for (LoginUser loginUser : list) {
            String a16_data62 = loginUser.getA16_data62();
            if (a16_data62.length() > 50){
                Future<String> submit = executorService.submit(new Data62LoginTask(data62Login, loginUser,loginRequest.getGroup_id()));
                futureList.add(submit);
            }else {
                Future<String> submit = executorService.submit(new A16LoginTask(a16Login, loginUser,loginRequest.getGroup_id()));
                futureList.add(submit);
            }
        }


        List<String> error = new ArrayList<>();
        List<ResponseUser> success = new ArrayList<>();

        for (Future<String> stringFuture : futureList) {
            String returnJson = null;
            try {
                returnJson = stringFuture.get();
                ResponseUser responseUser = JSON.parseObject(returnJson, ResponseUser.class);
                success.add(responseUser);
                System.out.println(JSON.toJSONString(success));
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

        }
        System.out.println(JSON.toJSONString(success));
        ResponseUserData responseUserData = new ResponseUserData();
        responseUserData.setError(error);
        responseUserData.setSuccess(success);
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setData(responseUserData);
        loginResponse.setCode("code");
        loginResponse.setMsg("everything is OK");

        return JSON.toJSONString(loginResponse);
    }

}

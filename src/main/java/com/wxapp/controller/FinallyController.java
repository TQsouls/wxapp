package com.wxapp.controller;

import com.alibaba.fastjson.JSON;
import com.wxapp.api.friend.FriendAction;
import com.wxapp.api.login.A16Login;
import com.wxapp.api.login.Data62Login;
import com.wxapp.api.login.UnLoginApi;
import com.wxapp.dbbean.TbUserAccountEntity;
import com.wxapp.requestentity.AddAccountRequest;
import com.wxapp.requestentity.DeleteAccountRequest;
import com.wxapp.requestentity.LoginRequest;
import com.wxapp.requestentity.UnLoginRequest;
import com.wxapp.requestentity.other.Account;
import com.wxapp.requestentity.other.LoginUser;
import com.wxapp.responseentity.LoginResponse;
import com.wxapp.responseentity.UnLoginResponse;
import com.wxapp.responseentity.other.ResponseUnLoginData;
import com.wxapp.responseentity.other.ResponseUserData;
import com.wxapp.service.AccountService;
import com.wxapp.task.A16LoginTask;
import com.wxapp.task.Data62LoginTask;
import com.wxapp.task.UnLoginTask;
import com.wxapp.util.RedisUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
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
@Api(tags = "最终版本")
@RestController
public class FinallyController {

    @Autowired
    A16Login a16Login;
    @Autowired
    Data62Login data62Login;
    @Autowired
    UnLoginApi unLoginApi;
    @Autowired
    FriendAction friendAction;

    @Autowired
    RedisUtil redisUtil;//自动注入 redis

    @Autowired
    ExecutorService executorService;

    @Autowired
    AccountService accountService;

    //一键登录多个账号
    @ApiOperation("一键登录多个账号")
    @PostMapping("/api/account/loginMulti")
    public String loginMulti(LoginRequest loginRequest){
        List<LoginUser> list = loginRequest.getList();

        List<Future<String>> futureList = new ArrayList<>();
        //进行 a16 data62 登录
        for (LoginUser loginUser : list) {
            String a16_data62 = loginUser.getA16_data62();
            if (a16_data62.length() > 50){
                Future<String> submit = executorService.submit(new Data62LoginTask(friendAction,redisUtil,data62Login, loginUser,loginRequest.getGroup_id()));
                futureList.add(submit);
            }else {
                Future<String> submit = executorService.submit(new A16LoginTask(friendAction,redisUtil,a16Login, loginUser,loginRequest.getGroup_id()));
                futureList.add(submit);
            }
        }
        //返回的成功和失败列表
        List<TbUserAccountEntity> error = new ArrayList<>();
        List<TbUserAccountEntity> success = new ArrayList<>();
        for (Future<String> stringFuture : futureList) {
            String returnJson = null;
            try {
                returnJson = stringFuture.get();
                TbUserAccountEntity responseUser = JSON.parseObject(returnJson, TbUserAccountEntity.class);
                if (responseUser.getAccountWxid().equals("error")) {
                    error.add(responseUser);
                }else {
                    success.add(responseUser);
                }
                System.out.println(JSON.toJSONString(success));
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        ResponseUserData responseUserData = new ResponseUserData();
        responseUserData.setError(error);
        responseUserData.setSuccess(success);
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setData(responseUserData);
        loginResponse.setCode("200");
        loginResponse.setMsg("everything is OK");
        return JSON.toJSONString(loginResponse);
    }

    //一键退出多个账号
    @ApiOperation("一键退出多个账号")
    @PostMapping("/api/account/logout")
    public String unLogin(UnLoginRequest unLoginRequest){
        List<Future<String>> futureList = new ArrayList<>();
        for (String wxId : unLoginRequest.getWxids()) {
            Future<String> submit = executorService.submit(new UnLoginTask(unLoginApi, wxId));
            futureList.add(submit);
        }
        UnLoginResponse unLoginResponse = new UnLoginResponse();
        ResponseUnLoginData responseUnLoginData = new ResponseUnLoginData();
        List<String> success = new ArrayList<>();
        List<String> error = new ArrayList<>();
        for (Future<String> stringFuture : futureList) {
            try {
                String response = stringFuture.get();
                if (response.startsWith("true")){
                    response = response.replaceAll("true","");
                    success.add(response);
                }else {
                    response = response.replaceAll("false","");
                    error.add(response);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        responseUnLoginData.setSuccess(success);
        responseUnLoginData.setError(error);
        //设置返回的状态吗和消息
        if (responseUnLoginData.getSuccess().size() == 0){
            unLoginResponse.setCode(-1);
            unLoginResponse.setMsg("全部失败");
        }else if (responseUnLoginData.getSuccess().size() < futureList.size()){
            unLoginResponse.setCode(-1);
            unLoginResponse.setMsg("有成功有失败");
        }else {
            unLoginResponse.setCode(0);
            unLoginResponse.setMsg("全部成功");
        }

        unLoginResponse.setData(responseUnLoginData);

        return JSON.toJSONString(unLoginResponse);
    }

    @ApiOperation("一键添加多个账号")
    @PostMapping("/api/account/addAccount")//这个的请求参数和登录参数一致
    public String addAccount(AddAccountRequest addAccountRequest){
        TbUserAccountEntity tbUserAccountEntity = null;
        for (Account account : addAccountRequest.getList()) {
            tbUserAccountEntity = new TbUserAccountEntity(account.getAccount(),account.getPassword(),account.getA16Data64(),addAccountRequest.getUserId(),addAccountRequest.getGroupId());
            accountService.saveOneAccount(tbUserAccountEntity);
        }
        return "success";
    }

    @ApiOperation("删除账号")
    @PostMapping("/api/account/deleteAccount")
    public String deleteAccount(DeleteAccountRequest deleteAccountRequest){

        return "success";
    }

    @ApiOperation("提交微信群 url")
    @PostMapping("/api/group/setGroupURL")
    public String setGroupURL(List<String> grpUrl){

        return "success";
    }

    @ApiOperation("根据微信号标签邀请进群")
    @PostMapping("/api/group/enterGroup")
    public String enterGroup(Integer tagId,Integer opType){

        return "success";
    }
}






































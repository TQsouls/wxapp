package com.wxapp.controller;

import com.alibaba.fastjson.JSON;
import com.wxapp.api.friend.FriendAction;
import com.wxapp.api.group.GroupApi;
import com.wxapp.api.login.A16Login;
import com.wxapp.api.login.Data62Login;
import com.wxapp.api.login.UnLoginApi;
import com.wxapp.dao.TbUserAccountDao;
import com.wxapp.dbbean.TbUserAccountEntity;
import com.wxapp.entity.addgroup.OneUrl;
import com.wxapp.requestentity.AddAccountRequest;
import com.wxapp.requestentity.DeleteAccountRequest;
import com.wxapp.requestentity.LoginRequest;
import com.wxapp.requestentity.UnLoginRequest;
import com.wxapp.requestentity.other.Account;
import com.wxapp.requestentity.other.LoginUser;
import com.wxapp.responseentity.*;
import com.wxapp.responseentity.other.*;
import com.wxapp.service.AccountService;
import com.wxapp.service.GroupService;
import com.wxapp.service.LoginService;
import com.wxapp.task.A16LoginTask;
import com.wxapp.task.Data62LoginTask;
import com.wxapp.task.GroupTask;
import com.wxapp.task.UnLoginTask;
import com.wxapp.util.RedisUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
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
    GroupApi groupApi;

    @Autowired
    RedisUtil redisUtil;//自动注入 redis

    @Autowired
    ExecutorService executorService;

    @Autowired
    AccountService accountService;
    @Autowired
    LoginService loginService;
    @Autowired
    GroupService groupService;

    @Autowired
    TbUserAccountDao tbUserAccountDao;

    //一键登录多个账号
    @ApiOperation("一键登录多个账号")
    @PostMapping("/api/account/loginMulti")
    public String loginMulti(LoginRequest loginRequest){

        List<LoginUser> list = null;
        if (loginRequest.getRequest_type().equals("0")){
            list = loginService.getAllByGroupId(Integer.valueOf(loginRequest.getGroup_id()));
        }else {
            list = loginRequest.getList();
        }

        List<Future<String>> futureList = new ArrayList<>();
        //进行 a16 data62 登录
        for (LoginUser loginUser : list) {
            String a16_data62 = loginUser.getA16_data62();
            if (a16_data62.length() > 50){
                Future<String> submit = executorService.submit(new Data62LoginTask(friendAction,redisUtil,data62Login, loginUser,loginRequest.getGroup_id(),tbUserAccountDao));
                futureList.add(submit);
            }else {
                Future<String> submit = executorService.submit(new A16LoginTask(friendAction,redisUtil,a16Login, loginUser,loginRequest.getGroup_id(),tbUserAccountDao));
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

        List<String> wxIds = null;
        if (unLoginRequest.getRequestType().equals("0")){
            wxIds = loginService.getLoginWxidsByGroupId(Integer.valueOf(unLoginRequest.getGroupId()));
        }else {
            wxIds = unLoginRequest.getWxids();
        }

        if (null != wxIds) {
            for (String wxId : wxIds) {
                Future<String> submit = executorService.submit(new UnLoginTask(redisUtil,unLoginApi, wxId,unLoginRequest.getGroupId().toString()));
                futureList.add(submit);
            }
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
        AccountResponse accountResponse = new AccountResponse();
        ResponseAddAccountData responseAddAccountData = new ResponseAddAccountData();
        List<Account> success = new ArrayList<>();
        List<Account> error = new ArrayList<>();

        TbUserAccountEntity tbUserAccountEntity = null;
        for (Account account : addAccountRequest.getList()) {
            tbUserAccountEntity = new TbUserAccountEntity(account.getAccount(),account.getPassword(),account.getA16Data64(),addAccountRequest.getUserId(),addAccountRequest.getGroupId());
            try {
                accountService.saveOneAccount(tbUserAccountEntity);
                success.add(account);
            }catch (Exception e){
                error.add(account);
            }
        }
        responseAddAccountData.setSuccess(success);
        responseAddAccountData.setError(error);

        accountResponse.setData(responseAddAccountData);
        if (success.size() == 0) {
            accountResponse.setCode(-1);
        }else if (error.size() > 0 && success.size()>0){
            accountResponse.setCode(1);
        }else {
            accountResponse.setCode(0);
        }
        accountResponse.setMsg("everything is ok!");
        return JSON.toJSONString(accountResponse);
    }

    @ApiOperation("删除账号")
    @PostMapping("/api/account/deleteAccount")
    public String deleteAccount(DeleteAccountRequest deleteAccountRequest){
        UnLoginResponse unLoginResponse = new UnLoginResponse();
        ResponseUnLoginData responseUnLoginData = new ResponseUnLoginData();
        List<String> success = new ArrayList<>();
        List<String> error = new ArrayList<>();

        for (String wxid : deleteAccountRequest.getWxids()) {
            try {
                accountService.deleteOneAccount(wxid);
                success.add(wxid);
            }catch (Exception e){
                error.add(wxid);
            }
        }
        if (success.size() == 0) {
            unLoginResponse.setCode(-1);
        }else if (error.size() > 0 && success.size()>0){
            unLoginResponse.setCode(1);
        }else {
            unLoginResponse.setCode(0);
        }
        responseUnLoginData.setError(error);
        responseUnLoginData.setSuccess(success);
        unLoginResponse.setData(responseUnLoginData);
        unLoginResponse.setMsg("everything is ok!");
        return JSON.toJSONString(unLoginResponse);
    }

    /**
     * 提交微信群 url 到缓存区
     * @param grpUrl
     * @return
     */
    @ApiOperation("提交微信群 url")
    @PostMapping("/api/group/setGroupURL")
    public String setGroupURL(@RequestParam(value="grpUrl",required=true)List<String> grpUrl){
        int code = groupService.submitUrl(grpUrl)?0:1;
        SubmitUrlResponse submitUrlResponse = new SubmitUrlResponse(code,code==0?"提交成功":"提交失败");
        return JSON.toJSONString(submitUrlResponse);
    }

    @ApiOperation("根据微信号标签邀请进群")
    @PostMapping("/api/group/enterGroup")
    public String enterGroup(Integer tagId,Integer opType){

        List<OneUrl> distribution = groupService.distribution(tagId, opType);
        List<Future<String>> futureList = new ArrayList<>();
        // GroupTask 类里写加群逻辑
        AddGroupResponse addGroupResponse = new AddGroupResponse();
        addGroupResponse.setCode(0);
        addGroupResponse.setMsg("拉群完成");
        List<ResponseOneGroupUrl> responseOneGroupUrlList = new ArrayList<>();
        for (OneUrl oneUrl : distribution) {
            Future<String> submit = executorService.submit(new GroupTask(groupApi, oneUrl));
            futureList.add(submit);
            responseOneGroupUrlList.add(new ResponseOneGroupUrl(oneUrl.getUrl(),oneUrl.currentCount,new ResponseOneGroup(oneUrl.currentCount-3,new Date())));
        }
        addGroupResponse.setData(responseOneGroupUrlList);
        return JSON.toJSONString(addGroupResponse);
    }
}

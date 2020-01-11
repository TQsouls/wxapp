package com.wxapp.task;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wxapp.api.login.A16Login;
import com.wxapp.entity.A16User;
import com.wxapp.requestentity.other.LoginUser;
import com.wxapp.responseentity.other.ResponseUser;

import java.util.concurrent.Callable;

public class A16LoginTask implements Callable<String> {

    private A16Login a16Login;
    private A16User a16User = null;//为空就构造
    private String group_id;

    //应对新接口
    private LoginUser loginUser;

    public A16LoginTask(A16Login a16Login, A16User a16User) {
        this.a16Login = a16Login;
        this.a16User = a16User;
    }

    //应对新接口的构造器
    public A16LoginTask(A16Login a16Login, LoginUser loginUser,String group_id) {
        this.a16Login = a16Login;
        this.loginUser = loginUser;
        this.group_id = group_id;
    }

    @Override
    public String call() throws Exception {
        //应对新接口
        if (null == a16User){
            a16User = new A16User();
            a16User.setWechatAccount(loginUser.getAccount());
            a16User.setWechatPassword(loginUser.getPwd());
            a16User.setWechatA16Data(loginUser.getA16_data62());
        }
        String a16LoginInfo = a16Login.weixinA16Login(a16User);
        System.out.println(a16User);
        System.out.println(a16LoginInfo);
        JSONObject data = JSON.parseObject(JSON.parseObject(a16LoginInfo).get("Data").toString());
        if (null == data){
            ResponseUser responseUser = new ResponseUser(
                    a16User.getWechatAccount(), a16User.getWechatPassword(), a16User.getWechatA16Data(),
                    "tag_name", "tag_id", "account_isValid",
                    "account_state", "account_friendCount",
                    "wxId", "group_name", group_id, "user_id"
            );
            return JSON.toJSONString(responseUser);
        }else {
            JSONObject baseResponse = JSON.parseObject(data.get("baseResponse").toString());
            JSONObject accountInfo = JSON.parseObject(data.get("accountInfo").toString());
            String wxId = accountInfo.get("wxid").toString();

            Object errMsg = baseResponse.get("errMsg");
            System.out.println(errMsg);

            ResponseUser responseUser = new ResponseUser(
                    a16User.getWechatAccount(), a16User.getWechatPassword(), a16User.getWechatA16Data(),
                    "tag_name", "tag_id", "account_isValid",
                    "account_state", "account_friendCount",
                    wxId, "group_name", group_id, "user_id"
            );
            return JSON.toJSONString(responseUser);
        }
    }
}

package com.wxapp.task;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wxapp.api.friend.FriendAction;
import com.wxapp.api.login.A16Login;
import com.wxapp.dbbean.TbUserAccountEntity;
import com.wxapp.entity.A16User;
import com.wxapp.entity.GetFriendListInfo;
import com.wxapp.jsonbean.FriendCounter;
import com.wxapp.requestentity.other.LoginUser;
import com.wxapp.util.RedisUtil;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.concurrent.Callable;

public class A16LoginTask implements Callable<String> {

    private A16Login a16Login;
    private A16User a16User = null;//为空就构造
    private String group_id;
    private FriendAction friendAction;
    private RedisUtil redisUtil;

    //应对新接口
    private LoginUser loginUser;

    public A16LoginTask(A16Login a16Login, A16User a16User) {
        this.a16Login = a16Login;
        this.a16User = a16User;
    }

    //应对新接口的构造器
    public A16LoginTask(FriendAction friendAction, RedisUtil redisUtil, A16Login a16Login, LoginUser loginUser, String group_id) {
        this.a16Login = a16Login;
        this.loginUser = loginUser;
        this.group_id = group_id;
        this.friendAction = friendAction;
        this.redisUtil = redisUtil;
    }

    @Override
    public String call() throws Exception {
        //应对新接口
        if (null == a16User) {
            a16User = new A16User();
            a16User.setWechatAccount(loginUser.getAccount());
            a16User.setWechatPassword(loginUser.getPwd());
            a16User.setWechatA16Data(loginUser.getA16_data62());
        }
        String a16LoginInfo = a16Login.weixinA16Login(a16User);

        Jedis jedis = redisUtil.getJedis();
        try {
            JSONObject data = JSON.parseObject(JSON.parseObject(a16LoginInfo).get("Data").toString());
            JSONObject baseResponse = JSON.parseObject(data.get("baseResponse").toString());
            JSONObject accountInfo = JSON.parseObject(data.get("accountInfo").toString());
            String wxId = accountInfo.get("wxid").toString();

            Object errMsg = baseResponse.get("errMsg");
            System.out.println(errMsg);
            ArrayList<String> friendList = friendAction.getFriendList(new GetFriendListInfo(wxId, 0, 0));


            jedis.set("friendList:"+wxId,JSON.toJSONString(new FriendCounter(friendList.size(),friendList)));

            TbUserAccountEntity responseUser = new TbUserAccountEntity(
                    a16User.getWechatAccount(), a16User.getWechatPassword(), a16User.getWechatA16Data(),
                    true, true, friendList.size(),
                    wxId, 0,
                    "tag_name", "group_name", 0, 0
            );
            return JSON.toJSONString(responseUser);
        } catch (Exception e) {
            TbUserAccountEntity responseUser = new TbUserAccountEntity(
                    a16User.getWechatAccount(), a16User.getWechatPassword(), a16User.getWechatA16Data(),
                    false, false, 0,
                    "error", 0,
                    "tag_name", "group_name", 0, 0
            );
            return JSON.toJSONString(responseUser);
        }finally {
            jedis.close();
        }


    }

}

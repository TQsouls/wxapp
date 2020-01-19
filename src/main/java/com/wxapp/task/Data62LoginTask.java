package com.wxapp.task;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wxapp.api.friend.FriendAction;
import com.wxapp.api.login.Data62Login;
import com.wxapp.dao.TbUserAccountDao;
import com.wxapp.dbbean.TbUserAccountEntity;
import com.wxapp.entity.Data62User;
import com.wxapp.entity.GetFriendListInfo;
import com.wxapp.jsonbean.FriendCounter;
import com.wxapp.requestentity.other.LoginUser;
import com.wxapp.util.RedisUtil;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.concurrent.Callable;

public class Data62LoginTask implements Callable<String> {

    private Data62Login data62Login;
    private Data62User data62User = null;
    private FriendAction friendAction;
    private RedisUtil redisUtil;
    private TbUserAccountDao tbUserAccountDao;


    private String group_id;
    private LoginUser loginUser;

    public Data62LoginTask(FriendAction friendAction, RedisUtil redisUtil, Data62Login data62Login, Data62User data62User) {
        this.data62Login = data62Login;
        this.data62User = data62User;
        this.friendAction = friendAction;
        this.redisUtil = redisUtil;
    }

    public Data62LoginTask(FriendAction friendAction, RedisUtil redisUtil, Data62Login data62Login, LoginUser loginUser,String group_id,TbUserAccountDao tbUserAccountDao) {
        this.data62Login = data62Login;
        this.loginUser = loginUser;
        this.group_id = group_id;
        this.friendAction = friendAction;
        this.redisUtil = redisUtil;
        this.tbUserAccountDao = tbUserAccountDao;
    }

    @Override
    public String call() throws Exception {
        //应对新接口
        if (null == data62User) {
            data62User = new Data62User();
            data62User.setUserName(loginUser.getAccount());
            data62User.setPassword(loginUser.getPwd());
            data62User.setData62(loginUser.getA16_data62());
        }
        String a16LoginInfo = data62Login.data62Login(data62User);
        System.out.println(data62User);
        System.out.println(a16LoginInfo);
        JSONObject data = JSON.parseObject(JSON.parseObject(a16LoginInfo).get("Data").toString());

        Jedis jedis = redisUtil.getJedis();

        try {
            JSONObject baseResponse = JSON.parseObject(data.get("baseResponse").toString());
            JSONObject accountInfo = JSON.parseObject(data.get("accountInfo").toString());
            String wxId = accountInfo.get("wxid").toString();

            Object errMsg = baseResponse.get("errMsg");
            System.out.println(errMsg);

            ArrayList<String> friendList = friendAction.getFriendList(new GetFriendListInfo(wxId,0,0));

            //默认一个号登录时候它的所有好友都是一手的
            for (String friendWxid : friendList) {
                jedis.sadd("friendList:" + wxId + ":first",friendWxid);
            }
            jedis.sadd("login:"+group_id+":wxid",wxId);//根据分组分类
            jedis.sadd("loginList",wxId);//登录后会添加到 set 集合里

            //微信id记录到数据库
            tbUserAccountDao.updateWxidAndAccountFriendCountByAccount(wxId,friendList.size(),data62User.getUserName());
            TbUserAccountEntity responseUser = new TbUserAccountEntity(
                    data62User.getUserName(), data62User.getPassword(), data62User.getData62(),
                    true, true, friendList.size(),
                    wxId, 0,
                    "tag_name", "group_name", 0, 0
            );
            return JSON.toJSONString(responseUser);
        }catch (Exception e){
            e.printStackTrace();
            TbUserAccountEntity responseUser = new TbUserAccountEntity(
                    data62User.getUserName(), data62User.getPassword(), data62User.getData62(),
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

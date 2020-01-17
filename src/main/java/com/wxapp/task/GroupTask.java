package com.wxapp.task;


import com.alibaba.fastjson.JSON;
import com.wxapp.api.group.GroupApi;
import com.wxapp.entity.bean.AddGroupMember;
import com.wxapp.entity.bean.AddGroupUseUrl;
import com.wxapp.service.AccountService;
import com.wxapp.util.RedisUtil;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.Callable;

public class GroupTask implements Callable<String> {

    private GroupApi groupApi;
    private AccountService accountService;
    private RedisUtil redisUtil;
    private String url;

    public GroupTask(GroupApi groupApi, AccountService accountService, RedisUtil redisUtil, String url) {
        this.groupApi = groupApi;
        this.accountService = accountService;
        this.redisUtil = redisUtil;
        this.url = url;
    }

    @Override
    public String call() throws Exception {
        Jedis jedis = redisUtil.getJedis();
        try {
            Set<String> allWxids = jedis.smembers("allWxids");
            //加群算法
            String scanInfoGroupResult = groupApi.scanIntoGroup(new AddGroupUseUrl(url, "wxId"));
            String chatRoomName = JSON.parseObject(scanInfoGroupResult).get("Data").toString();
            String addGroupMemberResult = groupApi.addGroupMember(new AddGroupMember(chatRoomName, new ArrayList<>(), "wxId"));
            return addGroupMemberResult;
        }catch (Exception e){
            return "error";
        }finally {
            jedis.close();
        }
    }
}

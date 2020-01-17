package com.wxapp.task;

import com.alibaba.fastjson.JSON;
import com.wxapp.api.login.UnLoginApi;
import com.wxapp.util.RedisUtil;
import org.springframework.data.redis.connection.jedis.JedisVersionUtil;
import redis.clients.jedis.Jedis;

import java.util.concurrent.Callable;

public class UnLoginTask implements Callable<String> {
    private RedisUtil redisUtil;
    private UnLoginApi unLoginApi;
    private String wxid;
    private String groupId;

    public UnLoginTask(RedisUtil redisUtil, UnLoginApi unLoginApi, String wxid, String groupId) {
        this.redisUtil = redisUtil;
        this.unLoginApi = unLoginApi;
        this.wxid = wxid;
        this.groupId = groupId;
    }

    @Override
    public String call() throws Exception {
        Jedis jedis = redisUtil.getJedis();
        try {
            String unLoginResult = unLoginApi.unLogin(wxid);
            String isSuccess = JSON.parseObject(unLoginResult).get("Success").toString();
            System.out.println(isSuccess);
            jedis.srem("login:"+groupId+":wxid",wxid);
            jedis.srem("allWxids",wxid);
            return isSuccess+wxid;
        }catch (Exception e){
            return "false"+wxid;
        }finally {
            jedis.close();
        }
    }
}

package com.wxapp.service.impl;

import com.alibaba.fastjson.JSON;
import com.wxapp.dao.TbSysUserDao;
import com.wxapp.entity.A16User;
import com.wxapp.entity.Data62User;
import com.wxapp.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;

public class LoginServiceImpl implements LoginService {

    @Autowired
    Jedis jedis;//自动注入 redis
    @Autowired
    TbSysUserDao tbSysUserDao;//自动注入对应表的操作


    @Override
    public String a16login(String returnStr, A16User a16User) {

        return null;
    }

    @Override
    public String data62login(String returnStr, Data62User data62User) {
        //JSON.parseArray(returnStr,)
        return null;
    }
}

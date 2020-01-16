package com.wxapp.service.impl;

import com.wxapp.dao.TbUserAccountDao;
import com.wxapp.dbbean.TbUserAccountEntity;
import com.wxapp.service.LoginService;
import com.wxapp.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;

import java.util.List;


public class LoginServiceImpl implements LoginService {

    @Autowired
    RedisUtil redisUtil;//自动注入 redis
    @Autowired
    TbUserAccountDao tbUserAccountDao;

    @Override
    public String login(List<TbUserAccountEntity> successList) {
        tbUserAccountDao.saveAll(successList);//批处理还没有配置
        return null;
    }
}

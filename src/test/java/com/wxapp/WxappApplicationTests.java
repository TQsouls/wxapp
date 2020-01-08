package com.wxapp;

import com.wxapp.dao.TbSysUserDao;
import com.wxapp.util.RedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.ExecutorService;

@SpringBootTest
class WxappApplicationTests {

    @Autowired
    RedisUtil redisUtil;

    @Autowired
    ExecutorService executor;

    @Autowired
    TbSysUserDao tbSysUserDao;

    @Value("${the-most-thread-numbers}")
    private int theMostThreadNum;

    @Test
    void contextLoads() {
        System.out.println(tbSysUserDao.getAll());
    }

}

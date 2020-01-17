package com.wxapp.service.impl;

import com.wxapp.dao.TbUserAccountDao;
import com.wxapp.dbbean.TbUserAccountEntity;
import com.wxapp.requestentity.other.LoginUser;
import com.wxapp.service.LoginService;
import com.wxapp.util.RedisUtil;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
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

    //根据组的id获得用户列表
    @Override
    public List<LoginUser> getAllByGroupId(Integer groupId) {
        List<LoginUser> loginUserList = new ArrayList<>();
        List<TbUserAccountEntity> allByGroupId = tbUserAccountDao.getAllByGroupId(groupId);
        for (TbUserAccountEntity tbUserAccountEntity : allByGroupId) {
            loginUserList.add(new LoginUser(tbUserAccountEntity.getAccount(),tbUserAccountEntity.getAccountPwd(),tbUserAccountEntity.getAccount62A16()));
        }
        return loginUserList;
    }

    @Override
    public List<String> getLoginWxidsByGroupId(Integer groupId) {
        Jedis jedis = redisUtil.getJedis();
        try {
            Set<String> smembers = jedis.smembers("login:" + groupId + ":wxid");
            return new ArrayList<String>(smembers);
        }catch (Exception e){
            return null;
        }finally {
            jedis.close();
        }
    }
}

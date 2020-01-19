package com.wxapp.service.impl;

import com.alibaba.fastjson.JSON;
import com.wxapp.dao.TbUserAccountDao;
import com.wxapp.entity.addgroup.OneUrl;
import com.wxapp.service.GroupService;
import com.wxapp.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;

@Service
public class GroupServiceImpl implements GroupService {


    @Autowired
    RedisUtil redisUtil;

    @Autowired
    TbUserAccountDao tbUserAccountDao;
    /**
     * 好友分配算法，假设刚刚登录时所有的号都在一个池子里，称为一手号池
     * 然后在这个方法里进行分配，若其所有好友均被分配完成，则将其移出一手号池，加入二手号池里
     * 通过键 fristWxids 来获取一手列表
     * 通过键 secondWxids 来获取二手号列表
     * 通过键 wxid 来获取这个号的好友量
     */
    private int i = 0;
    private int num = 20;//添加的好友量
    int currentIndex = 0;//开始时的下标
    @Override
    public List<OneUrl> distribution(int tagId,int opType) {
        Jedis jedis = redisUtil.getJedis();
        //获取群 url 列表
        List<String> urlList = JSON.parseObject(jedis.get("currentUrl"),List.class);
        //构造返回列表
        List<OneUrl> oneUrlList = new ArrayList<>();
        for (String url : urlList) {
            oneUrlList.add(new OneUrl(url));
        }
        try {
            List<String> allByTagId = tbUserAccountDao.getAccountWxidByTagId(tagId);//根据tagId获取一个子号池

            List<String> mainList = null;
            List<String> tempList = null;
            for (OneUrl oneUrl : oneUrlList) {
                String wxid = allByTagId.get(i);
                //根据类型获取好友列表
                if (null == mainList) {
                    if (opType == 0) {
                        //获取一手好友列表
                        mainList = new ArrayList<>(jedis.smembers("friendList:" + wxid + ":first"));
                        for (String toSecond : mainList) {
                            //一手转二手
                            jedis.srem("friendList:" + wxid + ":first", toSecond);
                            jedis.sadd("friendList:" + wxid + ":second", toSecond);
                        }
                    } else {
                        //获取二手好友列表
                        mainList = new ArrayList<>(jedis.smembers("friendList:" + wxid + ":second"));
                    }
                }
                //好友数量就是列表大小
                int friendCount = mainList.size();

                //总好友量-当前下标+1 大于 要拉取的好友量时，直接添加
                if ((friendCount-(currentIndex+1)) > num){
                    //当前下标加 num-1
                    currentIndex+=(num-1);
                    oneUrl.wxIds.add(wxid);
                    tempList = mainList.subList(currentIndex-(num-1),currentIndex);//添加num个好友
                    oneUrl.friendList.put(wxid,tempList);
                    oneUrl.currentCount += num;
                }else {
                    //如果好友量不够，就把当前所有的好友添加进去
                    oneUrl = doMath(oneUrl,mainList,jedis,wxid,allByTagId,opType);
                    mainList = null;
                }
                System.out.println(i);
            }
            return oneUrlList;
        }catch (Exception e){
            e.printStackTrace();
            return oneUrlList;
        }finally {
            jedis.close();
            i = 0;
            currentIndex = 0;
        }
    }



    public OneUrl doMath(OneUrl oneUrl,List<String> currentList,Jedis jedis,String wxid,List<String> allByTagId,int opType) {

        if (null == currentList) {
            //当前下标加 20
            currentIndex += (num - oneUrl.currentCount);
            if (opType == 0) {
                //获取一手好友列表
                currentList = new ArrayList<>(jedis.smembers("friendList:" + wxid + ":first"));
                for (String toSecond : currentList) {
                    //一手转二手
                    jedis.srem("friendList:" + wxid + ":first",toSecond);
                    jedis.sadd("friendList:" + wxid + ":second",toSecond);
                }
            }else {
                //获取二手好友列表
                currentList = new ArrayList<>(jedis.smembers("friendList:" + wxid + ":second"));
            }
            try {
                currentList = currentList.subList(currentIndex - (num - oneUrl.currentCount), currentIndex);
            }catch (Exception e){
                System.out.println(currentList.size());
                e.printStackTrace();
            }

            oneUrl.wxIds.add(wxid);
            oneUrl.friendList.put(wxid, currentList);
            oneUrl.currentCount += (currentIndex+1);

        } else {
            oneUrl.currentCount += (currentList.size()-currentIndex-1);
            oneUrl.wxIds.add(wxid);
            try {
                oneUrl.friendList.put(wxid, currentList.subList(currentIndex,currentList.size()-1));
            }catch (Exception e){
                System.out.println("当前列表长度是："+currentList.size()+" 当前下标是："+currentIndex);
                e.printStackTrace();
            }
            currentIndex = 0;
            i+=1;
            wxid = allByTagId.get(i);
            //从缓存里拿好友数
            while (oneUrl.currentCount < num) {
                oneUrl = doMath(oneUrl, null,jedis, wxid, allByTagId,opType);
            }
        }
        return oneUrl;
    }

    //提交微信群 url 到缓存
    @Override
    public boolean submitUrl(List<String> grpUrl) {
        Jedis jedis = redisUtil.getJedis();
        try {
            jedis.set("currentUrl",JSON.toJSONString(grpUrl));
            return true;
        }catch (Exception e){
            return false;
        }finally {
            jedis.close();
        }
    }
}

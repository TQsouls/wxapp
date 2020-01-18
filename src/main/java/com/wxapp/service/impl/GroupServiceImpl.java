package com.wxapp.service.impl;

import com.alibaba.fastjson.JSON;
import com.wxapp.entity.addgroup.OneUrl;
import com.wxapp.service.GroupService;
import com.wxapp.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class GroupServiceImpl implements GroupService {


    @Autowired
    RedisUtil redisUtil;
    /**
     * 好友分配算法，假设刚刚登录时所有的号都在一个池子里，称为一手号池
     * 然后在这个方法里进行分配，若其所有好友均被分配完成，则将其移出一手号池，加入二手号池里
     * 通过键 fristWxids 来获取一手列表
     * 通过键 secondWxids 来获取二手号列表
     * 通过键 wxid 来获取这个号的好友量
     * @param urlList
     * @return
     */
    private int i = 0;
    private int num = 15;//添加的好友量
    @Override
    public List<OneUrl> distribution(List<String> urlList) {

        //构造返回列表
        List<OneUrl> oneUrlList = new ArrayList<>();
        for (String url : urlList) {
            oneUrlList.add(new OneUrl(url));
        }
        Jedis jedis = redisUtil.getJedis();
        try {
            //获取一手好友的操作
            Set<String> fristWxids = jedis.smembers("fristWxids");
            ArrayList<String> fristWxidList = new ArrayList<>(fristWxids);
            //获取好友列表的开始下标
            int currentIndex = 0;
            for (OneUrl oneUrl : oneUrlList) {
                String wxid = fristWxidList.get(i);
                int friendCount = Integer.valueOf(jedis.get(wxid));

                if ((friendCount-(currentIndex+1)) > num){
                    //当前下标加 num-1
                    currentIndex+=(num-1);
                    List<String> currentList = JSON.parseObject(jedis.get("friendList:" + wxid),List.class);
                    oneUrl.wxIds.add(wxid);
                    currentList = currentList.subList(currentIndex-(num-1),currentIndex);//添加num个好友
                    oneUrl.friendList.put(wxid,currentList);
                    oneUrl.currentCount += num;
                }else {
                    //如果好友量不够，就把当前所有的好友添加进去
                    oneUrl = doMath(oneUrl,friendCount,currentIndex,jedis,wxid,fristWxidList);
                }
            }
            return oneUrlList;
        }catch (Exception e){
            e.printStackTrace();
            return oneUrlList;
        }finally {
            jedis.close();
        }
    }

    public OneUrl doMath(OneUrl oneUrl,int friendCount,int currentIndex,Jedis jedis,String wxid,ArrayList<String> fristWxidList) {

        if ((friendCount - currentIndex) > (num - oneUrl.currentCount)) {
            //当前下标加 20
            currentIndex += (num - oneUrl.currentCount);
            List<String> currentList = JSON.parseObject(jedis.get("friendList:" + wxid), List.class);
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
            //如果好友量不够，就把当前所有的好友添加进去
            List<String> currentList = JSON.parseObject(jedis.get("friendList:" + wxid), List.class);
            oneUrl.currentCount += friendCount;
            oneUrl.wxIds.add(wxid);
            oneUrl.friendList.put(wxid, currentList);

            currentIndex = 0;
            wxid = fristWxidList.get(++i);
            friendCount = Integer.valueOf(jedis.get(wxid));
            Set<String> fristWxids = jedis.smembers("fristWxids");
            fristWxidList = new ArrayList<>(fristWxids);
            while (oneUrl.currentCount < num) {
                oneUrl = doMath(oneUrl, friendCount, currentIndex, jedis, wxid, fristWxidList);
            }
        }
        return oneUrl;
    }
}

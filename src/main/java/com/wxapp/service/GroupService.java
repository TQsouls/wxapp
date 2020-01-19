package com.wxapp.service;

import com.wxapp.entity.addgroup.OneUrl;

import java.util.List;

//有关加群逻辑的操作
public interface GroupService {

    //根据群聊分配微信id
    List<OneUrl> distribution(int tagId,int opType);
    boolean submitUrl(List<String> grpUrl);
}

package com.wxapp.api.group;


import com.alibaba.fastjson.JSON;
import com.wxapp.entity.bean.AddGroupMember;
import com.wxapp.entity.bean.AddGroupUseUrl;
import com.wxapp.util.HttpclientUtil;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//拉群加群等操作
@Component
public class GroupApi {
    //根据 url 加群
    public String scanIntoGroup(AddGroupUseUrl addGroupUseUrl) {
        String url = "http://47.110.75.232:8080/api/Group/ScanIntoGroup";
        String addGroupResult = HttpclientUtil.doJSONPost(url, JSON.toJSONString(addGroupUseUrl));
        System.out.println(addGroupResult);
        return addGroupResult;
    }

    //拉40人小群
    public String addGroupMember(AddGroupMember addGroupMember){
        String url = "http://47.110.75.232:8080/api/Group/AddGroupMember";
        String returnMsg = HttpclientUtil.doJSONPost(url,JSON.toJSONString(addGroupMember));
        System.out.println(returnMsg);
        return returnMsg;
    }
}

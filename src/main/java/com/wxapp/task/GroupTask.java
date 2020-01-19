package com.wxapp.task;


import com.alibaba.fastjson.JSON;
import com.wxapp.api.group.GroupApi;
import com.wxapp.entity.addgroup.OneUrl;
import com.wxapp.entity.bean.AddGroupMember;
import com.wxapp.entity.bean.AddGroupUseUrl;

import java.util.concurrent.Callable;

public class GroupTask implements Callable<String> {

    private GroupApi groupApi;
    private OneUrl oneUrl;

    public GroupTask(GroupApi groupApi, OneUrl oneUrl) {
        this.groupApi = groupApi;
        this.oneUrl = oneUrl;
    }

    @Override
    public String call() throws Exception {
        for (String wxId : oneUrl.wxIds) {
            System.out.println(oneUrl);
            String result = groupApi.scanIntoGroup(new AddGroupUseUrl(oneUrl.getUrl(), wxId));
            String chatRoomName = JSON.parseObject(result).get("Data").toString();
            String groupName = groupApi.addGroupMember(new AddGroupMember(chatRoomName,oneUrl.friendList.get(wxId),wxId));
        }
        return null;
    }
}

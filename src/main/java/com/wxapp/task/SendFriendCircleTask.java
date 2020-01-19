package com.wxapp.task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Callable;

import com.alibaba.fastjson.JSON;
import com.wxapp.api.friendcycle.FriendCircleApi;
import com.wxapp.dbbean.TbUserAccountEntity;
import com.wxapp.entity.FriendCircle;
import com.wxapp.entity.bean.*;
//
public class SendFriendCircleTask implements Callable<HashMap<String, String>> {

    // 用户账号信息
	private TbUserAccountEntity user;
	// 已经上传的图片
	private List<String> passUrls;
	// 标题
	private String title;
	// 内容
    private String content;
    


	public SendFriendCircleTask(){};
	public SendFriendCircleTask(
					 TbUserAccountEntity user, 
					 String title, 
					 String content) {

		this.user = user;
		this.title = title;
		this.content = content;
	}
	public SendFriendCircleTask(
					 TbUserAccountEntity user, 
					 List<String> passUrls, 
					 String title, 
					 String content) {

		this.user = user;
		this.passUrls = passUrls;
		this.title = title;
		this.content = content;
	}
   @Override
   public HashMap<String, String> call() throws Exception {
        FriendCircleApi friendCicleApi = new FriendCircleApi();
        FriendCircle friendCircle = new FriendCircle();

        friendCircle.setTitle(title);
        friendCircle.setContent(content);
        // 文本朋友圈类型
        if(this.passUrls == null) {
            friendCircle.setType(0);
        } 
        // 图片朋友圈类型
        else {
            friendCircle.setType(1);
        }
        String wxid = user.getAccountWxid();
        friendCircle.setWxId(wxid);
        List<MediaInfo> mediaInfos = new ArrayList<>();
        for (String url : passUrls) {
            MediaInfo mediaInfo = new MediaInfo();
            mediaInfo.setUrl(url);
            mediaInfo.setImageUrl(url);
            mediaInfos.add(mediaInfo);
        }
        friendCircle.setMediaInfos(mediaInfos);

        // 发送请求
        String result = friendCicleApi.sendFriendCircle(friendCircle);
        if((JSON.parseObject(result).get("Success")+"").equals("true")) {
            HashMap<String, String> successMap = new HashMap<String, String>();
            successMap.put("success", user.getAccount());
            return successMap;
        } else {
            HashMap<String, String> errorMap = new HashMap<String, String>();
            errorMap.put("error", user.getAccount());
            return errorMap;
        }
   }
}

/**
 * 
 */
package com.wxapp.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;

import com.wxapp.api.friendcycle.FriendCircleApi;
import com.wxapp.dbbean.TbAddcontactfriendEntity;
import com.wxapp.dbbean.TbSendcircleEntity;
import com.wxapp.dbbean.TbSendmsgEntity;
import com.wxapp.dbbean.TbTomodifyprofileEntity;
import com.wxapp.dbbean.TbUserAccountEntity;
import com.wxapp.service.MarketingService;
import com.wxapp.task.SendFriendCircleTask;
import com.wxapp.util.PicTransUploadUtils;
import com.wxapp.util.Result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 营销管理类接口控制器
 *   - 设置添加通讯录好友验证消息 					  已完成
 *   - 设置个人信息素材（密码，昵称，个签，头像）	   已完成
 *   - 设置发送消息素材								已完成
 *   - 设置朋友圈素材								已完成
 *   - 发朋友圈（通过组）							已完成
 *   
 * @author 马鑫
 * @datetime 2020年1月13日 下午7:03:57
 */

@RestController
@RequestMapping("/api/marketing")
public class MarketingController {

	@Autowired
	MarketingService marketingService;

	
	
	/**
	 * 设置添加通讯录好友验证消息
	 * @param content
	 * @param user_id
	 */
	@PostMapping("setAddMsg")
	public Result setAddMsg(@RequestBody HashMap<String, Object> map) {
		
		TbAddcontactfriendEntity tae = new TbAddcontactfriendEntity();
		tae.setAcfContent(map.get("content").toString());
		tae.setUserId(Integer.parseInt(map.get("user_id").toString()));
		
		int result = marketingService.save(tae);
		if(result <= 0) {
			return Result.ok(-1, "添加失败");
		}
		return Result.ok(0, "添加成功");
	}
	
	/**
	 * 设置个人信息素材（密码，昵称，个签，头像）
	 * @param pwd
	 * @param nickname
	 * @param signature
	 * @param avatarUrl
	 * @param user_id
	 * @return
	 */
	@PostMapping("setInfo")
	public Result setInfo(@RequestBody HashMap<String, Object> map) {
		
		
		String pwd = map.get("pwd").toString();
		String nickname = map.get("nickname").toString();
		String signature = map.get("signature").toString();
		String avatarUrl = map.get("avatarUrl").toString();
		Integer user_id = Integer.parseInt(map.get("user_id").toString());

		// 上传图片
		String uploadResult = PicTransUploadUtils.transAndUpload(avatarUrl);
		if(uploadResult == null || uploadResult.equals("")) {
			return Result.ok(-1, "文件上传失败", null);
		}
		TbTomodifyprofileEntity tte = new TbTomodifyprofileEntity();
		tte.setMpAvatarUrl(uploadResult);
		tte.setUserId(user_id);
		tte.setMpNewPwd(pwd);
		tte.setMpNickname(nickname);
		tte.setMpSignature(signature);
		
		if(marketingService.save(tte) <= 0) {
			return Result.ok(-1, "设置失败，原因未知", null);
		}
		return Result.ok(0, "设置成功", null);
		
	}
	
	/**
	 * 设置发送消息素材
	 * @param content
	 * @param user_id
	 * @return
	 */
	@PostMapping("setSendMsg")
	public Result setSendMsg(@RequestBody HashMap<String, Object> map) {
		
		TbSendmsgEntity tse = new TbSendmsgEntity();
		tse.setSmMsg(map.get("content").toString());
		tse.setUserId(Integer.parseInt(map.get("user_id").toString()));
		
		int result = marketingService.save(tse);
		if(result <= 0) {
			return Result.ok(-1, "添加失败");
		}
		return Result.ok(0, "添加成功");
		
//		return Result.ok(0, "设置成功", null);
	}
	
	/**
	 * 设置朋友圈素材
	 * @param title
	 * @param picURL
	 * @param content
	 * @param user_id
	 * @return
	 */
	@PostMapping("setFriendCricle")
	public Result setFriendCricle(@RequestBody HashMap<String, Object> map) {
		
		String  title   = map.get("title").toString();
		List<String> picUrls = (List)map.get("picURL");
		String  content = map.get("content").toString();
		Integer user_id = Integer.parseInt(map.get("user_id").toString());
		String result = "";
		for (String data64 : picUrls) {
			// 将图片路径以逗号分隔，保存到数据库
			result += "," + PicTransUploadUtils.transAndUpload(data64);
		}

		// 去掉开头的逗号
		if(result.charAt(0) == ',' && result.length() >= 1){
			result = result.substring(1);
		}
		TbSendcircleEntity tse = new TbSendcircleEntity();
		tse.setScContent(content);
		tse.setScTitle(title);
		tse.setUserId(user_id);
		tse.setScPicUrl(result);
		
		if(marketingService.save(tse) >= 1) {
			return Result.ok(0, "设置成功", null);
		}
		return Result.ok(-1, "设置失败，原因未知", null);
	}
	
	/**
	 * 发朋友圈（通过组）
	 * @param group_id
	 * @return
	 */
	@PostMapping("sendFriendCircle")
	public Result sendFriendCircle(@RequestBody HashMap<String, Object> map) {
		
		// 最大线程数
		final int MAX_THREAD = 8;

		Integer group_id = Integer.parseInt(map.get("group_id").toString());

		List<TbUserAccountEntity> userList =  marketingService.findByGroupId(group_id);

		if(userList == null || userList.size() <= 0) {
			return Result.ok(-1, "该组中暂无账号", null);
		}

		// 先保存一个微信ID 用于把图片上传到腾讯服务器，这样才能发送朋友圈。
		String wxid = userList.get(0).getAccountWxid();
		Integer userId = userList.get(0).getUserId();

		List<String> base64s = marketingService.findImageBase64s(userId);
		TbSendcircleEntity tse = marketingService.findByUserId(userId);
		String title = tse.getScTitle();
		String content = tse.getScContent();
		ArrayList<Future<HashMap<String, String>>> result = new ArrayList<>();
		
		ExecutorService executorService = Executors.newFixedThreadPool(MAX_THREAD);
		// 发送文字朋友圈
		if(base64s == null || base64s.size() <= 0) {
			for (TbUserAccountEntity user : userList) {
				Future<HashMap<String, String>> resultMap = executorService.submit(new SendFriendCircleTask(user, title, content));
				result.add(resultMap);
			
			}
		} 
		// 发送图片朋友圈
		else {
			// 将图片上传到腾讯服务器
			List<String> passUrls = new FriendCircleApi().sendFriendCircleImage(base64s, wxid);
			for (TbUserAccountEntity user : userList) {
				Future<HashMap<String, String>> resultMap = executorService.submit(new SendFriendCircleTask(user, passUrls, title, content));
				result.add(resultMap);
			}
		}
		
		HashMap<String, Object> returnData = new HashMap<>();
		List<String> successList = new ArrayList<>();
		List<String> errorList = new ArrayList<>();
		for (Future<HashMap<String, String>> future : result) {
			try {
				if(future.get().containsKey("success")) {
					successList.add(future.get().get("success"));
				}
				if(future.get().containsKey("error")) {
					errorList.add(future.get().get("error"));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		returnData.put("success", successList);
		returnData.put("error", errorList);
		if(successList == null || successList.size() <= 0) {
			return Result.ok(-1, "发送失败", returnData);
		}
		if(errorList == null || errorList.size() <= 0) {
			return Result.ok(0, "发送成功", returnData);
		}
		return Result.ok(1, "有的成功有的失败", returnData);
	}
}
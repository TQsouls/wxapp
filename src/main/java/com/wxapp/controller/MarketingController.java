/**
 * 
 */
package com.wxapp.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wxapp.dbbean.TbAddcontactfriendEntity;
import com.wxapp.service.MarketingService;
import com.wxapp.util.Result;

/**
 * 营销管理类接口控制器
 *   - 设置添加通讯录好友验证消息
 *   - 设置个人信息素材（密码，昵称，个签，头像）
 *   - 设置发送消息素材
 *   - 设置朋友圈素材
 *   - 发朋友圈（通过组）
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
		tae.setAcfId(Integer.parseInt(map.get("user_id").toString()));
		
		int result = marketingService.save(tae);
		if(result <= 0) {
			return Result.ok(-1, "添加失败");
		}
		return Result.ok(0, "添加成功");
	}
	
	/**
	 * 设置个人信息素材（密码，昵称，个签，头像）
	 * @param content
	 * @param user_id
	 * @return
	 */
	@PostMapping("setInfo")
	public Result setInfo(@RequestParam("pwd")       String pwd, 
			              @RequestParam("nickname")  String nickname,
			              @RequestParam("signature") String signature,
			              @RequestParam("avatarUrl") String avatarUrl,
			              @RequestParam("user_id")   String user_id ) {
		
		return Result.ok(0, "设置成功", null);
	}
	
	/**
	 * 设置发送消息素材
	 * @param content
	 * @param user_id
	 * @return
	 */
	@PostMapping("setSendMsg")
	public Result setSendMsg(@RequestParam("content") String content, 
			                @RequestParam("user_id") String user_id) {
		
		return Result.ok(0, "设置成功", null);
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
	public Result setFriendCricle(@RequestParam("title")   String title, 
			                      @RequestParam("picURL")  String picURL,
			                      @RequestParam("content") String content,
			                      @RequestParam("user_id") String user_id ) {
		
		return Result.ok(0, "设置成功", null);
	}
	
	/**
	 * 发朋友圈（通过组）
	 * @param group_id
	 * @return
	 */
	@PostMapping("sendFriendCircle")
	public Result sendFriendCircle(@RequestParam("group_id") String group_id) {
		
		return Result.ok(0, "发送成功", null);
	}
}

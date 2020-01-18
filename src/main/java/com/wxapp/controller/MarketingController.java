/**
 * 
 */
package com.wxapp.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.wxapp.api.friendcycle.FriendCircleApi;
import com.wxapp.dbbean.TbAddcontactfriendEntity;
import com.wxapp.dbbean.TbSendcircleEntity;
import com.wxapp.dbbean.TbSendmsgEntity;
import com.wxapp.dbbean.TbTomodifyprofileEntity;
import com.wxapp.dbbean.TbUserAccountEntity;
import com.wxapp.entity.FriendCircle;
import com.wxapp.service.MarketingService;
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
		
		Integer group_id = Integer.parseInt(map.get("group_id").toString());

		List<TbUserAccountEntity> userList =  marketingService.findByGroupId(group_id);

		if(userList == null || userList.size() <= 0) {
			return Result.ok(-1, "该组中暂无账号", null);
		}

		// 先保存一个微信ID 用于把图片上传到腾讯服务器，这样才能发送朋友圈。
		String wxid = userList.get(0).getAccountWxid();
		Integer userId = userList.get(0).getUserId();

		List<String> base64s = marketingService.findImageBase64s(userId);

		// 发送文字朋友圈
		if(base64s == null || base64s.size() <= 0) {
			
		} 
		// 发送图片朋友圈
		else {
			// 将图片上传到腾讯服务器
			List<String> passUrls = new FriendCircleApi().sendFriendCircleImage(base64s, wxid);
		}
		
		


		return Result.ok(0, "发送成功", null);
	}
}

/**
 * 发送朋友圈多线程类
 */
class SendFriendCircle extends Thread{
	// 用户账号信息列表
	private List<TbUserAccountEntity> userAccounts;
	// 账号量
	private int accountSize;
	// 已经上传的图片
	private List<String> passUrls;
	// 标题
	private String title;
	// 内容
	private String content;

    private CountDownLatch count;//线程计数器
    private HashMap<String, Object> map;//接收结果

	SendFriendCircle(){};
	SendFriendCircle(CountDownLatch count, 
					 HashMap<String, Object> map, 
					 List<TbUserAccountEntity> userAccounts, 
					 String title, 
					 String content) {

		this.userAccounts = userAccounts;
		this.accountSize = userAccounts.size();
		this.title = title;
		this.content = content;
		this.count = count;
		this.map = map;
	}
	SendFriendCircle(CountDownLatch count, 
					 HashMap<String, Object> map, 
					 List<TbUserAccountEntity> userAccounts, 
					 List<String> passUrls, 
					 String title, 
					 String content) {

		this.userAccounts = userAccounts;
		this.accountSize = userAccounts.size();
		this.passUrls = passUrls;
		this.title = title;
		this.content = content;
		this.count = count;
		this.map = map;
	}

	public void run() {
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
		while(true) {
			synchronized(this) {
				if(accountSize > 0) {
					String wxid = userAccounts.get(--accountSize).getAccountWxid();
					friendCircle.setWxId(wxid);
					// String result = friendCicleApi.sendFriendCircle(friendCircle);
					// test
					// 发送成功
					// if((JSON.parseObject(result).get("Success")+"").equals("true")) {
					map.put("success", userAccounts.get(accountSize).getAccount());
					System.out.println("线程" + Thread.currentThread().getName() + ": " + userAccounts.get(accountSize).getAccount());
					// /* 发送失败 */ else {
						// map.put("error", userAccounts.get(accountSize).getAccount());
					// }
					// 减少锁存器的计数，如果计数达到零，释放所有等待的线程。 
					count.countDown();
				}
			}
		}
		
	}

	public static void main(String[] args) {
		//线程池堵塞队列
        ArrayBlockingQueue<Runnable> workQueue = null;
        //线程池队列
        ThreadPoolExecutor pool = null;
        //线程计数器
		CountDownLatch end = null;

		// 用于保存结果
		HashMap<String, Object> map = new HashMap<>();

		// 最大线程数
		final int MAX_THREAD = 8;

		// 账号列表
		List<TbUserAccountEntity> userAccounts = new ArrayList<TbUserAccountEntity>();
		TbUserAccountEntity tua1 = new TbUserAccountEntity();
		TbUserAccountEntity tua2 = new TbUserAccountEntity();
		TbUserAccountEntity tua3 = new TbUserAccountEntity();
		TbUserAccountEntity tua4 = new TbUserAccountEntity();
		TbUserAccountEntity tua5 = new TbUserAccountEntity();
		TbUserAccountEntity tua6 = new TbUserAccountEntity();
		tua1.setAccount("account1");
		tua1.setAccountWxid("wxid1");
		tua2.setAccount("account2");
		tua2.setAccountWxid("wxid2");
		tua3.setAccount("account3");
		tua3.setAccountWxid("wxid3");
		tua4.setAccount("account4");
		tua4.setAccountWxid("wxid4");
		tua5.setAccount("account5");
		tua5.setAccountWxid("wxid5");
		tua6.setAccount("account6");
		tua6.setAccountWxid("wxid6");
		userAccounts.add(tua1);
		userAccounts.add(tua2);
		userAccounts.add(tua3);
		userAccounts.add(tua4);
		userAccounts.add(tua5);
		userAccounts.add(tua6);
		
		try {
			// 开启线程池
			workQueue = new ArrayBlockingQueue<Runnable>(MAX_THREAD);
			/*  corePoolSize - 即使空闲时仍保留在池中的线程数，除非设置 allowCoreThreadTimeOut 
				maximumPoolSize - 池中允许的最大线程数 
				keepAliveTime - 当线程数大于核心时，这是多余的空闲线程在终止之前等待新任务的最大时间。 
				unit - keepAliveTime参数的时间单位 
				workQueue - 在执行任务之前用于保存任务的队列。 该队列将仅保存execute方法提交的Runnable任务。 
			*/
			pool = new ThreadPoolExecutor(0, MAX_THREAD, 200, TimeUnit.MILLISECONDS, workQueue);
			//创建线程计数器
			end = new CountDownLatch(MAX_THREAD);
			
			for(int i = 0; i <= MAX_THREAD; i++){
				//创建线程
				Thread sfc = new SendFriendCircle(end, map, userAccounts, "title-mx", "content-mx");
				// Thread rt = new sendFriendCircle(i,end,reList);
				//执行线程任务
				pool.execute(sfc);
			} 
			//当所有线程执行完毕后才继续执行后续代码
			end.await();
			Object o = map.get("success");
			// for (Object item : o) {
				System.out.println("+++++:" + o.toString());
			// }
		} catch(InterruptedException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

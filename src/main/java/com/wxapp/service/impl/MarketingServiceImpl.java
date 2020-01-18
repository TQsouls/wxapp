/**
 * 
 */
package com.wxapp.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.wxapp.dao.TbAddcontactfriendDao;
import com.wxapp.dao.TbSendcircleDao;
import com.wxapp.dao.TbSendmsgDao;
import com.wxapp.dao.TbTomodifyprofileDao;
import com.wxapp.dao.TbUserAccountDao;
import com.wxapp.dbbean.TbAddcontactfriendEntity;
import com.wxapp.dbbean.TbSendcircleEntity;
import com.wxapp.dbbean.TbSendmsgEntity;
import com.wxapp.dbbean.TbTomodifyprofileEntity;
import com.wxapp.dbbean.TbUserAccountEntity;
import com.wxapp.service.MarketingService;
import com.wxapp.util.Base64Util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author maxin
 * @datetime 2020年1月13日 下午10:52:19
 */
@Service
public class MarketingServiceImpl implements MarketingService {

	@Autowired
	TbAddcontactfriendDao addcontactfriendDao;
	@Autowired
	TbSendcircleDao sendcircleDao;
	@Autowired
	TbSendmsgDao sendmsgDao;
	@Autowired
	TbTomodifyprofileDao tomodifyprofileDao;
	@Autowired
	TbUserAccountDao userAccountDao;
	
	@SuppressWarnings("unchecked")
	@Override
	public <T> int save(T t) {
		String className = t.getClass().toString();
		T obj = null;

		    // 通讯录好友验证消息
		if(className.equals(TbAddcontactfriendEntity.class + "")) {
			obj = (T) addcontactfriendDao.save((TbAddcontactfriendEntity)t);

			// 朋友圈素材
		} else if(className.equals(TbSendcircleEntity.class + "")) {
			obj = (T) sendcircleDao.save((TbSendcircleEntity)t);

			// 发送消息素材
		} else if(className.equals(TbSendmsgEntity.class + "")) {
			obj = (T) sendmsgDao.save((TbSendmsgEntity)t);

			// 个人信息素材
		} else if(className.equals(TbTomodifyprofileEntity.class + "")) {
			obj = (T) tomodifyprofileDao.save((TbTomodifyprofileEntity)t);
		} else {
			return 0;
		}
		
		if(obj == null) {
			return 0;
		} else {
			return 1;
		}
	}
	

	public List<TbUserAccountEntity> findByGroupId(Integer groupId) {

		return userAccountDao.findByGroupId(groupId);

	}

	public List<String> findImageBase64s(int userId) {

		List<String> result = new ArrayList<>();

		TbSendcircleEntity sendcircleEntity = sendcircleDao.findByUserId(userId);
		if(sendcircleEntity == null || sendcircleEntity.getScPicUrl().equals("")) {
			return null;
		}
		String imagePaths = sendcircleEntity.getScPicUrl();
		if(imagePaths.contains(",")) {
			String [] split = imagePaths.split(",");
			for (String path : split) {
				// 将给定路径的图片转换成字节数组
				byte [] img = Base64Util.imageTobyte(path);
				// 将图片转换成Base64数据
				String base64 = Base64Util.encode(img);
				result.add(base64);
			}
		} else {
			// 将给定路径的图片转换成字节数组
			byte [] img = Base64Util.imageTobyte(imagePaths);
			// 将图片转换成data64数据
			String data64 = Base64Util.encode(img);
			result.add(data64);
		}
		return result;

	}
		
	public TbSendcircleEntity findByUserId(int userId) {
		return sendcircleDao.findByUserId(userId);
	}
}

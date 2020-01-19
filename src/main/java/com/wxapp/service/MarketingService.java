/**
 * 
 */
package com.wxapp.service;

import java.util.List;

import com.wxapp.dbbean.TbSendcircleEntity;
import com.wxapp.dbbean.TbUserAccountEntity;

/**
 * @author maxin
 * @datetime 2020年1月13日 下午10:51:35
 */
public interface MarketingService {
//	int save(TbAddcontactfriendEntity addcontactfriend);
//	int save(TbSendmsgEntity sendmsgEntity);
	
	<T> int save(T t);

	List<TbUserAccountEntity> findByGroupId(Integer groupId);

	// 返回朋友圈素材库里的图片的base64数据
	List<String> findImageBase64s(int userId);

	// 返回朋友圈素材库的标题和内容
	TbSendcircleEntity findByUserId(int userId);
}

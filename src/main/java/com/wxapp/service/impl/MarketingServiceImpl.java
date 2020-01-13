/**
 * 
 */
package com.wxapp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wxapp.dao.TbAddcontactfriendDao;
import com.wxapp.dbbean.TbAddcontactfriendEntity;
import com.wxapp.service.MarketingService;

/**
 * @author maxin
 * @datetime 2020年1月13日 下午10:52:19
 */
@Service
public class MarketingServiceImpl implements MarketingService {

	@Autowired
	TbAddcontactfriendDao addcontactfriendDao;
	
	@Override
	public int save(TbAddcontactfriendEntity addcontactfriend) {

		TbAddcontactfriendEntity tae = addcontactfriendDao.save(addcontactfriend);
		if(tae == null) {
			return 0;
		} else {
			return 1;
		}
	}
	
}

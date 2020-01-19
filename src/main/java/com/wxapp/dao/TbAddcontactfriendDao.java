package com.wxapp.dao;

import com.wxapp.dbbean.TbAddcontactfriendEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TbAddcontactfriendDao extends JpaRepository<TbAddcontactfriendEntity,Integer> {
	
	/**
	 * 添加
	 */
	TbAddcontactfriendEntity save(TbAddcontactfriendEntity addcontactfriend);
}

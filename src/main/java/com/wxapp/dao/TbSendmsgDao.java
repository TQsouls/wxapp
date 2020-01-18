package com.wxapp.dao;

import com.wxapp.dbbean.TbSendmsgEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TbSendmsgDao extends JpaRepository<TbSendmsgEntity,Integer> {
	TbSendmsgEntity save(TbSendmsgEntity sendmsgEntity);
}

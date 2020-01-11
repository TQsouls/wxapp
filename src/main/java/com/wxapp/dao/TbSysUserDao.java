package com.wxapp.dao;

import com.wxapp.dbbean.TbSysUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TbSysUserDao extends JpaRepository<TbSysUserEntity,Integer> {
}

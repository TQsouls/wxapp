package com.wxapp.dao;

import com.wxapp.dbbean.TbSysSetupEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface TbSysSetupDao extends JpaRepository<TbSysSetupEntity,Integer> {
}

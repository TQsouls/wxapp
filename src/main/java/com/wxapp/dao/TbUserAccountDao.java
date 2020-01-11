package com.wxapp.dao;

import com.wxapp.dbbean.TbUserAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TbUserAccountDao extends JpaRepository<TbUserAccountEntity,String> {
}

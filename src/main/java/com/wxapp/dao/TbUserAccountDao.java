package com.wxapp.dao;

import com.wxapp.dbbean.TbUserAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TbUserAccountDao extends JpaRepository<TbUserAccountEntity,String> {
    TbUserAccountEntity deleteByAccountWxid(String accountWxid);
    List<TbUserAccountEntity> getAllByGroupId(Integer groupId);
}

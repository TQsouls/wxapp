package com.wxapp.dao;

import java.util.List;

import com.wxapp.dbbean.TbUserAccountEntity;

import org.springframework.data.jpa.repository.JpaRepository;


public interface TbUserAccountDao extends JpaRepository<TbUserAccountEntity,String> {
    List<TbUserAccountEntity> findByGroupId(Integer groupId);
    TbUserAccountEntity deleteByAccountWxid(String accountWxid);
    List<TbUserAccountEntity> getAllByGroupId(Integer groupId);
}

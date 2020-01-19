package com.wxapp.dao;

import java.util.List;

import com.wxapp.dbbean.TbUserAccountEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;


public interface TbUserAccountDao extends JpaRepository<TbUserAccountEntity,String> {
    List<TbUserAccountEntity> findByGroupId(Integer groupId);
    TbUserAccountEntity deleteByAccountWxid(String accountWxid);
    List<TbUserAccountEntity> getAllByGroupId(Integer groupId);

    @Query("select tuae.accountWxid from TbUserAccountEntity tuae where tuae.tagId=?1")
    List<String> getAccountWxidByTagId(Integer tagId);

    @Modifying
    @Transactional
    @Query("update TbUserAccountEntity tuse set tuse.accountWxid=?1,tuse.accountFriendCount=?2 where tuse.account=?3")
    void updateWxidAndAccountFriendCountByAccount(String wxId,Integer accountFriendCount,String account);
}

package com.wxapp.service;

import com.wxapp.dbbean.TbUserAccountEntity;

import java.util.List;

public interface AccountService {
    String saveOneAccount(TbUserAccountEntity tbUserAccountEntity);
    String saveAllAccount(List<TbUserAccountEntity> tbUserAccountEntityList);
}

package com.wxapp.service.impl;

import com.wxapp.dao.TbUserAccountDao;
import com.wxapp.dbbean.TbUserAccountEntity;
import com.wxapp.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    TbUserAccountDao tbUserAccountDao;

    @Override
    public String saveOneAccount(TbUserAccountEntity tbUserAccountEntity) {
        TbUserAccountEntity save = tbUserAccountDao.save(tbUserAccountEntity);
        return "success";
    }

    @Override
    public String saveAllAccount(List<TbUserAccountEntity> tbUserAccountEntityList) {
        List<TbUserAccountEntity> tbUserAccountEntities = tbUserAccountDao.saveAll(tbUserAccountEntityList);
        return "success";
    }
}

package com.wxapp.service;

import com.wxapp.dbbean.TbUserAccountEntity;

import java.util.List;

/**
 * 这里写登录业务（每个微信账号的登录都会执行的业务）
 * 登录后先记录账号
 * 查询好友列表并存储到缓存
 */
//+--------------+-------------+------+-----+---------+----------------+
//        | Field        | Type        | Null | Key | Default | Extra          |
//        +--------------+-------------+------+-----+---------+----------------+
//        | user_id      | int(64)     | NO   | PRI | NULL    | auto_increment |
//        | user_account | varchar(20) | NO   |     | NULL    |                |
//        | user_pwd     | varchar(20) | NO   |     | NULL    |                |
//        | user_cdkey   | varchar(50) | YES  |     | NULL    |                |
//        | user_role    | bit(64)     | NO   |     | NULL    |                |
//        | user_name    | varchar(20) | NO   |     | NULL    |                |
//        | user_isValid | bit(64)     | NO   |     | b'1'    |                |
//        +--------------+-------------+------+-----+---------+----------------+
public interface LoginService {

    String login(List<TbUserAccountEntity> successList);

}

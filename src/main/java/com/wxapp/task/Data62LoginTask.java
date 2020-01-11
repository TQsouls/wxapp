package com.wxapp.task;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wxapp.api.login.Data62Login;
import com.wxapp.dbbean.TbUserAccountEntity;
import com.wxapp.entity.Data62User;
import com.wxapp.requestentity.other.LoginUser;

import java.util.concurrent.Callable;

public class Data62LoginTask implements Callable<String> {

    private Data62Login data62Login;
    private Data62User data62User = null;


    private String group_id;
    private LoginUser loginUser;

    public Data62LoginTask(Data62Login data62Login, Data62User data62User) {
        this.data62Login = data62Login;
        this.data62User = data62User;
    }

    public Data62LoginTask(Data62Login data62Login, LoginUser loginUser,String group_id) {
        this.data62Login = data62Login;
        this.loginUser = loginUser;
        this.group_id = group_id;
    }

    @Override
    public String call() throws Exception {
        //应对新接口
        if (null == data62User) {
            data62User = new Data62User();
            data62User.setUserName(loginUser.getAccount());
            data62User.setPassword(loginUser.getPwd());
            data62User.setData62(loginUser.getA16_data62());
        }
        String a16LoginInfo = data62Login.data62Login(data62User);
        System.out.println(data62User);
        System.out.println(a16LoginInfo);
        JSONObject data = JSON.parseObject(JSON.parseObject(a16LoginInfo).get("Data").toString());

        try {
            JSONObject baseResponse = JSON.parseObject(data.get("baseResponse").toString());
            JSONObject accountInfo = JSON.parseObject(data.get("accountInfo").toString());
            String wxId = accountInfo.get("wxid").toString();

            Object errMsg = baseResponse.get("errMsg");
            System.out.println(errMsg);

            TbUserAccountEntity responseUser = new TbUserAccountEntity(
                    data62User.getUserName(), data62User.getPassword(), data62User.getData62(),
                    true, true, 0,
                    wxId, "0",
                    "tag_name", "group_name", "groupId", "user_id"
            );
            return JSON.toJSONString(responseUser);
        }catch (Exception e){
            TbUserAccountEntity responseUser = new TbUserAccountEntity(
                    data62User.getUserName(), data62User.getPassword(), data62User.getData62(),
                    true, true, 0,
                    "error", "0",
                    "tag_name", "group_name", "groupId", "user_id"
            );
            return JSON.toJSONString(responseUser);
        }
    }
}

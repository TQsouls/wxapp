package com.wxapp.api.login;

import com.wxapp.util.HttpclientUtil;
import org.springframework.stereotype.Component;

//退出账号
@Component
public class UnLoginApi {

    public String unLogin(String wxId){
        String url = "http://47.110.75.232:8080/api/Login/LogOut/"+wxId;
        String urLoginResult = HttpclientUtil.doPost(url, null);
        return urLoginResult;
    }
}

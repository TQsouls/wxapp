package com.wxapp.api.contrats;

import com.alibaba.fastjson.JSON;
import com.wxapp.entity.Contrat;
import com.wxapp.util.HttpclientUtil;
import org.springframework.stereotype.Component;

@Component
public class UploadContratsApi {
    public String uploadContrats(Contrat contrat){
        String url = "http://47.110.75.232:8080/api/Common/UploadContrats";
        String contratUploadResult = HttpclientUtil.doJSONPost(url, JSON.toJSONString(contrat));
        return contratUploadResult;
    }
}

package com.wxapp.task;

import com.wxapp.api.user.UserOperating;
import com.wxapp.entity.UploadHeadImage;
import com.wxapp.util.ImgUtil;

import java.util.concurrent.Callable;

public class UploadHeadImageTask implements Callable<String> {

    private UploadHeadImage uploadHeadImage;
    private UserOperating userOperating;

    public UploadHeadImageTask(UploadHeadImage uploadHeadImage, UserOperating userOperating) {
        this.uploadHeadImage = uploadHeadImage;
        this.userOperating = userOperating;
    }

    @Override
    public String call() throws Exception {
        String uploadHeadImageResult = userOperating.uploadHeadImage(ImgUtil.
                getBase64ByImgUrl(uploadHeadImage.getImgUrl()), uploadHeadImage.getWxId());
        return uploadHeadImageResult;
    }
}

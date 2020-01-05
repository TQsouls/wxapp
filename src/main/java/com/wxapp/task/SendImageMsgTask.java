package com.wxapp.task;

import com.wxapp.api.msg.SendMsg;
import com.wxapp.entity.msg.ImageMeg;
import java.util.concurrent.Callable;

public class SendImageMsgTask implements Callable<String> {
    private ImageMeg imageMeg;
    private SendMsg sendMsg;

    public SendImageMsgTask( SendMsg sendMsg,ImageMeg imageMeg) {
        this.imageMeg = imageMeg;
        this.sendMsg = sendMsg;
    }

    @Override
    public String call() throws Exception {
        String sendImageMessageResult = sendMsg.sendImageMessage(imageMeg);
        return sendImageMessageResult;
    }
}

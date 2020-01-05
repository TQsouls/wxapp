package com.wxapp.task;

import com.wxapp.api.msg.SendMsg;
import com.wxapp.entity.msg.TextMsg;

import java.util.concurrent.Callable;

public class SendTextMsgTask implements Callable<String> {

    private TextMsg textMsg;
    private SendMsg sendMsg;


    public SendTextMsgTask(SendMsg sendMsg,TextMsg textMsg) {
        this.textMsg = textMsg;
        this.sendMsg = sendMsg;
    }

    @Override
    public String call() throws Exception {
        String sendTxtMessageResult = sendMsg.sendTxtMessage(textMsg);
        return sendTxtMessageResult;
    }
}

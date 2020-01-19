package com.wxapp.responseentity.other;

import java.io.Serializable;
import java.util.Date;

public class ResponseOneGroup implements Serializable {
    private int sussEnterGroupAmount;
    private Date enterTime;

    public ResponseOneGroup( ) {
    }

    public ResponseOneGroup(int sussEnterGroupAmount, Date enterTime) {
        this.sussEnterGroupAmount = sussEnterGroupAmount;
        this.enterTime = enterTime;
    }

    public int getSussEnterGroupAmount() {
        return sussEnterGroupAmount;
    }

    public void setSussEnterGroupAmount(int sussEnterGroupAmount) {
        this.sussEnterGroupAmount = sussEnterGroupAmount;
    }

    public Date getEnterTime() {
        return enterTime;
    }

    public void setEnterTime(Date enterTime) {
        this.enterTime = enterTime;
    }
}

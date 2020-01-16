/**
  * Copyright 2019 bejson.com 
  */
package com.wxapp.jsonbean;
import java.io.Serializable;
import java.util.List;

/**
 * Auto-generated: 2019-12-31 15:56:46
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Offline implements Serializable {

    private String OFLCount;
    private List<String> OFLWxids;

    public Offline( ) {
    }

    public Offline(String OFLCount, List<String> OFLWxids) {
        this.OFLCount = OFLCount;
        this.OFLWxids = OFLWxids;
    }

    public void setOFLCount(String OFLCount) {
         this.OFLCount = OFLCount;
     }
     public String getOFLCount() {
         return OFLCount;
     }

    public void setOFLWxids(List<String> OFLWxids) {
         this.OFLWxids = OFLWxids;
     }
     public List<String> getOFLWxids() {
         return OFLWxids;
     }

}
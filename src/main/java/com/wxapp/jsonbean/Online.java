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
public class Online implements Serializable {

    private String OLCount;
    private List<String> OLWxids;

    public Online( ) {
    }

    public Online(String OLCount, List<String> OLWxids) {
        this.OLCount = OLCount;
        this.OLWxids = OLWxids;
    }

    public void setOLCount(String OLCount) {
         this.OLCount = OLCount;
     }
     public String getOLCount() {
         return OLCount;
     }

    public void setOLWxids(List<String> OLWxids) {
         this.OLWxids = OLWxids;
     }
     public List<String> getOLWxids() {
         return OLWxids;
     }

}
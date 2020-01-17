/**
  * Copyright 2019 bejson.com 
  */
package com.wxapp.jsonbean;

import java.io.Serializable;

/**
 * Auto-generated: 2019-12-31 15:56:46
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Setup implements Serializable {

    private String threshold;

    public Setup( ) {
    }

    public Setup(String threshold) {
        this.threshold = threshold;
    }

    public void setThreshold(String threshold) {
         this.threshold = threshold;
     }
     public String getThreshold() {
         return threshold;
     }

}
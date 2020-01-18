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
public class Modify implements Serializable {

    private String newPwd;
    private String newNickname;
    private String newSignature;
    private String newAvatarURL;
    private List<String> modifiedAccount;

    public Modify() {
    }

    public Modify(String newPwd, String newNickname, String newSignature, String newAvatarURL, List<String> modifiedAccount) {
        this.newPwd = newPwd;
        this.newNickname = newNickname;
        this.newSignature = newSignature;
        this.newAvatarURL = newAvatarURL;
        this.modifiedAccount = modifiedAccount;
    }

    public void setNewPwd(String newPwd) {
         this.newPwd = newPwd;
     }
     public String getNewPwd() {
         return newPwd;
     }

    public void setNewNickname(String newNickname) {
         this.newNickname = newNickname;
     }
     public String getNewNickname() {
         return newNickname;
     }

    public void setNewSignature(String newSignature) {
         this.newSignature = newSignature;
     }
     public String getNewSignature() {
         return newSignature;
     }

    public void setNewAvatarURL(String newAvatarURL) {
         this.newAvatarURL = newAvatarURL;
     }
     public String getNewAvatarURL() {
         return newAvatarURL;
     }

    public void setModifiedAccount(List<String> modifiedAccount) {
         this.modifiedAccount = modifiedAccount;
     }
     public List<String> getModifiedAccount() {
         return modifiedAccount;
     }

}
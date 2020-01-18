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
public class PostMoments implements Serializable {

    private String postTitle;
    private String postContent;
    private String imgUrl;
    private List<String> account;

    public PostMoments( ) {
    }

    public PostMoments(String postTitle, String postContent, String imgUrl, List<String> account) {
        this.postTitle = postTitle;
        this.postContent = postContent;
        this.imgUrl = imgUrl;
        this.account = account;
    }

    public void setPostTitle(String postTitle) {
         this.postTitle = postTitle;
     }
     public String getPostTitle() {
         return postTitle;
     }

    public void setPostContent(String postContent) {
         this.postContent = postContent;
     }
     public String getPostContent() {
         return postContent;
     }

    public void setImgUrl(String imgUrl) {
         this.imgUrl = imgUrl;
     }
     public String getImgUrl() {
         return imgUrl;
     }

    public void setAccount(List<String> account) {
         this.account = account;
     }
     public List<String> getAccount() {
         return account;
     }

}
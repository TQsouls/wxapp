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
public class Operation implements Serializable {

    private SendFriendMsg sendFriendMsg;
    private PostMoments postMoments;
    private Modify modify;
    private UploadContact uploadContact;
    private DownloadContact downloadContact;
    private InviteGroup inviteGroup;

    public Operation( ) {
    }

    public Operation(SendFriendMsg sendFriendMsg, PostMoments postMoments, Modify modify, UploadContact uploadContact, DownloadContact downloadContact, InviteGroup inviteGroup) {
        this.sendFriendMsg = sendFriendMsg;
        this.postMoments = postMoments;
        this.modify = modify;
        this.uploadContact = uploadContact;
        this.downloadContact = downloadContact;
        this.inviteGroup = inviteGroup;
    }

    public void setSendFriendMsg(SendFriendMsg sendFriendMsg) {
         this.sendFriendMsg = sendFriendMsg;
     }
     public SendFriendMsg getSendFriendMsg() {
         return sendFriendMsg;
     }

    public void setPostMoments(PostMoments postMoments) {
         this.postMoments = postMoments;
     }
     public PostMoments getPostMoments() {
         return postMoments;
     }

    public void setModify(Modify modify) {
         this.modify = modify;
     }
     public Modify getModify() {
         return modify;
     }

    public void setUploadContact(UploadContact uploadContact) {
         this.uploadContact = uploadContact;
     }
     public UploadContact getUploadContact() {
         return uploadContact;
     }

    public void setDownloadContact(DownloadContact downloadContact) {
         this.downloadContact = downloadContact;
     }
     public DownloadContact getDownloadContact() {
         return downloadContact;
     }

    public void setInviteGroup(InviteGroup inviteGroup) {
         this.inviteGroup = inviteGroup;
     }
     public InviteGroup getInviteGroup() {
         return inviteGroup;
     }

}
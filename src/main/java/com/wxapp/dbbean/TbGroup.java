package com.wxapp.dbbean;


public class TbGroup {

  private long groupId;
  private String groupName;
  private long userId;
  private java.sql.Timestamp groupCreateDate;


  public long getGroupId() {
    return groupId;
  }

  public void setGroupId(long groupId) {
    this.groupId = groupId;
  }


  public String getGroupName() {
    return groupName;
  }

  public void setGroupName(String groupName) {
    this.groupName = groupName;
  }


  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }


  public java.sql.Timestamp getGroupCreateDate() {
    return groupCreateDate;
  }

  public void setGroupCreateDate(java.sql.Timestamp groupCreateDate) {
    this.groupCreateDate = groupCreateDate;
  }

}

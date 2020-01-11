package com.wxapp.dbbean;


public class TbTag {

  private long tagId;
  private String tagName;
  private long userId;
  private java.sql.Timestamp tagCreateDate;


  public long getTagId() {
    return tagId;
  }

  public void setTagId(long tagId) {
    this.tagId = tagId;
  }


  public String getTagName() {
    return tagName;
  }

  public void setTagName(String tagName) {
    this.tagName = tagName;
  }


  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }


  public java.sql.Timestamp getTagCreateDate() {
    return tagCreateDate;
  }

  public void setTagCreateDate(java.sql.Timestamp tagCreateDate) {
    this.tagCreateDate = tagCreateDate;
  }

}

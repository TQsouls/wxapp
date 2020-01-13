package com.wxapp.dbbean;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "tb_user_account", schema = "app", catalog = "")
public class TbUserAccountEntity {
    private String account;
    private String accountPwd;
    private String account62A16;
    private Integer tagId;
    private boolean accountIsValid;
    private Boolean accountState;
    private Integer accountFriendCount;
    private String accountWxid;
    private int userId;
    private Integer groupId;

    //响应的字段
    private String tagName;
    private String groupName;

    // a16User.getWechatAccount(), a16User.getWechatPassword(), a16User.getWechatA16Data(),
    //                    "tag_name", "tag_id", true,
    //                    true, 0,
    //                    wxId, "group_name", group_id, "user_id"
    public TbUserAccountEntity(String account, String accountPwd, String account62A16, boolean accountIsValid, Boolean accountState, Integer accountFriendCount, String accountWxid) {
        this.account = account;
        this.accountPwd = accountPwd;
        this.account62A16 = account62A16;
        this.accountIsValid = accountIsValid;
        this.accountState = accountState;
        this.accountFriendCount = accountFriendCount;
        this.accountWxid = accountWxid;
    }

    public TbUserAccountEntity(String account, String accountPwd, String account62A16, boolean accountIsValid, Boolean accountState, Integer accountFriendCount, String accountWxid,Integer tagId, String tagName, String groupName, Integer groupId, Integer userId) {
        this.account = account;
        this.accountPwd = accountPwd;
        this.account62A16 = account62A16;
        this.accountIsValid = accountIsValid;
        this.accountState = accountState;
        this.accountFriendCount = accountFriendCount;
        this.accountWxid = accountWxid;
        this.tagId = tagId;
        this.tagName = tagName;
        this.groupName = groupName;
        this.groupId = groupId;
        this.userId = userId;
    }

    public TbUserAccountEntity() {
    }


    @Transient
    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    @Transient
    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }


    @Id
    @Column(name = "account", nullable = false, length = 50)
    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    @Basic
    @Column(name = "account_pwd", nullable = false, length = 50)
    public String getAccountPwd() {
        return accountPwd;
    }

    public void setAccountPwd(String accountPwd) {
        this.accountPwd = accountPwd;
    }

    @Basic
    @Column(name = "account_62_a16", nullable = false, length = 255)
    public String getAccount62A16() {
        return account62A16;
    }

    public void setAccount62A16(String account62A16) {
        this.account62A16 = account62A16;
    }

    @Basic
    @Column(name = "tag_id", nullable = true)
    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }

    @Basic
    @Column(name = "account_is_valid", nullable = false)
    public boolean isAccountIsValid() {
        return accountIsValid;
    }

    public void setAccountIsValid(boolean accountIsValid) {
        this.accountIsValid = accountIsValid;
    }

    @Basic
    @Column(name = "account_state", nullable = true)
    public Boolean getAccountState() {
        return accountState;
    }

    public void setAccountState(Boolean accountState) {
        this.accountState = accountState;
    }

    @Basic
    @Column(name = "account_friend_count", nullable = true)
    public Integer getAccountFriendCount() {
        return accountFriendCount;
    }

    public void setAccountFriendCount(Integer accountFriendCount) {
        this.accountFriendCount = accountFriendCount;
    }

    @Basic
    @Column(name = "account_wxid", nullable = true, length = 20)
    public String getAccountWxid() {
        return accountWxid;
    }

    public void setAccountWxid(String accountWxid) {
        this.accountWxid = accountWxid;
    }

    @Basic
    @Column(name = "user_id", nullable = false)
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "group_id", nullable = true)
    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TbUserAccountEntity that = (TbUserAccountEntity) o;
        return accountIsValid == that.accountIsValid &&
                userId == that.userId &&
                Objects.equals(account, that.account) &&
                Objects.equals(accountPwd, that.accountPwd) &&
                Objects.equals(account62A16, that.account62A16) &&
                Objects.equals(tagId, that.tagId) &&
                Objects.equals(accountState, that.accountState) &&
                Objects.equals(accountFriendCount, that.accountFriendCount) &&
                Objects.equals(accountWxid, that.accountWxid) &&
                Objects.equals(groupId, that.groupId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(account, accountPwd, account62A16, tagId, accountIsValid, accountState, accountFriendCount, accountWxid, userId, groupId);
    }
}

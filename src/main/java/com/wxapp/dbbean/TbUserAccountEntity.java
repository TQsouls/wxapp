package com.wxapp.dbbean;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "tb_user_account", schema = "app", catalog = "")
public class TbUserAccountEntity {
    private String account;
    private String accountPwd;
    private String account62A16;
    private boolean accountIsValid;
    private Boolean accountState;
    private Integer accountFriendCount;
    private String accountWxid;

    @Id
    @Column(name = "account")
    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    @Basic
    @Column(name = "account_pwd")
    public String getAccountPwd() {
        return accountPwd;
    }

    public void setAccountPwd(String accountPwd) {
        this.accountPwd = accountPwd;
    }

    @Basic
    @Column(name = "account_62_a16")
    public String getAccount62A16() {
        return account62A16;
    }

    public void setAccount62A16(String account62A16) {
        this.account62A16 = account62A16;
    }

    @Basic
    @Column(name = "account_isValid")
    public boolean isAccountIsValid() {
        return accountIsValid;
    }

    public void setAccountIsValid(boolean accountIsValid) {
        this.accountIsValid = accountIsValid;
    }

    @Basic
    @Column(name = "account_state")
    public Boolean getAccountState() {
        return accountState;
    }

    public void setAccountState(Boolean accountState) {
        this.accountState = accountState;
    }

    @Basic
    @Column(name = "account_friendCount")
    public Integer getAccountFriendCount() {
        return accountFriendCount;
    }

    public void setAccountFriendCount(Integer accountFriendCount) {
        this.accountFriendCount = accountFriendCount;
    }

    @Basic
    @Column(name = "account_wxid")
    public String getAccountWxid() {
        return accountWxid;
    }

    public void setAccountWxid(String accountWxid) {
        this.accountWxid = accountWxid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TbUserAccountEntity that = (TbUserAccountEntity) o;
        return accountIsValid == that.accountIsValid &&
                Objects.equals(account, that.account) &&
                Objects.equals(accountPwd, that.accountPwd) &&
                Objects.equals(account62A16, that.account62A16) &&
                Objects.equals(accountState, that.accountState) &&
                Objects.equals(accountFriendCount, that.accountFriendCount) &&
                Objects.equals(accountWxid, that.accountWxid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(account, accountPwd, account62A16, accountIsValid, accountState, accountFriendCount, accountWxid);
    }
}

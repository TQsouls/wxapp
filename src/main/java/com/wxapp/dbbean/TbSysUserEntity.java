package com.wxapp.dbbean;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "tb_sys_user", schema = "app", catalog = "")
public class TbSysUserEntity {
    private int userId;
    private String userAccount;
    private String userPwd;
    private String userCdkey;
    private boolean userRole;
    private String userName;
    private boolean userIsValid;

    @Id
    @Column(name = "user_id", nullable = false)
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "user_account", nullable = false, length = 20)
    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    @Basic
    @Column(name = "user_pwd", nullable = false, length = 20)
    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    @Basic
    @Column(name = "user_cdkey", nullable = true, length = 50)
    public String getUserCdkey() {
        return userCdkey;
    }

    public void setUserCdkey(String userCdkey) {
        this.userCdkey = userCdkey;
    }

    @Basic
    @Column(name = "user_role", nullable = false)
    public boolean isUserRole() {
        return userRole;
    }

    public void setUserRole(boolean userRole) {
        this.userRole = userRole;
    }

    @Basic
    @Column(name = "user_name", nullable = false, length = 20)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "user_is_valid", nullable = false)
    public boolean isUserIsValid() {
        return userIsValid;
    }

    public void setUserIsValid(boolean userIsValid) {
        this.userIsValid = userIsValid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TbSysUserEntity that = (TbSysUserEntity) o;
        return userId == that.userId &&
                userRole == that.userRole &&
                userIsValid == that.userIsValid &&
                Objects.equals(userAccount, that.userAccount) &&
                Objects.equals(userPwd, that.userPwd) &&
                Objects.equals(userCdkey, that.userCdkey) &&
                Objects.equals(userName, that.userName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, userAccount, userPwd, userCdkey, userRole, userName, userIsValid);
    }
}

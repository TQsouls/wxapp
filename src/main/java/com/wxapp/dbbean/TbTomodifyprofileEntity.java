package com.wxapp.dbbean;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "tb_tomodifyprofile", schema = "app", catalog = "")
public class TbTomodifyprofileEntity {
    private int mpId;
    private String mpNewPwd;
    private String mpNickname;
    private String mpSignature;
    private String mpAvatarUrl;
    private int userId;

    @Id
    @Column(name = "mp_id", nullable = false)
    public int getMpId() {
        return mpId;
    }

    public void setMpId(int mpId) {
        this.mpId = mpId;
    }

    @Basic
    @Column(name = "mp_new_pwd", nullable = true, length = 20)
    public String getMpNewPwd() {
        return mpNewPwd;
    }

    public void setMpNewPwd(String mpNewPwd) {
        this.mpNewPwd = mpNewPwd;
    }

    @Basic
    @Column(name = "mp_nickname", nullable = true, length = 20)
    public String getMpNickname() {
        return mpNickname;
    }

    public void setMpNickname(String mpNickname) {
        this.mpNickname = mpNickname;
    }

    @Basic
    @Column(name = "mp_signature", nullable = true, length = 255)
    public String getMpSignature() {
        return mpSignature;
    }

    public void setMpSignature(String mpSignature) {
        this.mpSignature = mpSignature;
    }

    @Basic
    @Column(name = "mp_avatar_url", nullable = true, length = 255)
    public String getMpAvatarUrl() {
        return mpAvatarUrl;
    }

    public void setMpAvatarUrl(String mpAvatarUrl) {
        this.mpAvatarUrl = mpAvatarUrl;
    }

    @Basic
    @Column(name = "user_id", nullable = false)
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TbTomodifyprofileEntity that = (TbTomodifyprofileEntity) o;
        return mpId == that.mpId &&
                userId == that.userId &&
                Objects.equals(mpNewPwd, that.mpNewPwd) &&
                Objects.equals(mpNickname, that.mpNickname) &&
                Objects.equals(mpSignature, that.mpSignature) &&
                Objects.equals(mpAvatarUrl, that.mpAvatarUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mpId, mpNewPwd, mpNickname, mpSignature, mpAvatarUrl, userId);
    }
}

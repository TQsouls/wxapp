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
    private String mpAvatarurl;

    @Id
    @Column(name = "mp_id")
    public int getMpId() {
        return mpId;
    }

    public void setMpId(int mpId) {
        this.mpId = mpId;
    }

    @Basic
    @Column(name = "mp_new_pwd")
    public String getMpNewPwd() {
        return mpNewPwd;
    }

    public void setMpNewPwd(String mpNewPwd) {
        this.mpNewPwd = mpNewPwd;
    }

    @Basic
    @Column(name = "mp_nickname")
    public String getMpNickname() {
        return mpNickname;
    }

    public void setMpNickname(String mpNickname) {
        this.mpNickname = mpNickname;
    }

    @Basic
    @Column(name = "mp_signature")
    public String getMpSignature() {
        return mpSignature;
    }

    public void setMpSignature(String mpSignature) {
        this.mpSignature = mpSignature;
    }

    @Basic
    @Column(name = "mp_avatarurl")
    public String getMpAvatarurl() {
        return mpAvatarurl;
    }

    public void setMpAvatarurl(String mpAvatarurl) {
        this.mpAvatarurl = mpAvatarurl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TbTomodifyprofileEntity that = (TbTomodifyprofileEntity) o;
        return mpId == that.mpId &&
                Objects.equals(mpNewPwd, that.mpNewPwd) &&
                Objects.equals(mpNickname, that.mpNickname) &&
                Objects.equals(mpSignature, that.mpSignature) &&
                Objects.equals(mpAvatarurl, that.mpAvatarurl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mpId, mpNewPwd, mpNickname, mpSignature, mpAvatarurl);
    }
}

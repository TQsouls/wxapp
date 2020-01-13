package com.wxapp.dbbean;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "tb_phonecontact", schema = "app", catalog = "")
public class TbPhonecontactEntity {
    private int pcId;
    private String phoneNumber;
    private boolean phoneHasUsed;
    private String phoneMd5;
    private String phoneOwnWxid;
    private String phoneV1;
    private String phoneV2;

    @Id
    @Column(name = "pc_id")
    public int getPcId() {
        return pcId;
    }

    public void setPcId(int pcId) {
        this.pcId = pcId;
    }

    @Basic
    @Column(name = "phone_number")
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Basic
    @Column(name = "phone_has_used")
    public boolean isPhoneHasUsed() {
        return phoneHasUsed;
    }

    public void setPhoneHasUsed(boolean phoneHasUsed) {
        this.phoneHasUsed = phoneHasUsed;
    }

    @Basic
    @Column(name = "phone_md5")
    public String getPhoneMd5() {
        return phoneMd5;
    }

    public void setPhoneMd5(String phoneMd5) {
        this.phoneMd5 = phoneMd5;
    }

    @Basic
    @Column(name = "phone_own_wxid")
    public String getPhoneOwnWxid() {
        return phoneOwnWxid;
    }

    public void setPhoneOwnWxid(String phoneOwnWxid) {
        this.phoneOwnWxid = phoneOwnWxid;
    }

    @Basic
    @Column(name = "phone_v1")
    public String getPhoneV1() {
        return phoneV1;
    }

    public void setPhoneV1(String phoneV1) {
        this.phoneV1 = phoneV1;
    }

    @Basic
    @Column(name = "phone_v2")
    public String getPhoneV2() {
        return phoneV2;
    }

    public void setPhoneV2(String phoneV2) {
        this.phoneV2 = phoneV2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TbPhonecontactEntity that = (TbPhonecontactEntity) o;
        return pcId == that.pcId &&
                phoneHasUsed == that.phoneHasUsed &&
                Objects.equals(phoneNumber, that.phoneNumber) &&
                Objects.equals(phoneMd5, that.phoneMd5) &&
                Objects.equals(phoneOwnWxid, that.phoneOwnWxid) &&
                Objects.equals(phoneV1, that.phoneV1) &&
                Objects.equals(phoneV2, that.phoneV2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pcId, phoneNumber, phoneHasUsed, phoneMd5, phoneOwnWxid, phoneV1, phoneV2);
    }
}

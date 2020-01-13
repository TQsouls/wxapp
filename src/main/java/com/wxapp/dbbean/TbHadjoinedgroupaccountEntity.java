package com.wxapp.dbbean;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "tb_hadjoinedgroupaccount", schema = "app", catalog = "")
public class TbHadjoinedgroupaccountEntity {
    private int jgaId;
    private String jgaWxid;
    private String jgaGroupId;
    private String jgaPhone;
    private String jgaNickname;

    @Id
    @Column(name = "jga_id")
    public int getJgaId() {
        return jgaId;
    }

    public void setJgaId(int jgaId) {
        this.jgaId = jgaId;
    }

    @Basic
    @Column(name = "jga_wxid")
    public String getJgaWxid() {
        return jgaWxid;
    }

    public void setJgaWxid(String jgaWxid) {
        this.jgaWxid = jgaWxid;
    }

    @Basic
    @Column(name = "jga_group_id")
    public String getJgaGroupId() {
        return jgaGroupId;
    }

    public void setJgaGroupId(String jgaGroupId) {
        this.jgaGroupId = jgaGroupId;
    }

    @Basic
    @Column(name = "jga_phone")
    public String getJgaPhone() {
        return jgaPhone;
    }

    public void setJgaPhone(String jgaPhone) {
        this.jgaPhone = jgaPhone;
    }

    @Basic
    @Column(name = "jga_nickname")
    public String getJgaNickname() {
        return jgaNickname;
    }

    public void setJgaNickname(String jgaNickname) {
        this.jgaNickname = jgaNickname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TbHadjoinedgroupaccountEntity that = (TbHadjoinedgroupaccountEntity) o;
        return jgaId == that.jgaId &&
                Objects.equals(jgaWxid, that.jgaWxid) &&
                Objects.equals(jgaGroupId, that.jgaGroupId) &&
                Objects.equals(jgaPhone, that.jgaPhone) &&
                Objects.equals(jgaNickname, that.jgaNickname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(jgaId, jgaWxid, jgaGroupId, jgaPhone, jgaNickname);
    }
}

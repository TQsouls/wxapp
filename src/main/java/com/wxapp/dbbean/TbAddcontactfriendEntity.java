package com.wxapp.dbbean;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "tb_addcontactfriend", schema = "app", catalog = "")
public class TbAddcontactfriendEntity {
    private int acfId;
    private String acfContent;

    @Id
    @Column(name = "acf_id")
    public int getAcfId() {
        return acfId;
    }

    public void setAcfId(int acfId) {
        this.acfId = acfId;
    }

    @Basic
    @Column(name = "acf_content")
    public String getAcfContent() {
        return acfContent;
    }

    public void setAcfContent(String acfContent) {
        this.acfContent = acfContent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TbAddcontactfriendEntity that = (TbAddcontactfriendEntity) o;
        return acfId == that.acfId &&
                Objects.equals(acfContent, that.acfContent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(acfId, acfContent);
    }
}

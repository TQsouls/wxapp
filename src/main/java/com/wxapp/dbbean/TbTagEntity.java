package com.wxapp.dbbean;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "tb_tag", schema = "app", catalog = "")
public class TbTagEntity {
    private int tagId;
    private Timestamp tagCreateDate;
    private String tagName;

    @Id
    @Column(name = "tag_id")
    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }

    @Basic
    @Column(name = "tag_create_date")
    public Timestamp getTagCreateDate() {
        return tagCreateDate;
    }

    public void setTagCreateDate(Timestamp tagCreateDate) {
        this.tagCreateDate = tagCreateDate;
    }

    @Basic
    @Column(name = "tag_name")
    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TbTagEntity that = (TbTagEntity) o;
        return tagId == that.tagId &&
                Objects.equals(tagCreateDate, that.tagCreateDate) &&
                Objects.equals(tagName, that.tagName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tagId, tagCreateDate, tagName);
    }
}

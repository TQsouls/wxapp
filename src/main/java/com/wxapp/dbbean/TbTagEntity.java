package com.wxapp.dbbean;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "tb_tag", schema = "app", catalog = "")
public class TbTagEntity {
    private int tagId;
    private String tagName;
    private int userId;
    private Timestamp tagCreateDate;

    @Id
    @Column(name = "tag_id", nullable = false)
    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }

    @Basic
    @Column(name = "tag_name", nullable = false, length = 50)
    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
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
    @Column(name = "tag_create_date", nullable = false)
    public Timestamp getTagCreateDate() {
        return tagCreateDate;
    }

    public void setTagCreateDate(Timestamp tagCreateDate) {
        this.tagCreateDate = tagCreateDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TbTagEntity that = (TbTagEntity) o;
        return tagId == that.tagId &&
                userId == that.userId &&
                Objects.equals(tagName, that.tagName) &&
                Objects.equals(tagCreateDate, that.tagCreateDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tagId, tagName, userId, tagCreateDate);
    }
}

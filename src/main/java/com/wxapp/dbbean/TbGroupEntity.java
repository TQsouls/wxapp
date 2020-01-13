package com.wxapp.dbbean;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "tb_group", schema = "app", catalog = "")
public class TbGroupEntity {
    private int groupId;
    private String groupName;
    private int userId;
    private Timestamp groupCreateDate;

    @Id
    @Column(name = "group_id", nullable = false)
    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    @Basic
    @Column(name = "group_name", nullable = false, length = 50)
    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
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
    @Column(name = "group_create_date", nullable = false)
    public Timestamp getGroupCreateDate() {
        return groupCreateDate;
    }

    public void setGroupCreateDate(Timestamp groupCreateDate) {
        this.groupCreateDate = groupCreateDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TbGroupEntity that = (TbGroupEntity) o;
        return groupId == that.groupId &&
                userId == that.userId &&
                Objects.equals(groupName, that.groupName) &&
                Objects.equals(groupCreateDate, that.groupCreateDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupId, groupName, userId, groupCreateDate);
    }
}

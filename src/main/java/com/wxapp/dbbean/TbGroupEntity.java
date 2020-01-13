package com.wxapp.dbbean;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "tb_group", schema = "app", catalog = "")
public class TbGroupEntity {
    private int groupId;
    private Timestamp groupCreateDate;
    private String groupName;

    @Id
    @Column(name = "group_id")
    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    @Basic
    @Column(name = "group_create_date")
    public Timestamp getGroupCreateDate() {
        return groupCreateDate;
    }

    public void setGroupCreateDate(Timestamp groupCreateDate) {
        this.groupCreateDate = groupCreateDate;
    }

    @Basic
    @Column(name = "group_name")
    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TbGroupEntity that = (TbGroupEntity) o;
        return groupId == that.groupId &&
                Objects.equals(groupCreateDate, that.groupCreateDate) &&
                Objects.equals(groupName, that.groupName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupId, groupCreateDate, groupName);
    }
}

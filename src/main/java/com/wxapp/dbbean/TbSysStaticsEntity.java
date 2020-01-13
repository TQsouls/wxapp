package com.wxapp.dbbean;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "tb_sys_statics", schema = "app", catalog = "")
public class TbSysStaticsEntity {
    private int ssId;
    private int userId;
    private int ssAllOnlineCount;
    private int ssAllFriendCount;
    private int ssAllDeadCount;

    @Id
    @Column(name = "ss_id", nullable = false)
    public int getSsId() {
        return ssId;
    }

    public void setSsId(int ssId) {
        this.ssId = ssId;
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
    @Column(name = "ss_all_online_count", nullable = false)
    public int getSsAllOnlineCount() {
        return ssAllOnlineCount;
    }

    public void setSsAllOnlineCount(int ssAllOnlineCount) {
        this.ssAllOnlineCount = ssAllOnlineCount;
    }

    @Basic
    @Column(name = "ss_all_friend_count", nullable = false)
    public int getSsAllFriendCount() {
        return ssAllFriendCount;
    }

    public void setSsAllFriendCount(int ssAllFriendCount) {
        this.ssAllFriendCount = ssAllFriendCount;
    }

    @Basic
    @Column(name = "ss_all_dead_count", nullable = false)
    public int getSsAllDeadCount() {
        return ssAllDeadCount;
    }

    public void setSsAllDeadCount(int ssAllDeadCount) {
        this.ssAllDeadCount = ssAllDeadCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TbSysStaticsEntity that = (TbSysStaticsEntity) o;
        return ssId == that.ssId &&
                userId == that.userId &&
                ssAllOnlineCount == that.ssAllOnlineCount &&
                ssAllFriendCount == that.ssAllFriendCount &&
                ssAllDeadCount == that.ssAllDeadCount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ssId, userId, ssAllOnlineCount, ssAllFriendCount, ssAllDeadCount);
    }
}

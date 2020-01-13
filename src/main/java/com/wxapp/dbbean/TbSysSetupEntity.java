package com.wxapp.dbbean;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "tb_sys_setup", schema = "app", catalog = "")
public class TbSysSetupEntity {
    private int suId;
    private int userId;
    private int suLoginShreshold;

    @Id
    @Column(name = "su_id", nullable = false)
    public int getSuId() {
        return suId;
    }

    public void setSuId(int suId) {
        this.suId = suId;
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
    @Column(name = "su_login_shreshold", nullable = false)
    public int getSuLoginShreshold() {
        return suLoginShreshold;
    }

    public void setSuLoginShreshold(int suLoginShreshold) {
        this.suLoginShreshold = suLoginShreshold;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TbSysSetupEntity that = (TbSysSetupEntity) o;
        return suId == that.suId &&
                userId == that.userId &&
                suLoginShreshold == that.suLoginShreshold;
    }

    @Override
    public int hashCode() {
        return Objects.hash(suId, userId, suLoginShreshold);
    }
}

package com.wxapp.dbbean;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "tb_sendmsg", schema = "app", catalog = "")
public class TbSendmsgEntity {
    private int smId;
    private String smMsg;
    private int userId;

    @Id
    @Column(name = "sm_id", nullable = false)
    public int getSmId() {
        return smId;
    }

    public void setSmId(int smId) {
        this.smId = smId;
    }

    @Basic
    @Column(name = "sm_msg", nullable = true, length = 255)
    public String getSmMsg() {
        return smMsg;
    }

    public void setSmMsg(String smMsg) {
        this.smMsg = smMsg;
    }

    @Basic
    @Column(name = "user_id", nullable = false)
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TbSendmsgEntity that = (TbSendmsgEntity) o;
        return smId == that.smId &&
                userId == that.userId &&
                Objects.equals(smMsg, that.smMsg);
    }

    @Override
    public int hashCode() {
        return Objects.hash(smId, smMsg, userId);
    }
}

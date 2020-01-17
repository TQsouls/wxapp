package com.wxapp.dbbean;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "tb_sendcircle", schema = "app", catalog = "")
public class TbSendcircleEntity {
    private int scId;
    private String scTitle;
    private String scContent;
    private String scPicUrl;
    private int userId;

    @Id
    @Column(name = "sc_id", nullable = false)
    public int getScId() {
        return scId;
    }

    public void setScId(int scId) {
        this.scId = scId;
    }

    @Basic
    @Column(name = "sc_title", nullable = true, length = 20)
    public String getScTitle() {
        return scTitle;
    }

    public void setScTitle(String scTitle) {
        this.scTitle = scTitle;
    }

    @Basic
    @Column(name = "sc_content", nullable = true, length = 255)
    public String getScContent() {
        return scContent;
    }

    public void setScContent(String scContent) {
        this.scContent = scContent;
    }

    @Basic
    @Column(name = "sc_pic_url", nullable = true, length = 255)
    public String getScPicUrl() {
        return scPicUrl;
    }

    public void setScPicUrl(String scPicUrl) {
        this.scPicUrl = scPicUrl;
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
        TbSendcircleEntity that = (TbSendcircleEntity) o;
        return scId == that.scId &&
                userId == that.userId &&
                Objects.equals(scTitle, that.scTitle) &&
                Objects.equals(scContent, that.scContent) &&
                Objects.equals(scPicUrl, that.scPicUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(scId, scTitle, scContent, scPicUrl, userId);
    }
}

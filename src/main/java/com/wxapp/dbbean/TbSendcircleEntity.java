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

    @Id
    @Column(name = "sc_id")
    public int getScId() {
        return scId;
    }

    public void setScId(int scId) {
        this.scId = scId;
    }

    @Basic
    @Column(name = "sc_title")
    public String getScTitle() {
        return scTitle;
    }

    public void setScTitle(String scTitle) {
        this.scTitle = scTitle;
    }

    @Basic
    @Column(name = "sc_content")
    public String getScContent() {
        return scContent;
    }

    public void setScContent(String scContent) {
        this.scContent = scContent;
    }

    @Basic
    @Column(name = "sc_picURL")
    public String getScPicUrl() {
        return scPicUrl;
    }

    public void setScPicUrl(String scPicUrl) {
        this.scPicUrl = scPicUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TbSendcircleEntity that = (TbSendcircleEntity) o;
        return scId == that.scId &&
                Objects.equals(scTitle, that.scTitle) &&
                Objects.equals(scContent, that.scContent) &&
                Objects.equals(scPicUrl, that.scPicUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(scId, scTitle, scContent, scPicUrl);
    }
}

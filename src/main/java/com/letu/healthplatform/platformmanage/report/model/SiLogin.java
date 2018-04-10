package com.letu.healthplatform.platformmanage.report.model;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;


public class SiLogin implements Serializable {
	
	  /** 样本主键 */
    private Integer tSiId;

    /** 销售人员主键 */
    private Integer tMLoginId;

    /** 创建时间 */
    private Date creatDate;

    private static final long serialVersionUID = 1L;

    public Integer gettSiId() {
        return tSiId;
    }

    public void settSiId(Integer tSiId) {
        this.tSiId = tSiId;
    }

    public Integer gettMLoginId() {
        return tMLoginId;
    }

    public void settMLoginId(Integer tMLoginId) {
        this.tMLoginId = tMLoginId;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public Date getCreatDate() {
        return creatDate;
    }

    public void setCreatDate(Date creatDate) {
        this.creatDate = creatDate;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        SiLogin other = (SiLogin) that;
        return (this.gettSiId() == null ? other.gettSiId() == null : this.gettSiId().equals(other.gettSiId()))
            && (this.gettMLoginId() == null ? other.gettMLoginId() == null : this.gettMLoginId().equals(other.gettMLoginId()))
            && (this.getCreatDate() == null ? other.getCreatDate() == null : this.getCreatDate().equals(other.getCreatDate()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((gettSiId() == null) ? 0 : gettSiId().hashCode());
        result = prime * result + ((gettMLoginId() == null) ? 0 : gettMLoginId().hashCode());
        result = prime * result + ((getCreatDate() == null) ? 0 : getCreatDate().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", tSiId=").append(tSiId);
        sb.append(", tMLoginId=").append(tMLoginId);
        sb.append(", creatDate=").append(creatDate);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
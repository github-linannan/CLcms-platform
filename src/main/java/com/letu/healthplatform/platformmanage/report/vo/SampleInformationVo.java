package com.letu.healthplatform.platformmanage.report.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import com.letu.healthplatform.platformmanage.user.vo.AppUserVo;


public class SampleInformationVo implements Serializable {
	
    /** 主键 */
    private Integer tSiId;

    /** 样本主题 */
    private String tSiSampleBatch;

    /** 样本创建时间 */
    private Date tSiCreatedate;

    /** 样本编号 */
    private String tSiNumber;

    /** 样本区域 */
    private String tSiArea;

    /** 创建时间 */
    private Date tSiCreateTime;

    /** 创建人 */
    private String tSiCreateBy;

    /** 修改时间 */
    private Date tSiModifyTime;

    /** 修改人 */
    private String tSiModifyBy;

    /** 备注 */
    private String tSiRemark;

    /** 类型 (个人 1 团体 2 ) */
    private Integer tSiLabel;

    /** 样本来源 */
    private String tSiSource;

    /** 检测项目 */
    private String tSiItem;

    /** 销售联系人 */
    private Integer tSaleId;

    /**团体发起负责人 */
    private Integer tUserId;

    /** 团体接收负责人 */
    private Integer tReciveUserId;
	
	 /** 患者ID */
	private String tUserIds;
	
	 /** 销售人员名字 */
	private String saleName;
	
	/** 负责人员名字 */
	private String userName;
	
    /** 团体接收负责人 */
    private String reciveUeserName;
	
	/***患者信息**/
	private List<AppUserVo>  list;

	/**
	 * @return the tReciveUserId
	 */
	public Integer gettReciveUserId() {
		return tReciveUserId;
	}

	/**
	 * @param tReciveUserId the tReciveUserId to set
	 */
	public void settReciveUserId(Integer tReciveUserId) {
		this.tReciveUserId = tReciveUserId;
	}


	/**
	 * @return the reciveUeserName
	 */
	public String getReciveUeserName() {
		return reciveUeserName;
	}

	/**
	 * @param reciveUeserName the reciveUeserName to set
	 */
	public void setReciveUeserName(String reciveUeserName) {
		this.reciveUeserName = reciveUeserName;
	}

	/**
	 * @return the list
	 */
	public List<AppUserVo> getList() {
		return list;
	}

	/**
	 * @param list the list to set
	 */
	public void setList(List<AppUserVo> list) {
		this.list = list;
	}

	/**
	 * @return the saleName
	 */
	public String getSaleName() {
		return saleName;
	}

	/**
	 * @param saleName the saleName to set
	 */
	public void setSaleName(String saleName) {
		this.saleName = saleName;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the tUserIds
	 */
	public String gettUserIds() {
		return tUserIds;
	}

	/**
	 * @param tUserIds the tUserIds to set
	 */
	public void settUserIds(String tUserIds) {
		this.tUserIds = tUserIds;
	}

	private static final long serialVersionUID = 1L;


    public Integer gettSiId() {
        return tSiId;
    }

    public void settSiId(Integer tSiId) {
        this.tSiId = tSiId;
    }

    public String gettSiSampleBatch() {
        return tSiSampleBatch;
    }

    public void settSiSampleBatch(String tSiSampleBatch) {
        this.tSiSampleBatch = tSiSampleBatch;
    }

    public Date gettSiCreatedate() {
        return tSiCreatedate;
    }

    public void settSiCreatedate(Date tSiCreatedate) {
        this.tSiCreatedate = tSiCreatedate;
    }

    public String gettSiNumber() {
        return tSiNumber;
    }

    public void settSiNumber(String tSiNumber) {
        this.tSiNumber = tSiNumber;
    }

    public String gettSiArea() {
        return tSiArea;
    }

    public void settSiArea(String tSiArea) {
        this.tSiArea = tSiArea;
    }

    public Date gettSiCreateTime() {
        return tSiCreateTime;
    }

    public void settSiCreateTime(Date tSiCreateTime) {
        this.tSiCreateTime = tSiCreateTime;
    }

    public String gettSiCreateBy() {
        return tSiCreateBy;
    }

    public void settSiCreateBy(String tSiCreateBy) {
        this.tSiCreateBy = tSiCreateBy;
    }

    public Date gettSiModifyTime() {
        return tSiModifyTime;
    }

    public void settSiModifyTime(Date tSiModifyTime) {
        this.tSiModifyTime = tSiModifyTime;
    }

    public String gettSiModifyBy() {
        return tSiModifyBy;
    }

    public void settSiModifyBy(String tSiModifyBy) {
        this.tSiModifyBy = tSiModifyBy;
    }

    public String gettSiRemark() {
        return tSiRemark;
    }

    public void settSiRemark(String tSiRemark) {
        this.tSiRemark = tSiRemark;
    }

    public Integer gettSiLabel() {
        return tSiLabel;
    }

    public void settSiLabel(Integer tSiLabel) {
        this.tSiLabel = tSiLabel;
    }

    public String gettSiSource() {
        return tSiSource;
    }

    public void settSiSource(String tSiSource) {
        this.tSiSource = tSiSource;
    }

    public String gettSiItem() {
        return tSiItem;
    }

    public void settSiItem(String tSiItem) {
        this.tSiItem = tSiItem;
    }

    public Integer gettSaleId() {
        return tSaleId;
    }

    public void settSaleId(Integer tSaleId) {
        this.tSaleId = tSaleId;
    }

    public Integer gettUserId() {
        return tUserId;
    }

    public void settUserId(Integer tUserId) {
        this.tUserId = tUserId;
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
        SampleInformationVo other = (SampleInformationVo) that;
        return (this.gettSiId() == null ? other.gettSiId() == null : this.gettSiId().equals(other.gettSiId()))
            && (this.gettSiSampleBatch() == null ? other.gettSiSampleBatch() == null : this.gettSiSampleBatch().equals(other.gettSiSampleBatch()))
            && (this.gettSiCreatedate() == null ? other.gettSiCreatedate() == null : this.gettSiCreatedate().equals(other.gettSiCreatedate()))
            && (this.gettSiNumber() == null ? other.gettSiNumber() == null : this.gettSiNumber().equals(other.gettSiNumber()))
            && (this.gettSiArea() == null ? other.gettSiArea() == null : this.gettSiArea().equals(other.gettSiArea()))
            && (this.gettSiCreateTime() == null ? other.gettSiCreateTime() == null : this.gettSiCreateTime().equals(other.gettSiCreateTime()))
            && (this.gettSiCreateBy() == null ? other.gettSiCreateBy() == null : this.gettSiCreateBy().equals(other.gettSiCreateBy()))
            && (this.gettSiModifyTime() == null ? other.gettSiModifyTime() == null : this.gettSiModifyTime().equals(other.gettSiModifyTime()))
            && (this.gettSiModifyBy() == null ? other.gettSiModifyBy() == null : this.gettSiModifyBy().equals(other.gettSiModifyBy()))
            && (this.gettSiRemark() == null ? other.gettSiRemark() == null : this.gettSiRemark().equals(other.gettSiRemark()))
            && (this.gettSiLabel() == null ? other.gettSiLabel() == null : this.gettSiLabel().equals(other.gettSiLabel()))
            && (this.gettSiSource() == null ? other.gettSiSource() == null : this.gettSiSource().equals(other.gettSiSource()))
            && (this.gettSiItem() == null ? other.gettSiItem() == null : this.gettSiItem().equals(other.gettSiItem()))
            && (this.gettSaleId() == null ? other.gettSaleId() == null : this.gettSaleId().equals(other.gettSaleId()))
            && (this.gettUserId() == null ? other.gettUserId() == null : this.gettUserId().equals(other.gettUserId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((gettSiId() == null) ? 0 : gettSiId().hashCode());
        result = prime * result + ((gettSiSampleBatch() == null) ? 0 : gettSiSampleBatch().hashCode());
        result = prime * result + ((gettSiCreatedate() == null) ? 0 : gettSiCreatedate().hashCode());
        result = prime * result + ((gettSiNumber() == null) ? 0 : gettSiNumber().hashCode());
        result = prime * result + ((gettSiArea() == null) ? 0 : gettSiArea().hashCode());
        result = prime * result + ((gettSiCreateTime() == null) ? 0 : gettSiCreateTime().hashCode());
        result = prime * result + ((gettSiCreateBy() == null) ? 0 : gettSiCreateBy().hashCode());
        result = prime * result + ((gettSiModifyTime() == null) ? 0 : gettSiModifyTime().hashCode());
        result = prime * result + ((gettSiModifyBy() == null) ? 0 : gettSiModifyBy().hashCode());
        result = prime * result + ((gettSiRemark() == null) ? 0 : gettSiRemark().hashCode());
        result = prime * result + ((gettSiLabel() == null) ? 0 : gettSiLabel().hashCode());
        result = prime * result + ((gettSiSource() == null) ? 0 : gettSiSource().hashCode());
        result = prime * result + ((gettSiItem() == null) ? 0 : gettSiItem().hashCode());
        result = prime * result + ((gettSaleId() == null) ? 0 : gettSaleId().hashCode());
        result = prime * result + ((gettUserId() == null) ? 0 : gettUserId().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", tSiId=").append(tSiId);
        sb.append(", tSiSampleBatch=").append(tSiSampleBatch);
        sb.append(", tSiCreatedate=").append(tSiCreatedate);
        sb.append(", tSiNumber=").append(tSiNumber);
        sb.append(", tSiArea=").append(tSiArea);
        sb.append(", tSiCreateTime=").append(tSiCreateTime);
        sb.append(", tSiCreateBy=").append(tSiCreateBy);
        sb.append(", tSiModifyTime=").append(tSiModifyTime);
        sb.append(", tSiModifyBy=").append(tSiModifyBy);
        sb.append(", tSiRemark=").append(tSiRemark);
        sb.append(", tSiLabel=").append(tSiLabel);
        sb.append(", tSiSource=").append(tSiSource);
        sb.append(", tSiItem=").append(tSiItem);
        sb.append(", tSaleId=").append(tSaleId);
        sb.append(", tUserId=").append(tUserId);
        sb.append(", saleName=").append(saleName);
        sb.append(", userName=").append(userName);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
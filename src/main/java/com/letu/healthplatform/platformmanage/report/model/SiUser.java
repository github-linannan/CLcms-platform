package com.letu.healthplatform.platformmanage.report.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class SiUser implements Serializable {
	
	 /** 样本主键 */
    private Integer tSiId;

    /** 患者主键 */
    private Integer tUserId;
    
    /** 创建时间 */
    private Date creatDate;

    /** 检测时间 */
    private Date tTestingTime;
    
    /**来样编号**/
    private String tComeNumber;

    /** 检测项目 */
    private String tSiProject;
    
    private List<SiUser> list;
    
    /***
     * 出报告状态
     * 0未出报告，1-已出报告
     */
    private Integer tReportStatus;
    /***
     * 出报告时间
     */
    private Date  tReportTime;
    
    /***
     *物流公司 
     */
    private String tLogistics ;
    
    /***
     * 物流单号
     */
     private String tLogisticsNumber;
    

    private static final long serialVersionUID = 1L;


	/**
	 * @return the tComeNumber
	 */
	public String gettComeNumber() {
		return tComeNumber;
	}

	/**
	 * @param tComeNumber the tComeNumber to set
	 */
	public void settComeNumber(String tComeNumber) {
		this.tComeNumber = tComeNumber;
	}

	/**
	 * @return the list
	 */
	public List<SiUser> getList() {
		return list;
	}

	/**
	 * @param list the list to set
	 */
	public void setList(List<SiUser> list) {
		this.list = list;
	}

	public Integer gettSiId() {
        return tSiId;
    }

    public void settSiId(Integer tSiId) {
        this.tSiId = tSiId;
    }

    public Integer gettUserId() {
        return tUserId;
    }

    public void settUserId(Integer tUserId) {
        this.tUserId = tUserId;
    }
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public Date getCreatDate() {
        return creatDate;
    }

    public void setCreatDate(Date creatDate) {
        this.creatDate = creatDate;
    }

    public Date gettTestingTime() {
        return tTestingTime;
    }

    public void settTestingTime(Date tTestingTime) {
        this.tTestingTime = tTestingTime;
    }

    public String gettSiProject() {
        return tSiProject;
    }

    public void settSiProject(String tSiProject) {
        this.tSiProject = tSiProject;
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
        SiUser other = (SiUser) that;
        return (this.gettSiId() == null ? other.gettSiId() == null : this.gettSiId().equals(other.gettSiId()))
            && (this.gettUserId() == null ? other.gettUserId() == null : this.gettUserId().equals(other.gettUserId()))
            && (this.getCreatDate() == null ? other.getCreatDate() == null : this.getCreatDate().equals(other.getCreatDate()))
            && (this.gettTestingTime() == null ? other.gettTestingTime() == null : this.gettTestingTime().equals(other.gettTestingTime()))
            && (this.gettSiProject() == null ? other.gettSiProject() == null : this.gettSiProject().equals(other.gettSiProject()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((gettSiId() == null) ? 0 : gettSiId().hashCode());
        result = prime * result + ((gettUserId() == null) ? 0 : gettUserId().hashCode());
        result = prime * result + ((getCreatDate() == null) ? 0 : getCreatDate().hashCode());
        result = prime * result + ((gettTestingTime() == null) ? 0 : gettTestingTime().hashCode());
        result = prime * result + ((gettSiProject() == null) ? 0 : gettSiProject().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", tSiId=").append(tSiId);
        sb.append(", tUserId=").append(tUserId);
        sb.append(", creatDate=").append(creatDate);
        sb.append(", tTestingTime=").append(tTestingTime);
        sb.append(", tSiProject=").append(tSiProject);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

	public Integer gettReportStatus() {
		return tReportStatus;
	}

	public void settReportStatus(Integer tReportStatus) {
		this.tReportStatus = tReportStatus;
	}

	public Date gettReportTime() {
		return tReportTime;
	}

	public void settReportTime(Date tReportTime) {
		this.tReportTime = tReportTime;
	}

	public String gettLogistics() {
		return tLogistics;
	}

	public void settLogistics(String tLogistics) {
		this.tLogistics = tLogistics;
	}

	public String gettLogisticsNumber() {
		return tLogisticsNumber;
	}

	public void settLogisticsNumber(String tLogisticsNumber) {
		this.tLogisticsNumber = tLogisticsNumber;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
    
    
    
}
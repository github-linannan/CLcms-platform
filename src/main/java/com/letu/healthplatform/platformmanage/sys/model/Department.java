package com.letu.healthplatform.platformmanage.sys.model;

import java.util.Date;

public class Department {
	
    private Integer tId;

    private String tDepartmentName;

    private String tDepartmentCode;

    private Integer tStatus;

    private Date tCreateTime;

    private String tCreateBy;

    private Date tModifyTime;

    private String tModifyBy;
    
    private String tRemark;
    
    private String tHospitalParentId;

    public Integer gettId() {
        return tId;
    }

    public void settId(Integer tId) {
        this.tId = tId;
    }

    public String gettDepartmentName() {
        return tDepartmentName;
    }

    public void settDepartmentName(String tDepartmentName) {
        this.tDepartmentName = tDepartmentName == null ? null : tDepartmentName.trim();
    }

    public String gettDepartmentCode() {
        return tDepartmentCode;
    }

    public void settDepartmentCode(String tDepartmentCode) {
        this.tDepartmentCode = tDepartmentCode == null ? null : tDepartmentCode.trim();
    }

    public Integer gettStatus() {
        return tStatus;
    }

    public void settStatus(Integer tStatus) {
        this.tStatus = tStatus;
    }

    public Date gettCreateTime() {
        return tCreateTime;
    }

    public void settCreateTime(Date tCreateTime) {
        this.tCreateTime = tCreateTime;
    }

    public String gettCreateBy() {
        return tCreateBy;
    }

    public void settCreateBy(String tCreateBy) {
        this.tCreateBy = tCreateBy == null ? null : tCreateBy.trim();
    }

    public Date gettModifyTime() {
        return tModifyTime;
    }

    public void settModifyTime(Date tModifyTime) {
        this.tModifyTime = tModifyTime;
    }

    public String gettModifyBy() {
        return tModifyBy;
    }

    public void settModifyBy(String tModifyBy) {
        this.tModifyBy = tModifyBy == null ? null : tModifyBy.trim();
    }

	/**
	 * @return the tRemark
	 */
	public String gettRemark() {
		return tRemark;
	}

	/**
	 * @param tRemark the tRemark to set
	 */
	public void settRemark(String tRemark) {
		this.tRemark = tRemark;
	}

	/**
	 * @return the tHospitalParentId
	 */
	public String gettHospitalParentId() {
		return tHospitalParentId;
	}

	/**
	 * @param tHospitalParentId the tHospitalParentId to set
	 */
	public void settHospitalParentId(String tHospitalParentId) {
		this.tHospitalParentId = tHospitalParentId;
	}
    
    
	
	
}
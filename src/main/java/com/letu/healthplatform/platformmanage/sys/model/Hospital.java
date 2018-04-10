package com.letu.healthplatform.platformmanage.sys.model;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

public class Hospital {
	
	
    private Integer tId;

    private String tHospitalName;

    private String tHospitalCode;

    private String tHospitalAddress;

    private Integer tStatus;

    private Date tCreateTime;

    private String tCreateBy;

    private Date tModifyTime;

    private String tModifyBy;
    
    private String tRemark;

    public Integer gettId() {
        return tId;
    }

    public void settId(Integer tId) {
        this.tId = tId;
    }

    public String gettHospitalName() {
        return tHospitalName;
    }

    public void settHospitalName(String tHospitalName) {
        this.tHospitalName = tHospitalName == null ? null : tHospitalName.trim();
    }

    public String gettHospitalCode() {
        return tHospitalCode;
    }

    public void settHospitalCode(String tHospitalCode) {
        this.tHospitalCode = tHospitalCode == null ? null : tHospitalCode.trim();
    }

    public String gettHospitalAddress() {
        return tHospitalAddress;
    }

    public void settHospitalAddress(String tHospitalAddress) {
        this.tHospitalAddress = tHospitalAddress == null ? null : tHospitalAddress.trim();
    }

    public Integer gettStatus() {
        return tStatus;
    }

    
    public void settStatus(Integer tStatus) {
        this.tStatus = tStatus;
    }
    
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
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
    
    
    
}
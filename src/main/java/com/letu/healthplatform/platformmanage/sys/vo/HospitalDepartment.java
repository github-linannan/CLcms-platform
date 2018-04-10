/**
 * 乐土精准医疗
 */
package com.letu.healthplatform.platformmanage.sys.vo;

import java.util.Date;

/**
 * @author dzb
 * @date 2017年12月25日 下午2:36:15
 * @version 1.0
 * @description 
 */
public class HospitalDepartment {
	
	
	/****/
    private Integer tId;
    /**科室名称**/
    private String tDepartmentName;
    /**科室编号**/
    private String tDepartmentCode;
    /**状态**/
    private Integer tStatus;
    /**创建时间**/
    private Date tCreateTime;
    /**创建人**/
    private String tCreateBy;
    /**修改时间**/
    private Date tModifyTime;
    /**修改人**/
    private String tModifyBy;

    /**部门父类ID**/
    private Integer tHospitalParentId;
    
    /**医院名称**/
    private String tHospitalName;
    /**医院编号**/
    private String tHospitalCode;
    /**备注**/
    private String tRemark;
    
    

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
	 * @return the tHospitalName
	 */
	public String gettHospitalName() {
		return tHospitalName;
	}

	/**
	 * @param tHospitalName the tHospitalName to set
	 */
	public void settHospitalName(String tHospitalName) {
		this.tHospitalName = tHospitalName;
	}

	/**
	 * @return the tHospitalCode
	 */
	public String gettHospitalCode() {
		return tHospitalCode;
	}

	/**
	 * @param tHospitalCode the tHospitalCode to set
	 */
	public void settHospitalCode(String tHospitalCode) {
		this.tHospitalCode = tHospitalCode;
	}

	/**
	 * @return the tHospitalParentId
	 */
	public Integer gettHospitalParentId() {
		return tHospitalParentId;
	}

	/**
	 * @param tHospitalParentId the tHospitalParentId to set
	 */
	public void settHospitalParentId(Integer tHospitalParentId) {
		this.tHospitalParentId = tHospitalParentId;
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

package com.letu.healthplatform.platformmanage.sys.model;

import java.util.Date;

public class Login {
	
	
    private Integer tId;

    private String tLoginName;

    private String tPassword;

    private String tUserName;

    private String tTelephone;

    private String tEmail;

    /**账户类型（0 系统用户 1 销售人员 2 医生 3 医院）**/
    private Integer tUserType;

    private Integer tPid;

    private String tInvite;

    private String tAddress;

    private Integer tStatus;

    private Date tCreateTime;

    private String tCreateBy;

    private Date tLastTime;

    private String tCount;
    /**角色ID**/
    private Integer tRoleId; 
    
    private Integer tHospitalId;

    private Integer tDepartmentId;
    
    /**是否是主任     0 否   1 是    空值不是医生 **/
    private Integer tDirector;
    /**主任的编码**/
    private Integer tDirectorId;
    
    /**上级ID**/
    private Integer tSuperiorId;
    /**上级邮箱**/
    private String  tSuperiorEmail;
    

    
	/**
	 * @return the tSuperiorEmail
	 */
	public String gettSuperiorEmail() {
		return tSuperiorEmail;
	}

	/**
	 * @param tSuperiorEmail the tSuperiorEmail to set
	 */
	public void settSuperiorEmail(String tSuperiorEmail) {
		this.tSuperiorEmail = tSuperiorEmail;
	}

	/**
	 * @return the tSuperiorId
	 */
	public Integer gettSuperiorId() {
		return tSuperiorId;
	}

	/**
	 * @param tSuperiorId the tSuperiorId to set
	 */
	public void settSuperiorId(Integer tSuperiorId) {
		this.tSuperiorId = tSuperiorId;
	}

	public Integer gettId() {
        return tId;
    }

    public void settId(Integer tId) {
        this.tId = tId;
    }

    public String gettLoginName() {
        return tLoginName;
    }

    public void settLoginName(String tLoginName) {
        this.tLoginName = tLoginName == null ? null : tLoginName.trim();
    }

    public String gettPassword() {
        return tPassword;
    }

    public void settPassword(String tPassword) {
        this.tPassword = tPassword == null ? null : tPassword.trim();
    }

    public String gettUserName() {
        return tUserName;
    }

    public void settUserName(String tUserName) {
        this.tUserName = tUserName == null ? null : tUserName.trim();
    }

    public String gettTelephone() {
        return tTelephone;
    }

    public void settTelephone(String tTelephone) {
        this.tTelephone = tTelephone == null ? null : tTelephone.trim();
    }

    public String gettEmail() {
        return tEmail;
    }

    public void settEmail(String tEmail) {
        this.tEmail = tEmail == null ? null : tEmail.trim();
    }

    public Integer gettUserType() {
        return tUserType;
    }

    public void settUserType(Integer tUserType) {
        this.tUserType = tUserType;
    }

    public Integer gettPid() {
        return tPid;
    }

    public void settPid(Integer tPid) {
        this.tPid = tPid;
    }

    public String gettInvite() {
        return tInvite;
    }

    public void settInvite(String tInvite) {
        this.tInvite = tInvite == null ? null : tInvite.trim();
    }

    public String gettAddress() {
        return tAddress;
    }

    public void settAddress(String tAddress) {
        this.tAddress = tAddress == null ? null : tAddress.trim();
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

    public Date gettLastTime() {
        return tLastTime;
    }

    public void settLastTime(Date tLastTime) {
        this.tLastTime = tLastTime;
    }

    public String gettCount() {
        return tCount;
    }

    public void settCount(String tCount) {
        this.tCount = tCount == null ? null : tCount.trim();
    }

	/**
	 * @return the tRoleId
	 */
	public Integer gettRoleId() {
		return tRoleId;
	}

	/**
	 * @param tRoleId the tRoleId to set
	 */
	public void settRoleId(Integer tRoleId) {
		this.tRoleId = tRoleId;
	}

	/**
	 * @return the tHospitalId
	 */
	public Integer gettHospitalId() {
		return tHospitalId;
	}

	/**
	 * @param tHospitalId the tHospitalId to set
	 */
	public void settHospitalId(Integer tHospitalId) {
		this.tHospitalId = tHospitalId;
	}

	/**
	 * @return the tDepartmentId
	 */
	public Integer gettDepartmentId() {
		return tDepartmentId;
	}

	/**
	 * @param tDepartmentId the tDepartmentId to set
	 */
	public void settDepartmentId(Integer tDepartmentId) {
		this.tDepartmentId = tDepartmentId;
	}
  
	public Integer gettDirector() {
	        return tDirector;
    }

    public void settDirector(Integer tDirector) {
        this.tDirector = tDirector;
    }

    public Integer gettDirectorId() {
        return tDirectorId;
    }

    public void settDirectorId(Integer tDirectorId) {
        this.tDirectorId = tDirectorId;
    }

	
    
    
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((tSuperiorId == null) ? 0 : tSuperiorId.hashCode());
		result = prime * result + ((tAddress == null) ? 0 : tAddress.hashCode());
		result = prime * result + ((tCount == null) ? 0 : tCount.hashCode());
		result = prime * result + ((tCreateBy == null) ? 0 : tCreateBy.hashCode());
		result = prime * result + ((tCreateTime == null) ? 0 : tCreateTime.hashCode());
		result = prime * result + ((tDepartmentId == null) ? 0 : tDepartmentId.hashCode());
		result = prime * result + ((tDirector == null) ? 0 : tDirector.hashCode());
		result = prime * result + ((tDirectorId == null) ? 0 : tDirectorId.hashCode());
		result = prime * result + ((tEmail == null) ? 0 : tEmail.hashCode());
		result = prime * result + ((tHospitalId == null) ? 0 : tHospitalId.hashCode());
		result = prime * result + ((tId == null) ? 0 : tId.hashCode());
		result = prime * result + ((tInvite == null) ? 0 : tInvite.hashCode());
		result = prime * result + ((tLastTime == null) ? 0 : tLastTime.hashCode());
		result = prime * result + ((tLoginName == null) ? 0 : tLoginName.hashCode());
		result = prime * result + ((tPassword == null) ? 0 : tPassword.hashCode());
		result = prime * result + ((tPid == null) ? 0 : tPid.hashCode());
		result = prime * result + ((tRoleId == null) ? 0 : tRoleId.hashCode());
		result = prime * result + ((tStatus == null) ? 0 : tStatus.hashCode());
		result = prime * result + ((tTelephone == null) ? 0 : tTelephone.hashCode());
		result = prime * result + ((tUserName == null) ? 0 : tUserName.hashCode());
		result = prime * result + ((tUserType == null) ? 0 : tUserType.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Login other = (Login) obj;
		if (tSuperiorId == null) {
			if (other.tSuperiorId != null)
				return false;
		} else if (!tSuperiorId.equals(other.tSuperiorId))
			return false;
		if (tAddress == null) {
			if (other.tAddress != null)
				return false;
		} else if (!tAddress.equals(other.tAddress))
			return false;
		if (tCount == null) {
			if (other.tCount != null)
				return false;
		} else if (!tCount.equals(other.tCount))
			return false;
		if (tCreateBy == null) {
			if (other.tCreateBy != null)
				return false;
		} else if (!tCreateBy.equals(other.tCreateBy))
			return false;
		if (tCreateTime == null) {
			if (other.tCreateTime != null)
				return false;
		} else if (!tCreateTime.equals(other.tCreateTime))
			return false;
		if (tDepartmentId == null) {
			if (other.tDepartmentId != null)
				return false;
		} else if (!tDepartmentId.equals(other.tDepartmentId))
			return false;
		if (tDirector == null) {
			if (other.tDirector != null)
				return false;
		} else if (!tDirector.equals(other.tDirector))
			return false;
		if (tDirectorId == null) {
			if (other.tDirectorId != null)
				return false;
		} else if (!tDirectorId.equals(other.tDirectorId))
			return false;
		if (tEmail == null) {
			if (other.tEmail != null)
				return false;
		} else if (!tEmail.equals(other.tEmail))
			return false;
		if (tHospitalId == null) {
			if (other.tHospitalId != null)
				return false;
		} else if (!tHospitalId.equals(other.tHospitalId))
			return false;
		if (tId == null) {
			if (other.tId != null)
				return false;
		} else if (!tId.equals(other.tId))
			return false;
		if (tInvite == null) {
			if (other.tInvite != null)
				return false;
		} else if (!tInvite.equals(other.tInvite))
			return false;
		if (tLastTime == null) {
			if (other.tLastTime != null)
				return false;
		} else if (!tLastTime.equals(other.tLastTime))
			return false;
		if (tLoginName == null) {
			if (other.tLoginName != null)
				return false;
		} else if (!tLoginName.equals(other.tLoginName))
			return false;
		if (tPassword == null) {
			if (other.tPassword != null)
				return false;
		} else if (!tPassword.equals(other.tPassword))
			return false;
		if (tPid == null) {
			if (other.tPid != null)
				return false;
		} else if (!tPid.equals(other.tPid))
			return false;
		if (tRoleId == null) {
			if (other.tRoleId != null)
				return false;
		} else if (!tRoleId.equals(other.tRoleId))
			return false;
		if (tStatus == null) {
			if (other.tStatus != null)
				return false;
		} else if (!tStatus.equals(other.tStatus))
			return false;
		if (tTelephone == null) {
			if (other.tTelephone != null)
				return false;
		} else if (!tTelephone.equals(other.tTelephone))
			return false;
		if (tUserName == null) {
			if (other.tUserName != null)
				return false;
		} else if (!tUserName.equals(other.tUserName))
			return false;
		if (tUserType == null) {
			if (other.tUserType != null)
				return false;
		} else if (!tUserType.equals(other.tUserType))
			return false;
		return true;
	}

	
	@Override
	public String toString() {
		return "Login [tId=" + tId + ", tLoginName=" + tLoginName + ", tPassword=" + tPassword + ", tUserName="
				+ tUserName + ", tTelephone=" + tTelephone + ", tEmail=" + tEmail + ", tUserType=" + tUserType
				+ ", tPid=" + tPid + ", tInvite=" + tInvite + ", tAddress=" + tAddress + ", tStatus=" + tStatus
				+ ", tCreateTime=" + tCreateTime + ", tCreateBy=" + tCreateBy + ", tLastTime=" + tLastTime + ", tCount="
				+ tCount + ", tRoleId=" + tRoleId + ", tHospitalId=" + tHospitalId + ", tDepartmentId=" + tDepartmentId
				+ ", tDirector=" + tDirector + ", tDirectorId=" + tDirectorId + ", tSuperiorId=" + tSuperiorId + "]";
	}
    
    
	
}
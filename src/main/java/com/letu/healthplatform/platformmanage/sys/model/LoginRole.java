package com.letu.healthplatform.platformmanage.sys.model;

import java.util.Date;

public class LoginRole {
	
    private Integer tLoginId;

    private Integer tRoleId;

    private Date crateTime;
    
    private String tRoleIds; 

    public Integer gettLoginId() {
        return tLoginId;
    }

    public void settLoginId(Integer tLoginId) {
        this.tLoginId = tLoginId;
    }

    public Integer gettRoleId() {
        return tRoleId;
    }

    public void settRoleId(Integer tRoleId) {
        this.tRoleId = tRoleId;
    }

    public Date getCrateTime() {
        return crateTime;
    }

    public void setCrateTime(Date crateTime) {
        this.crateTime = crateTime;
    }

	/**
	 * @return the tRoleIds
	 */
	public String gettRoleIds() {
		return tRoleIds;
	}

	/**
	 * @param tRoleIds the tRoleIds to set
	 */
	public void settRoleIds(String tRoleIds) {
		this.tRoleIds = tRoleIds;
	}
    
    
    
}
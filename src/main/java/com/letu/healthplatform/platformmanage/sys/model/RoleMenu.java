package com.letu.healthplatform.platformmanage.sys.model;

import java.util.Date;

public class RoleMenu {
	
    private Integer tMenuId;

    private Integer tRoleId;

    private Date crateTime;
    
    private String tMenuIds;

    public Integer gettMenuId() {
        return tMenuId;
    }

    public void settMenuId(Integer tMenuId) {
        this.tMenuId = tMenuId;
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
	 * @return the tMenuIds
	 */
	public String gettMenuIds() {
		return tMenuIds;
	}

	/**
	 * @param tMenuIds the tMenuIds to set
	 */
	public void settMenuIds(String tMenuIds) {
		this.tMenuIds = tMenuIds;
	}
    
    
}
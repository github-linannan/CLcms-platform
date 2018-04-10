package com.letu.healthplatform.platformmanage.sys.model;

import java.util.Date;

public class Invite {
	
	
    private Integer inviteId;

    private String inviteCode;

    private Date inviteCreatetime;

    
    /**
	 * 
	 */
	public Invite() {
	}
	
	
	public Invite(String inviteCode) {
		this.inviteCode=inviteCode;
	}
	
    
    public Integer getInviteId() {
        return inviteId;
    }

    public void setInviteId(Integer inviteId) {
        this.inviteId = inviteId;
    }

    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode == null ? null : inviteCode.trim();
    }

    public Date getInviteCreatetime() {
        return inviteCreatetime;
    }

    public void setInviteCreatetime(Date inviteCreatetime) {
        this.inviteCreatetime = inviteCreatetime;
    }
}
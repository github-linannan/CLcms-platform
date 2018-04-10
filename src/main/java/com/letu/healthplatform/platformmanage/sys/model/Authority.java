package com.letu.healthplatform.platformmanage.sys.model;

import java.util.Date;

public class Authority {
    private Integer tId;

    private String tAuthorityName;

    private Date tCreateTime;

    private String tRemark;

    public Integer gettId() {
        return tId;
    }

    public void settId(Integer tId) {
        this.tId = tId;
    }

    public String gettAuthorityName() {
        return tAuthorityName;
    }

    public void settAuthorityName(String tAuthorityName) {
        this.tAuthorityName = tAuthorityName == null ? null : tAuthorityName.trim();
    }

    public Date gettCreateTime() {
        return tCreateTime;
    }

    public void settCreateTime(Date tCreateTime) {
        this.tCreateTime = tCreateTime;
    }

    public String gettRemark() {
        return tRemark;
    }

    public void settRemark(String tRemark) {
        this.tRemark = tRemark == null ? null : tRemark.trim();
    }
}
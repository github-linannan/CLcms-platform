package com.letu.healthplatform.platformmanage.user.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class AppUser {
    
    private Integer userId;

  
    private String userName;

 
    private String userSex;

 
    private Date userBirthday;

 
    private Integer userHeight;

  
    private Integer userWeight;


    private String userNative;


    private Integer loginId;
    
    /***
     * 过滤 不需要的字段
     */
    private String ids;

   
    private String userTelephone;

   
    private String userAddress;

  
    private String userAddressInfo;

  
    private String userEducation;

  
    private Integer userRelationship;

    
    private String loginTelephone;
   
    public Integer getUserId() {
        return userId;
    }

  
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

  
    public String getUserName() {
        return userName;
    }


    public void setUserName(String userName) {
        this.userName = userName;
    }

 
    public String getUserSex() {
        return userSex;
    }

  
    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

   

    public Date getUserBirthday() {
        return userBirthday;
    }

  
    public void setUserBirthday(Date userBirthday) {
        this.userBirthday = userBirthday;
    }

  
    public Integer getUserHeight() {
        return userHeight;
    }


    public void setUserHeight(Integer userHeight) {
        this.userHeight = userHeight;
    }

   
    public Integer getUserWeight() {
        return userWeight;
    }

  
    public void setUserWeight(Integer userWeight) {
        this.userWeight = userWeight;
    }


    public String getUserNative() {
        return userNative;
    }

    public void setUserNative(String userNative) {
        this.userNative = userNative;
    }

   
    public Integer getLoginId() {
        return loginId;
    }

   
    public void setLoginId(Integer loginId) {
        this.loginId = loginId;
    }

    
    public String getUserTelephone() {
        return userTelephone;
    }

  
    public void setUserTelephone(String userTelephone) {
        this.userTelephone = userTelephone;
    }

   
    public String getUserAddress() {
        return userAddress;
    }

   
    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

   
    public String getUserAddressInfo() {
        return userAddressInfo;
    }

 
    public void setUserAddressInfo(String userAddressInfo) {
        this.userAddressInfo = userAddressInfo;
    }

  
    public String getUserEducation() {
        return userEducation;
    }

  
    public void setUserEducation(String userEducation) {
        this.userEducation = userEducation;
    }

 

	/**
	 * @return the userRelationship
	 */
	public Integer getUserRelationship() {
		return userRelationship;
	}


	/**
	 * @param userRelationship the userRelationship to set
	 */
	public void setUserRelationship(Integer userRelationship) {
		this.userRelationship = userRelationship;
	}


	public String getLoginTelephone() {
		return loginTelephone;
	}

	public void setLoginTelephone(String loginTelephone) {
		this.loginTelephone = loginTelephone;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}
    
    
}
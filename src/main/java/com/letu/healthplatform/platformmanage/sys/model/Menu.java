package com.letu.healthplatform.platformmanage.sys.model;

import java.util.List;

public class Menu {
	
	/**菜单ID**/
    private Integer tMenuId;
    /**菜单父级id**/
    private Integer tMenuParentid;
    /**菜单url**/
    private String tMenuUrl;
    /**菜单名称**/
    private String tMenuName;
    /**排序**/
    private Integer tMenuOrdernum;
    /**类型**/
    private String tMenuType;
    /**状态**/
    private Integer  tMenuStatus;
    
    private List<Menu> childrenList;

    public Integer gettMenuId() {
        return tMenuId;
    }

    public void settMenuId(Integer tMenuId) {
        this.tMenuId = tMenuId;
    }

    public Integer gettMenuParentid() {
        return tMenuParentid;
    }

    public void settMenuParentid(Integer tMenuParentid) {
        this.tMenuParentid = tMenuParentid;
    }

    public String gettMenuUrl() {
        return tMenuUrl;
    }

    public void settMenuUrl(String tMenuUrl) {
        this.tMenuUrl = tMenuUrl == null ? null : tMenuUrl.trim();
    }

    public String gettMenuName() {
        return tMenuName;
    }

    public void settMenuName(String tMenuName) {
        this.tMenuName = tMenuName == null ? null : tMenuName.trim();
    }

    public Integer gettMenuOrdernum() {
        return tMenuOrdernum;
    }

    public void settMenuOrdernum(Integer tMenuOrdernum) {
        this.tMenuOrdernum = tMenuOrdernum;
    }

    public String gettMenuType() {
        return tMenuType;
    }

    public void settMenuType(String tMenuType) {
        this.tMenuType = tMenuType == null ? null : tMenuType.trim();
    }

	/**
	 * @return the childrenList
	 */
	public List<Menu> getChildrenList() {
		return childrenList;
	}

	/**
	 * @param childrenList the childrenList to set
	 */
	public void setChildrenList(List<Menu> childrenList) {
		this.childrenList = childrenList;
	}

	/**
	 * @return the tMenuStatus
	 */
	public Integer gettMenuStatus() {
		return tMenuStatus;
	}

	/**
	 * @param tMenuStatus the tMenuStatus to set
	 */
	public void settMenuStatus(Integer tMenuStatus) {
		this.tMenuStatus = tMenuStatus;
	}
    
	
	
    
}
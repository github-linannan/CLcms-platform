package com.letu.healthplatform.platformmanage.sys.model;

import java.util.Date;

public class TypeGroup {
	
    private Integer id;
    /**类型名称**/
    private String typeName;
    /**类型编码**/
    private String typeCode;
    /**类型排序**/
    private Integer typeDesc;

    private Date crateDate;
    /**类型**/
    private String type;
    
    /**状态**/
    private  Integer  state ;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName == null ? null : typeName.trim();
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode == null ? null : typeCode.trim();
    }

    public Integer getTypeDesc() {
        return typeDesc;
    }

    public void setTypeDesc(Integer typeDesc) {
        this.typeDesc = typeDesc;
    }

    public Date getCrateDate() {
        return crateDate;
    }

    public void setCrateDate(Date crateDate) {
        this.crateDate = crateDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

	/**
	 * @return the state
	 */
	public Integer getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(Integer state) {
		this.state = state;
	}
    
    
}
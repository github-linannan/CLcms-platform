package com.letu.healthplatform.platformmanage.order.model;

import java.util.Date;

public class OrderSendInfo {
	
	/****/
    private Integer orderSendId;
    /**订单详情**/
    private String orderinfoId;
    /**回寄物流类型**/
    private String orderReturnShipType;
    /****/
    private String orderReturnShipNumber;
    /**订单详情状态(1.未发货2：已发货（盒子）3：已回寄，4：已收到样品，5：检测中，6：报告待审核，7：完成报告 。)**/
    private Integer orderInfoStatus;
    /**回寄地址**/
    private Integer orderReturnAddressId;
    /**回寄时间**/
    private Date orderReturnShipTime;
    /**检测人ID**/
    private Integer userId;
    /**已接收样品时间**/
    private Date orderAccpectShipTime;
    /**检测时间**/
    private Date orderDetectionShipTime;
    /**报告审核时间**/
    private Date orderCheckShipTime;
    /**报告完成时间**/
    private Date orderFinshShipTime;
    
    /**订单编号**/
    private String orderNumber;
    
    /**采样盒编号**/
    private String  tSampleNumber;
    /**检测项目**/
    private  String  tSiProject;
    
    private Date orderReturnCreateTime;

    public Integer getOrderSendId() {
        return orderSendId;
    }

    public void setOrderSendId(Integer orderSendId) {
        this.orderSendId = orderSendId;
    }

    public String getOrderinfoId() {
        return orderinfoId;
    }

    public void setOrderinfoId(String orderinfoId) {
        this.orderinfoId = orderinfoId == null ? null : orderinfoId.trim();
    }

    public Date getOrderReturnShipTime() {
        return orderReturnShipTime;
    }

    public void setOrderReturnShipTime(Date orderReturnShipTime) {
        this.orderReturnShipTime = orderReturnShipTime;
    }

    public String getOrderReturnShipType() {
        return orderReturnShipType;
    }

    public void setOrderReturnShipType(String orderReturnShipType) {
        this.orderReturnShipType = orderReturnShipType == null ? null : orderReturnShipType.trim();
    }

    public String getOrderReturnShipNumber() {
        return orderReturnShipNumber;
    }

    public void setOrderReturnShipNumber(String orderReturnShipNumber) {
        this.orderReturnShipNumber = orderReturnShipNumber == null ? null : orderReturnShipNumber.trim();
    }

    public Integer getOrderReturnAddressId() {
        return orderReturnAddressId;
    }

    public void setOrderReturnAddressId(Integer orderReturnAddressId) {
        this.orderReturnAddressId = orderReturnAddressId;
    }

    public Date getOrderAccpectShipTime() {
        return orderAccpectShipTime;
    }

    public void setOrderAccpectShipTime(Date orderAccpectShipTime) {
        this.orderAccpectShipTime = orderAccpectShipTime;
    }

    public Date getOrderDetectionShipTime() {
        return orderDetectionShipTime;
    }

    public void setOrderDetectionShipTime(Date orderDetectionShipTime) {
        this.orderDetectionShipTime = orderDetectionShipTime;
    }

    public Date getOrderCheckShipTime() {
        return orderCheckShipTime;
    }

    public void setOrderCheckShipTime(Date orderCheckShipTime) {
        this.orderCheckShipTime = orderCheckShipTime;
    }

    public Date getOrderFinshShipTime() {
        return orderFinshShipTime;
    }

    public void setOrderFinshShipTime(Date orderFinshShipTime) {
        this.orderFinshShipTime = orderFinshShipTime;
    }

    public Integer getOrderInfoStatus() {
        return orderInfoStatus;
    }

    public void setOrderInfoStatus(Integer orderInfoStatus) {
        this.orderInfoStatus = orderInfoStatus;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

	/**
	 * @return the orderNumber
	 */
	public String getOrderNumber() {
		return orderNumber;
	}

	/**
	 * @param orderNumber the orderNumber to set
	 */
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	/**
	 * @return the tSampleNumber
	 */
	public String gettSampleNumber() {
		return tSampleNumber;
	}

	/**
	 * @param tSampleNumber the tSampleNumber to set
	 */
	public void settSampleNumber(String tSampleNumber) {
		this.tSampleNumber = tSampleNumber;
	}

	/**
	 * @return the tSiProject
	 */
	public String gettSiProject() {
		return tSiProject;
	}

	/**
	 * @param tSiProject the tSiProject to set
	 */
	public void settSiProject(String tSiProject) {
		this.tSiProject = tSiProject;
	}

	/**
	 * @return the orderReturnCreateTime
	 */
	public Date getOrderReturnCreateTime() {
		return orderReturnCreateTime;
	}

	/**
	 * @param orderReturnCreateTime the orderReturnCreateTime to set
	 */
	public void setOrderReturnCreateTime(Date orderReturnCreateTime) {
		this.orderReturnCreateTime = orderReturnCreateTime;
	}
    
    
	
    
}
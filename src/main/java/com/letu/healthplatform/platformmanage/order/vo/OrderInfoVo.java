/**
 * 乐土精准医疗有限公司
 */
package com.letu.healthplatform.platformmanage.order.vo;

import java.util.Date;
import java.util.List;


/**
 * @author dongzhibo
 * @date 2018年2月26日 下午3:50:10
 * @version 1.0.0
 * @description 
 */
public class OrderInfoVo {
	
	private String   orderNumber;
	
	private String   jcName;
	
	private String   loginNickname;
	
	private String    loginTelephone ;
	
	private Double   orderPayTotalPrice;
	
	private String   orderParticulars;
	
	private Integer   orderStatus;
	
	private Date   orderCreateTime;
	
	private String   loginInvite;
	
	private String   addressInfo;
	
	private Integer   goodsId;
	
	private String   goodsName;
	
	private Integer   orderGoodsNum;
	
	private Double   orderGoodsPrice;
	
	private Float   orderGoodsDiscount;
	
	private Double   orderGoodsDiscountPrice;
	

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
	 * @return the jcName
	 */
	public String getJcName() {
		return jcName;
	}


	/**
	 * @param jcName the jcName to set
	 */
	public void setJcName(String jcName) {
		this.jcName = jcName;
	}


	/**
	 * @return the loginNickname
	 */
	public String getLoginNickname() {
		return loginNickname;
	}


	/**
	 * @param loginNickname the loginNickname to set
	 */
	public void setLoginNickname(String loginNickname) {
		this.loginNickname = loginNickname;
	}


	/**
	 * @return the loginTelephone
	 */
	public String getLoginTelephone() {
		return loginTelephone;
	}


	/**
	 * @param loginTelephone the loginTelephone to set
	 */
	public void setLoginTelephone(String loginTelephone) {
		this.loginTelephone = loginTelephone;
	}


	/**
	 * @return the orderPayTotalPrice
	 */
	public Double getOrderPayTotalPrice() {
		return orderPayTotalPrice;
	}


	/**
	 * @param orderPayTotalPrice the orderPayTotalPrice to set
	 */
	public void setOrderPayTotalPrice(Double orderPayTotalPrice) {
		this.orderPayTotalPrice = orderPayTotalPrice;
	}


	/**
	 * @return the orderParticulars
	 */
	public String getOrderParticulars() {
		return orderParticulars;
	}


	/**
	 * @param orderParticulars the orderParticulars to set
	 */
	public void setOrderParticulars(String orderParticulars) {
		this.orderParticulars = orderParticulars;
	}


	/**
	 * @return the orderStatus
	 */
	public Integer getOrderStatus() {
		return orderStatus;
	}


	/**
	 * @param orderStatus the orderStatus to set
	 */
	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}


	/**
	 * @return the orderCreateTime
	 */
	public Date getOrderCreateTime() {
		return orderCreateTime;
	}


	/**
	 * @param orderCreateTime the orderCreateTime to set
	 */
	public void setOrderCreateTime(Date orderCreateTime) {
		this.orderCreateTime = orderCreateTime;
	}




	/**
	 * @return the loginInvite
	 */
	public String getLoginInvite() {
		return loginInvite;
	}


	/**
	 * @param loginInvite the loginInvite to set
	 */
	public void setLoginInvite(String loginInvite) {
		this.loginInvite = loginInvite;
	}


	/**
	 * @return the addressInfo
	 */
	public String getAddressInfo() {
		return addressInfo;
	}


	/**
	 * @param addressInfo the addressInfo to set
	 */
	public void setAddressInfo(String addressInfo) {
		this.addressInfo = addressInfo;
	}


	/**
	 * @return the goodsId
	 */
	public Integer getGoodsId() {
		return goodsId;
	}


	/**
	 * @param goodsId the goodsId to set
	 */
	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}


	/**
	 * @return the goodsName
	 */
	public String getGoodsName() {
		return goodsName;
	}


	/**
	 * @param goodsName the goodsName to set
	 */
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}


	/**
	 * @return the orderGoodsNum
	 */
	public Integer getOrderGoodsNum() {
		return orderGoodsNum;
	}


	/**
	 * @param orderGoodsNum the orderGoodsNum to set
	 */
	public void setOrderGoodsNum(Integer orderGoodsNum) {
		this.orderGoodsNum = orderGoodsNum;
	}


	/**
	 * @return the orderGoodsPrice
	 */
	public Double getOrderGoodsPrice() {
		return orderGoodsPrice;
	}


	/**
	 * @param orderGoodsPrice the orderGoodsPrice to set
	 */
	public void setOrderGoodsPrice(Double orderGoodsPrice) {
		this.orderGoodsPrice = orderGoodsPrice;
	}


	/**
	 * @return the orderGoodsDiscount
	 */
	public Float getOrderGoodsDiscount() {
		return orderGoodsDiscount;
	}


	/**
	 * @param orderGoodsDiscount the orderGoodsDiscount to set
	 */
	public void setOrderGoodsDiscount(Float orderGoodsDiscount) {
		this.orderGoodsDiscount = orderGoodsDiscount;
	}


	/**
	 * @return the orderGoodsDiscountPrice
	 */
	public Double getOrderGoodsDiscountPrice() {
		return orderGoodsDiscountPrice;
	}


	/**
	 * @param orderGoodsDiscountPrice the orderGoodsDiscountPrice to set
	 */
	public void setOrderGoodsDiscountPrice(Double orderGoodsDiscountPrice) {
		this.orderGoodsDiscountPrice = orderGoodsDiscountPrice;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "OrderInfoVo [orderNumber=" + orderNumber + ", jcName=" + jcName + ", loginNickname=" + loginNickname
				+ ", loginTelephone=" + loginTelephone + ", orderPayTotalPrice=" + orderPayTotalPrice
				+ ", orderParticulars=" + orderParticulars + ", orderStatus=" + orderStatus + ", orderCreateTime="
				+ orderCreateTime + ", loginInvite=" + loginInvite + ", addressInfo=" + addressInfo + ", goodsId="
				+ goodsId + ", goodsName=" + goodsName + ", orderGoodsNum=" + orderGoodsNum + ", orderGoodsPrice="
				+ orderGoodsPrice + ", orderGoodsDiscount=" + orderGoodsDiscount + ", orderGoodsDiscountPrice="
				+ orderGoodsDiscountPrice + "]";
	}
	
	
	

}

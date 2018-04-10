/**
 * 乐土精准医疗有限公司
 */
package com.letu.healthplatform.platformmanage.order.vo;

import java.util.Date;
import java.util.List;

/**
 * @author dongzhibo
 * @date 2018年2月27日 下午5:09:27
 * @version 1.0.0
 * @description
 */
public class OrderDetailsVo {
	
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
	
	private  List<GoodsVo> list;

	/**
	 * @return the list
	 */
	public List<GoodsVo> getList() {
		return list;
	}

	/**
	 * @param list the list to set
	 */
	public void setList(List<GoodsVo> list) {
		this.list = list;
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
	
	

}

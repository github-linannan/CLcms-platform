package com.letu.healthplatform.platformmanage.order.model;

import java.util.Date;


public class Order {
  
    private Integer orderId;

    /**订单编号**/
    private String orderNumber;

    /**登陆ID**/
    private Integer loginId;

    /**订单总金额**/
    private Double orderPayTotalPrice;

    /**订单状态 0：未支付，1：支付成功，2：已发货（盒子） 3：已回寄，4：已收到样品，5：检测中，6：报告待审核，7：完成报告 。-1支付失败，-2申请退款，-3已退款 -9 删除订单  **/
    private String orderStatus;

    /**支付方式**/
    private String orderPayType;

    /**支付时间**/
    private Date orderPayTime;

    /**发货时间**/
    private Date orderShipTime;

    /**回寄时间**/
    private Date orderReturnTime;

    /**采集时间**/
    private Date orderCollectionTime;

    /**发货物流类型**/
    private String orderShipType;

    /**发货单号**/
    private String orderShipNumber;

    /**创建时间**/
    private Date orderCreateTime;

    /**更新时间**/
    private Date orderUpdateTime;

    /**收货地址**/
    private Integer orderShipAddress;
    /**回寄地址**/
    private Integer orderReturnShipAddress;
    
    private String  goodsName;

    /***
     * 邀请码
     */
    private String userInvite;
    
    
    /**购买紧急程度   购车 立即**/
    private Integer orderDegree;
    
    private String[] orderStatus_;
    
    private String loginNickname;
    /***采样盒编号**/
    private String tSampleNumbers;
    
    /**电话**/
    private  String   loginTelephone;
    
    /**收货人号码**/
    private  String addressTelephone;
    /**
	 * 
	 */
	public Order() {
		// TODO Auto-generated constructor stub
	}
    
    /**
	 * 
	 */
	public Order(String orderNumber) {
		this.orderNumber=orderNumber;
	}
	
	
	
    
    public Integer getOrderId() {
        return orderId;
    }

 
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    
    public String getOrderNumber() {
        return orderNumber;
    }

    
    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    
    public Integer getLoginId() {
        return loginId;
    }

   
    public void setLoginId(Integer loginId) {
        this.loginId = loginId;
    }

  
    public Double getOrderPayTotalPrice() {
        return orderPayTotalPrice;
    }

   
    public void setOrderPayTotalPrice(Double orderPayTotalPrice) {
        this.orderPayTotalPrice = orderPayTotalPrice;
    }

    
    public String getOrderStatus() {
        return orderStatus;
    }

   
    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    
    public String getOrderPayType() {
        return orderPayType;
    }

   
    public void setOrderPayType(String orderPayType) {
        this.orderPayType = orderPayType;
    }

   
    public Date getOrderPayTime() {
        return orderPayTime;
    }

   
    public void setOrderPayTime(Date orderPayTime) {
        this.orderPayTime = orderPayTime;
    }

    
    public Date getOrderShipTime() {
        return orderShipTime;
    }

    
    public void setOrderShipTime(Date orderShipTime) {
        this.orderShipTime = orderShipTime;
    }

   
    public Date getOrderReturnTime() {
        return orderReturnTime;
    }

   
    public void setOrderReturnTime(Date orderReturnTime) {
        this.orderReturnTime = orderReturnTime;
    }

    
    public Date getOrderCollectionTime() {
        return orderCollectionTime;
    }

   
    public void setOrderCollectionTime(Date orderCollectionTime) {
        this.orderCollectionTime = orderCollectionTime;
    }

   
    public String getOrderShipType() {
        return orderShipType;
    }

    
    public void setOrderShipType(String orderShipType) {
        this.orderShipType = orderShipType;
    }

    
    public String getOrderShipNumber() {
        return orderShipNumber;
    }

   
    public void setOrderShipNumber(String orderShipNumber) {
        this.orderShipNumber = orderShipNumber;
    }


    public Date getOrderCreateTime() {
        return orderCreateTime;
    }

    
    public void setOrderCreateTime(Date orderCreateTime) {
        this.orderCreateTime = orderCreateTime;
    }

 
    public Date getOrderUpdateTime() {
        return orderUpdateTime;
    }

   
    public void setOrderUpdateTime(Date orderUpdateTime) {
        this.orderUpdateTime = orderUpdateTime;
    }

    
    public Integer getOrderShipAddress() {
        return orderShipAddress;
    }

 
    public void setOrderShipAddress(Integer orderShipAddress) {
        this.orderShipAddress = orderShipAddress;
    }

  
    public Integer getOrderReturnShipAddress() {
        return orderReturnShipAddress;
    }

   
    public void setOrderReturnShipAddress(Integer orderReturnShipAddress) {
        this.orderReturnShipAddress = orderReturnShipAddress;
    }


	public String getUserInvite() {
		return userInvite;
	}

	public void setUserInvite(String userInvite) {
		this.userInvite = userInvite;
	}


	/**
	 * @return the orderDegree
	 */
	public Integer getOrderDegree() {
		return orderDegree;
	}


	/**
	 * @param orderDegree the orderDegree to set
	 */
	public void setOrderDegree(Integer orderDegree) {
		this.orderDegree = orderDegree;
	}


	/**
	 * @return the orderStatus_
	 */
	public String[] getOrderStatus_() {
		return orderStatus_;
	}


	/**
	 * @param orderStatus_ the orderStatus_ to set
	 */
	public void setOrderStatus_(String[] orderStatus_) {
		this.orderStatus_ = orderStatus_;
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
	 * @return the tSampleNumbers
	 */
	public String gettSampleNumbers() {
		return tSampleNumbers;
	}


	/**
	 * @param tSampleNumbers the tSampleNumbers to set
	 */
	public void settSampleNumbers(String tSampleNumbers) {
		this.tSampleNumbers = tSampleNumbers;
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
	 * @return the addressTelephone
	 */
	public String getAddressTelephone() {
		return addressTelephone;
	}

	/**
	 * @param addressTelephone the addressTelephone to set
	 */
	public void setAddressTelephone(String addressTelephone) {
		this.addressTelephone = addressTelephone;
	}

	
	
    
}
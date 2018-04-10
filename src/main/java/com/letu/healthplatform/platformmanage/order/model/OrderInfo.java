package com.letu.healthplatform.platformmanage.order.model;

import java.util.Date;

public class OrderInfo {
   
    private Integer orderinfoId;

    /**订单编号**/
    private String orderNumber;

    /**商品ID**/
    private Integer goodsId;

    /**购买商品数量**/
    private Integer orderGoodsNum;

    /**商品价格**/
    private Double orderGoodsPrice;

    /**打折**/
    private Float orderGoodsDiscount;

    /**折后价格**/
    private Double orderGoodsDiscountPrice;

    /**创建时间**/
    private Date orderCreateTime;

    private Date orderUpdateTime;

    /**卖家留言**/
    private String orderParticulars;
    
    
    private String orderInfoStatus;
    /**
	 * 
	 */
	public OrderInfo() {
		// TODO Auto-generated constructor stub
	}
    
    /**
	 * 
	 */
	public OrderInfo(String orderNumber) {
		this.orderNumber=orderNumber;
	}
	
	
	
 
    public Integer getOrderinfoId() {
        return orderinfoId;
    }

   
    public void setOrderinfoId(Integer orderinfoId) {
        this.orderinfoId = orderinfoId;
    }

   
    public String getOrderNumber() {
        return orderNumber;
    }

  
    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

   
    public Integer getGoodsId() {
        return goodsId;
    }

   
    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    
    public Integer getOrderGoodsNum() {
        return orderGoodsNum;
    }

   
    public void setOrderGoodsNum(Integer orderGoodsNum) {
        this.orderGoodsNum = orderGoodsNum;
    }

    public Double getOrderGoodsPrice() {
        return orderGoodsPrice;
    }

    
    public void setOrderGoodsPrice(Double orderGoodsPrice) {
        this.orderGoodsPrice = orderGoodsPrice;
    }

    public Float getOrderGoodsDiscount() {
        return orderGoodsDiscount;
    }

   
    public void setOrderGoodsDiscount(Float orderGoodsDiscount) {
        this.orderGoodsDiscount = orderGoodsDiscount;
    }

    
    public Double getOrderGoodsDiscountPrice() {
        return orderGoodsDiscountPrice;
    }

   
    public void setOrderGoodsDiscountPrice(Double orderGoodsDiscountPrice) {
        this.orderGoodsDiscountPrice = orderGoodsDiscountPrice;
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

	public String getOrderInfoStatus() {
		return orderInfoStatus;
	}

	public void setOrderInfoStatus(String orderInfoStatus) {
		this.orderInfoStatus = orderInfoStatus;
	}
    
    
}
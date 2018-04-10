/**
 * 乐土精准医疗有限公司
 */
package com.letu.healthplatform.platformmanage.order.vo;

/**
 * @author dongzhibo
 * @date 2018年2月27日 下午5:02:00
 * @version 1.0.0
 * @description  订单商品详情
 */
public class GoodsVo {
	
	/**商品ID**/
    private Integer goodsId;
    

    /**商品名称**/
    private String goodsName;
    
	
	  /**原价**/
    private Double goodsPrice;

    /**打折**/
    private Float goodsDiscount;

    /**折后价格**/
    private Double goodsDiscountPrice;
    
    /**商品数量**/
    private Integer   orderGoodsNum;

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
	 * @return the goodsPrice
	 */
	public Double getGoodsPrice() {
		return goodsPrice;
	}

	/**
	 * @param goodsPrice the goodsPrice to set
	 */
	public void setGoodsPrice(Double goodsPrice) {
		this.goodsPrice = goodsPrice;
	}

	/**
	 * @return the goodsDiscount
	 */
	public Float getGoodsDiscount() {
		return goodsDiscount;
	}

	/**
	 * @param goodsDiscount the goodsDiscount to set
	 */
	public void setGoodsDiscount(Float goodsDiscount) {
		this.goodsDiscount = goodsDiscount;
	}

	/**
	 * @return the goodsDiscountPrice
	 */
	public Double getGoodsDiscountPrice() {
		return goodsDiscountPrice;
	}

	/**
	 * @param goodsDiscountPrice the goodsDiscountPrice to set
	 */
	public void setGoodsDiscountPrice(Double goodsDiscountPrice) {
		this.goodsDiscountPrice = goodsDiscountPrice;
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
    
    
    

}

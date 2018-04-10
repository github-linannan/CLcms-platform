package com.letu.healthplatform.platformmanage.goods.model;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonInclude;

/***
 * 商品表
 * */
public class Goods   implements
       Serializable{
    
	/**
	 * 
	 */
	private static final long serialVersionUID = -1816187995811433083L;

	/**商品ID**/
    private Integer goodsId;

    /**商品名称**/
    private String goodsName;

    /**商品短名称**/
    private String goodsShortname;

    /****/
    private String goodsImageOne;

    /****/
    private String goodsImageTwo;

    /****/
    private String goodsImageThree;

    /****/
    private String goodsImageFour;

    /****/
    private String goodsImage1Five;

    /**原价**/
    private Double goodsPrice;

    /**打折**/
    private Float goodsDiscount;

    /**折后价格**/
    private Double goodsDiscountPrice;

    /****/
    private Integer goodsCounts;
    
    //2017/12/01 添加字段值	
    /**产品类型**/
    private  Integer goodsTypeId;	
    /**排序字段**/
    private  Integer  goodsDesc;	
    /**商品状态**/
    private Integer goodsStatus;
   

  
    /**
	 * @return the goodsTypeId
	 */
	public Integer getGoodsTypeId() {
		return goodsTypeId;
	}


	/**
	 * @param goodsTypeId the goodsTypeId to set
	 */
	public void setGoodsTypeId(Integer goodsTypeId) {
		this.goodsTypeId = goodsTypeId;
	}


	/**
	 * @return the goodsDesc
	 */
	public Integer getGoodsDesc() {
		return goodsDesc;
	}


	/**
	 * @param goodsDesc the goodsDesc to set
	 */
	public void setGoodsDesc(Integer goodsDesc) {
		this.goodsDesc = goodsDesc;
	}


	public Integer getGoodsId() {
        return goodsId;
    }

   
    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

   
    public String getGoodsName() {
        return goodsName;
    }

   
    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

   
    public String getGoodsShortname() {
        return goodsShortname;
    }

    
    public void setGoodsShortname(String goodsShortname) {
        this.goodsShortname = goodsShortname;
    }

    
    public String getGoodsImageOne() {
        return goodsImageOne ;
    }

    
    public void setGoodsImageOne(String goodsImageOne) {
        this.goodsImageOne =goodsImageOne;
    }

   
    public String getGoodsImageTwo() {
        return goodsImageTwo;
    }

    
    public void setGoodsImageTwo(String goodsImageTwo) {
        this.goodsImageTwo = goodsImageTwo;
    }

    
    public String getGoodsImageThree() {
        return goodsImageThree;
    }

    
    public void setGoodsImageThree(String goodsImageThree) {
        this.goodsImageThree = goodsImageThree;
    }

    
    public String getGoodsImageFour() {
        return goodsImageFour;
    }

    
    public void setGoodsImageFour(String goodsImageFour) {
        this.goodsImageFour = goodsImageFour;
    }

    
    public String getGoodsImage1Five() {
        return goodsImage1Five;
    }

   
    public void setGoodsImage1Five(String goodsImage1Five) {
        this.goodsImage1Five = goodsImage1Five;
    }

    
    public Double getGoodsPrice() {
        return goodsPrice;
    }

    
    public void setGoodsPrice(Double goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    
    public Float getGoodsDiscount() {
        return goodsDiscount;
    }

    public void setGoodsDiscount(Float goodsDiscount) {
        this.goodsDiscount = goodsDiscount;
    }

   
    public Double getGoodsDiscountPrice() {
        return goodsDiscountPrice;
    }

    
    public void setGoodsDiscountPrice(Double goodsDiscountPrice) {
        this.goodsDiscountPrice = goodsDiscountPrice;
    }

    
    public Integer getGoodsCounts() {
        return goodsCounts;
    }

   
    public void setGoodsCounts(Integer goodsCounts) {
        this.goodsCounts = goodsCounts;
    }


	/**
	 * @return the goodsStatus
	 */
	public Integer getGoodsStatus() {
		return goodsStatus;
	}


	/**
	 * @param goodsStatus the goodsStatus to set
	 */
	public void setGoodsStatus(Integer goodsStatus) {
		this.goodsStatus = goodsStatus;
	}



    
   
    
}
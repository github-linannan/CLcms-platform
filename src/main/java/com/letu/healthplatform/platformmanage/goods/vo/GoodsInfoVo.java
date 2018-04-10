/**
 * 乐土精准医疗
 */
package com.letu.healthplatform.platformmanage.goods.vo;

/**
 * @author dzb
 * @date 2017年12月26日 下午4:30:03
 * @version 1.0
 * @description  商品和商品详情
 */
public class GoodsInfoVo {
	

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


    /**价格**/
    private Double goodsPrice;

    /**打折**/
    private Float goodsDiscount;

    /**打折后价格**/
    private Double goodsDiscountPrice;

    /**库存**/
    private Integer goodsCounts;
    
    //2017/12/01 添加字段值	
    /**产品类型**/
    private  Integer goodsTypeId;	
    /**排序字段**/
    private  Integer  goodsDesc;	
    /**商品状态**/
    private Integer goodsStatus;
    
    /**详情**/
    private String  goodsinfoDetails;
    
    /**类型名称**/
    private String goodstypeName;

  
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


	/**
	 * @return the goodsinfoDetails
	 */
	public String getGoodsinfoDetails() {
		return goodsinfoDetails;
	}


	/**
	 * @param goodsinfoDetails the goodsinfoDetails to set
	 */
	public void setGoodsinfoDetails(String goodsinfoDetails) {
		this.goodsinfoDetails = goodsinfoDetails;
	}


	/**
	 * @return the goodsImageOne
	 */
	public String getGoodsImageOne() {
		return goodsImageOne;
	}


	/**
	 * @param goodsImageOne the goodsImageOne to set
	 */
	public void setGoodsImageOne(String goodsImageOne) {
		this.goodsImageOne = goodsImageOne;
	}


	/**
	 * @return the goodsImageTwo
	 */
	public String getGoodsImageTwo() {
		return goodsImageTwo;
	}


	/**
	 * @param goodsImageTwo the goodsImageTwo to set
	 */
	public void setGoodsImageTwo(String goodsImageTwo) {
		this.goodsImageTwo = goodsImageTwo;
	}


	/**
	 * @return the goodsImageThree
	 */
	public String getGoodsImageThree() {
		return goodsImageThree;
	}


	/**
	 * @param goodsImageThree the goodsImageThree to set
	 */
	public void setGoodsImageThree(String goodsImageThree) {
		this.goodsImageThree = goodsImageThree;
	}


	/**
	 * @return the goodsImageFour
	 */
	public String getGoodsImageFour() {
		return goodsImageFour;
	}


	/**
	 * @param goodsImageFour the goodsImageFour to set
	 */
	public void setGoodsImageFour(String goodsImageFour) {
		this.goodsImageFour = goodsImageFour;
	}


	/**
	 * @return the goodsImage1Five
	 */
	public String getGoodsImage1Five() {
		return goodsImage1Five;
	}


	/**
	 * @param goodsImage1Five the goodsImage1Five to set
	 */
	public void setGoodsImage1Five(String goodsImage1Five) {
		this.goodsImage1Five = goodsImage1Five;
	}


	/**
	 * @return the goodstypeName
	 */
	public String getGoodstypeName() {
		return goodstypeName;
	}


	/**
	 * @param goodstypeName the goodstypeName to set
	 */
	public void setGoodstypeName(String goodstypeName) {
		this.goodstypeName = goodstypeName;
	}

   
	
}

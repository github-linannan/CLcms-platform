package com.letu.healthplatform.platformmanage.goods.model;

public class GoodInfoType {
	/**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_goods.goods_id
     *
     * @mbggenerated Fri Nov 24 17:29:17 CST 2017
     */
    private Integer goodsId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_goods.goods_name
     *
     * @mbggenerated Fri Nov 24 17:29:17 CST 2017
     */
    private String goodsName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_goods.goods_shortname
     *
     * @mbggenerated Fri Nov 24 17:29:17 CST 2017
     */
    private String goodsShortname;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_goods.goods_image_one
     *
     * @mbggenerated Fri Nov 24 17:29:17 CST 2017
     */
    private String goodsImageOne;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_goods.goods_image_two
     *
     * @mbggenerated Fri Nov 24 17:29:17 CST 2017
     */
    private String goodsImageTwo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_goods.goods_image_three
     *
     * @mbggenerated Fri Nov 24 17:29:17 CST 2017
     */
    private String goodsImageThree;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_goods.goods_image_four
     *
     * @mbggenerated Fri Nov 24 17:29:17 CST 2017
     */
    private String goodsImageFour;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_goods.goods_image1_five
     *
     * @mbggenerated Fri Nov 24 17:29:17 CST 2017
     */
    private String goodsImage1Five;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_goods.goods_price
     *
     * @mbggenerated Fri Nov 24 17:29:17 CST 2017
     */
    private Double goodsPrice;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_goods.goods_discount
     *
     * @mbggenerated Fri Nov 24 17:29:17 CST 2017
     */
    private Float goodsDiscount;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_goods.goods_discount_price
     *
     * @mbggenerated Fri Nov 24 17:29:17 CST 2017
     */
    private Double goodsDiscountPrice;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_goods.goods_typeId
     *
     * @mbggenerated Fri Nov 24 17:29:17 CST 2017
     */
    private Integer goodsTypeid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_goods.goods_counts
     *
     * @mbggenerated Fri Nov 24 17:29:17 CST 2017
     */
    private Integer goodsCounts;

    //=============================goodsInfo  start================
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_goods_info.goodsInfo_id
     *
     * @mbggenerated Fri Nov 24 17:29:17 CST 2017
     */
    private Integer goodsinfoId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_goods_info.goodsInfo_content
     *
     * @mbggenerated Fri Nov 24 17:29:17 CST 2017
     */
    private String goodsinfoContent;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_goods_info.goodsInfo_image_one
     *
     * @mbggenerated Fri Nov 24 17:29:17 CST 2017
     */
    private String goodsinfoImageOne;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_goods_info.goodsInfo_image_two
     *
     * @mbggenerated Fri Nov 24 17:29:17 CST 2017
     */
    private String goodsinfoImageTwo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_goods_info.goodsInfo_image_three
     *
     * @mbggenerated Fri Nov 24 17:29:17 CST 2017
     */
    private String goodsinfoImageThree;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_goods_info.goodsInfo_image_four
     *
     * @mbggenerated Fri Nov 24 17:29:17 CST 2017
     */
    private String goodsinfoImageFour;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_goods_info.goodsInfo_image_five
     *
     * @mbggenerated Fri Nov 24 17:29:17 CST 2017
     */
    private String goodsinfoImageFive;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_goods_info.goodsInfo_image_sixe
     *
     * @mbggenerated Fri Nov 24 17:29:17 CST 2017
     */
    private String goodsinfoImageSixe;
    //=============================goodsInfo  end================
    //=============================goods type start==============
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_goods_type.goodstype_id
     *
     * @mbggenerated Fri Nov 24 17:29:17 CST 2017
     */
    private Integer goodstypeId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_goods_type.goodstype_name
     *
     * @mbggenerated Fri Nov 24 17:29:17 CST 2017
     */
    private String goodstypeName;
    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_goods_type.goodstype_id
     *
     * @return the value of t_goods_type.goodstype_id
     *
     * @mbggenerated Fri Nov 24 17:29:17 CST 2017
     */
    //=============================goods type end==============
    
     
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
		return goodsImageOne;
	}

	public void setGoodsImageOne(String goodsImageOne) {
		this.goodsImageOne = goodsImageOne;
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

	public Integer getGoodsTypeid() {
		return goodsTypeid;
	}

	public void setGoodsTypeid(Integer goodsTypeid) {
		this.goodsTypeid = goodsTypeid;
	}

	public Integer getGoodsCounts() {
		return goodsCounts;
	}

	public void setGoodsCounts(Integer goodsCounts) {
		this.goodsCounts = goodsCounts;
	}

	public Integer getGoodsinfoId() {
		return goodsinfoId;
	}

	public void setGoodsinfoId(Integer goodsinfoId) {
		this.goodsinfoId = goodsinfoId;
	}

	public String getGoodsinfoContent() {
		return goodsinfoContent;
	}

	public void setGoodsinfoContent(String goodsinfoContent) {
		this.goodsinfoContent = goodsinfoContent;
	}

	public String getGoodsinfoImageOne() {
		return goodsinfoImageOne;
	}

	public void setGoodsinfoImageOne(String goodsinfoImageOne) {
		this.goodsinfoImageOne = goodsinfoImageOne;
	}

	public String getGoodsinfoImageTwo() {
		return goodsinfoImageTwo;
	}

	public void setGoodsinfoImageTwo(String goodsinfoImageTwo) {
		this.goodsinfoImageTwo = goodsinfoImageTwo;
	}

	public String getGoodsinfoImageThree() {
		return goodsinfoImageThree;
	}

	public void setGoodsinfoImageThree(String goodsinfoImageThree) {
		this.goodsinfoImageThree = goodsinfoImageThree;
	}

	public String getGoodsinfoImageFour() {
		return goodsinfoImageFour;
	}

	public void setGoodsinfoImageFour(String goodsinfoImageFour) {
		this.goodsinfoImageFour = goodsinfoImageFour;
	}

	public String getGoodsinfoImageFive() {
		return goodsinfoImageFive;
	}

	public void setGoodsinfoImageFive(String goodsinfoImageFive) {
		this.goodsinfoImageFive = goodsinfoImageFive;
	}

	public String getGoodsinfoImageSixe() {
		return goodsinfoImageSixe;
	}

	public void setGoodsinfoImageSixe(String goodsinfoImageSixe) {
		this.goodsinfoImageSixe = goodsinfoImageSixe;
	}

	public Integer getGoodstypeId() {
		return goodstypeId;
	}

	public void setGoodstypeId(Integer goodstypeId) {
		this.goodstypeId = goodstypeId;
	}

	public String getGoodstypeName() {
		return goodstypeName;
	}

	public void setGoodstypeName(String goodstypeName) {
		this.goodstypeName = goodstypeName;
	}


    
    
}

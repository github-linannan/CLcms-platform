package com.letu.healthplatform.platformmanage.goods.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

public class GoodsInfo implements Serializable{
	
	private static final long serialVersionUID = -5220266050520453006L;

    private Integer goodsinfoId;

    private Integer goodsId;

    private String goodsinfoContent;

    private String goodsinfoImageOne;

    private String goodsinfoImageTwo;

    private String goodsinfoImageThree;

    private String goodsinfoImageFour;

    private String goodsinfoImageFive;

    private String goodsinfoImageSixe;
    
    /**详情**/
    private String  goodsinfoDetails;

    public Integer getGoodsinfoId() {
        return goodsinfoId;
    }


    public void setGoodsinfoId(Integer goodsinfoId) {
        this.goodsinfoId = goodsinfoId;
    }


    public Integer getGoodsId() {
        return goodsId;
    }


    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
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

    
}
package com.letu.healthplatform.platformmanage.goods.model;


public class GoodsType {
   
    private Integer goodstypeId;

    
    private String goodstypeName;
    
    
    private Integer goodstypeStatus;
    
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


	/**
	 * @return the goodstypeStatus
	 */
	public Integer getGoodstypeStatus() {
		return goodstypeStatus;
	}


	/**
	 * @param goodstypeStatus the goodstypeStatus to set
	 */
	public void setGoodstypeStatus(Integer goodstypeStatus) {
		this.goodstypeStatus = goodstypeStatus;
	}
    
    
}
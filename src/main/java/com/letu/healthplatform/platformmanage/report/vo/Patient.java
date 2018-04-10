/**
 * 乐土精准医疗有限公司
 */
package com.letu.healthplatform.platformmanage.report.vo;


/**
 * @author dzb
 * @date 2018年1月26日 上午9:01:50
 * @version 1.0.0
 * @see
 */
public class Patient {
	
	private Integer id ;
	private String name;
	private String tel;
	private String item;
	private String tComeNumber;
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the tel
	 */
	public String getTel() {
		return tel;
	}
	/**
	 * @param tel the tel to set
	 */
	public void setTel(String tel) {
		this.tel = tel;
	}
	/**
	 * @return the item
	 */
	public String getItem() {
		return item;
	}
	/**
	 * @param item the item to set
	 */
	public void setItem(String item) {
		this.item = item;
	}

	
	/**
	 * @return the tComeNumber
	 */
	public String gettComeNumber() {
		return tComeNumber;
	}
	/**
	 * @param tComeNumber the tComeNumber to set
	 */
	public void settComeNumber(String tComeNumber) {
		this.tComeNumber = tComeNumber;
	}
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Patient [id=" + id + ", name=" + name + ", tel=" + tel + ", item=" + item + ", tComeNumber="
				+ tComeNumber + "]";
	}

	
	
	
	

}

/**
 * 乐土精准医疗有限公司
 */
package com.letu.healthplatform.platformmanage.report.vo;


import com.letu.healthplatform.platformmanage.excel.annotation.Excel;

/**
 * @author dzb
 * @date 2018年2月2日 下午2:36:36
 * @version 1.0.0
 * @see   导出Excel实体
 */
public class SampleInfoReportVo {
	
	@Excel(name = "类别(团体/个人)" ,width = 20)
    private String  tSiLable;
	/**省份**/
	@Excel(name = "省份" ,width = 20)
	private String tSiArea;
	
	@Excel(name = "公司来源" ,width = 20)
	private String tSiSource;
	/**来样编号**/
	@Excel(name = "来样编号" ,width = 20)
	private String tComeNumber;
	
	/**姓名**/
	@Excel(name = "姓名" ,width = 20)
	private String tUserName;
	/**性别**/
	@Excel(name = "性别" ,width = 20)
	private String  tUserSex;
	/**患者电话号码**/
	@Excel(name = "电话号码" ,width = 20)
	private String tUserTelphone;
	
	/**样本接收日期**/
	@Excel(name = "样本接收日期" ,width = 20)
	private String  tTestingTime;
	
	@Excel(name = "检测项目" ,width = 20)
	private  String   tSiProject;
	/**批次编号**/
	@Excel(name = "商务订单号" ,width = 20)
	private  String   tSiNumber;
	
	/**销售对接人**/
	@Excel(name = "销售对接人" ,width = 20)
	private String tSaleName;
	
	/**报告邮寄人电话**/
//	@Excel(name = "销售人电话" ,width = 20)
//	private String tTelephone;
	/**销售邮箱**/
	@Excel(name = "销售人员邮箱" ,width = 20)
	private String  tSaleEmail;
	
	/**报告完成时间**/
	@Excel(name = "报告完成时间" ,width = 20)
	private String tReportTime;
	
	/**快递单号**/
	@Excel(name = "快递单号" ,width = 20)
	private String tLogisticsNumber;
	
	
	@Excel(name = "负责人" ,width = 20)
    private  String  tPrincipal ;
	
	@Excel(name = "负责人电话" ,width = 20)
	private String   tPrincipalTelephone;
	
	

	/**
	 * @return the tSiLable
	 */
	public String gettSiLable() {
		return tSiLable;
	}


	/**
	 * @param tSiLable the tSiLable to set
	 */
	public void settSiLable(String tSiLable) {
		this.tSiLable = tSiLable;
	}


	/**
	 * @return the tSiArea
	 */
	public String gettSiArea() {
		return tSiArea;
	}


	/**
	 * @param tSiArea the tSiArea to set
	 */
	public void settSiArea(String tSiArea) {
		this.tSiArea = tSiArea;
	}


	/**
	 * @return the tSiSource
	 */
	public String gettSiSource() {
		return tSiSource;
	}


	/**
	 * @param tSiSource the tSiSource to set
	 */
	public void settSiSource(String tSiSource) {
		this.tSiSource = tSiSource;
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


	/**
	 * @return the tUserName
	 */
	public String gettUserName() {
		return tUserName;
	}


	/**
	 * @param tUserName the tUserName to set
	 */
	public void settUserName(String tUserName) {
		this.tUserName = tUserName;
	}


	/**
	 * @return the tUserSex
	 */
	public String gettUserSex() {
		return tUserSex;
	}


	/**
	 * @param tUserSex the tUserSex to set
	 */
	public void settUserSex(String tUserSex) {
		this.tUserSex = tUserSex;
	}


	/**
	 * @return the tUserTelphone
	 */
	public String gettUserTelphone() {
		return tUserTelphone;
	}


	/**
	 * @param tUserTelphone the tUserTelphone to set
	 */
	public void settUserTelphone(String tUserTelphone) {
		this.tUserTelphone = tUserTelphone;
	}


	/**
	 * @return the tTestingTime
	 */
	public String gettTestingTime() {
		return tTestingTime;
	}


	/**
	 * @param tTestingTime the tTestingTime to set
	 */
	public void settTestingTime(String tTestingTime) {
		this.tTestingTime = tTestingTime;
	}



	/**
	 * @return the tSiProject
	 */
	public String gettSiProject() {
		return tSiProject;
	}


	/**
	 * @param tSiProject the tSiProject to set
	 */
	public void settSiProject(String tSiProject) {
		this.tSiProject = tSiProject;
	}


	/**
	 * @return the tSiNumber
	 */
	public String gettSiNumber() {
		return tSiNumber;
	}


	/**
	 * @param tSiNumber the tSiNumber to set
	 */
	public void settSiNumber(String tSiNumber) {
		this.tSiNumber = tSiNumber;
	}


	/**
	 * @return the tSaleName
	 */
	public String gettSaleName() {
		return tSaleName;
	}


	/**
	 * @param tSaleName the tSaleName to set
	 */
	public void settSaleName(String tSaleName) {
		this.tSaleName = tSaleName;
	}


	/**
	 * @return the tReportTime
	 */
	public String gettReportTime() {
		return tReportTime;
	}


	/**
	 * @param tReportTime the tReportTime to set
	 */
	public void settReportTime(String tReportTime) {
		this.tReportTime = tReportTime;
	}


	/**
	 * @return the tLogisticsNumber
	 */
	public String gettLogisticsNumber() {
		return tLogisticsNumber;
	}


	/**
	 * @param tLogisticsNumber the tLogisticsNumber to set
	 */
	public void settLogisticsNumber(String tLogisticsNumber) {
		this.tLogisticsNumber = tLogisticsNumber;
	}


//	/**
//	 * @return the tTelephone
//	 */
//	public String gettTelephone() {
//		return tTelephone;
//	}
//
//
//	/**
//	 * @param tTelephone the tTelephone to set
//	 */
//	public void settTelephone(String tTelephone) {
//		this.tTelephone = tTelephone;
//	}

	/**
	 * @return the tPrincipal
	 */
	public String gettPrincipal() {
		return tPrincipal;
	}


	/**
	 * @return the tSaleEmail
	 */
	public String gettSaleEmail() {
		return tSaleEmail;
	}


	/**
	 * @param tSaleEmail the tSaleEmail to set
	 */
	public void settSaleEmail(String tSaleEmail) {
		this.tSaleEmail = tSaleEmail;
	}


	/**
	 * @param tPrincipal the tPrincipal to set
	 */
	public void settPrincipal(String tPrincipal) {
		this.tPrincipal = tPrincipal;
	}


	/**
	 * @return the tPrincipalTelephone
	 */
	public String gettPrincipalTelephone() {
		return tPrincipalTelephone;
	}


	/**
	 * @param tPrincipalTelephone the tPrincipalTelephone to set
	 */
	public void settPrincipalTelephone(String tPrincipalTelephone) {
		this.tPrincipalTelephone = tPrincipalTelephone;
	}


}

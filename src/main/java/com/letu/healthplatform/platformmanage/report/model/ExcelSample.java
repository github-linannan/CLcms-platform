package com.letu.healthplatform.platformmanage.report.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.letu.healthplatform.platformmanage.excel.annotation.Excel;

public class ExcelSample{
	
	
    /** 主键 */
    private Integer tId;

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
	private Date  tTestingTime;
	
	@Excel(name = "检测项目" ,width = 20)
	private  String   tSiProject;
	/**批次编号**/
	@Excel(name = "商务订单号" ,width = 20)
	private  String   tSiNumber;
	
	/**销售对接人**/
	@Excel(name = "销售对接人" ,width = 20)
	private String tSaleName;
	
//	/**报告邮寄人电话**/
	@Excel(name = "销售人电话" ,width = 20)
	private String tTelephone;
	
	/**销售邮箱**/
	@Excel(name = "销售人员邮箱" ,width = 20)
	private String  tSaleEmail;
	
	/**报告完成时间**/
	@Excel(name = "报告完成时间" ,width = 20)
	private Date tReportTime;
	
	/**快递单号**/
	@Excel(name = "快递单号" ,width = 20)
	private String tLogisticsNumber;
	
	
	@Excel(name = "负责人" ,width = 20)
    private  String  tPrincipal ;
	
	@Excel(name = "负责人电话" ,width = 20)
	private String   tPrincipalTelephone;

    /** 状态（1 未处理  2  处理） */
    private Integer tStatus;

    /** 创建时间 */
    private Date tCreateDate;
    
    /**导入批次的编号**/
    private String   tExportNumber;
    
    
    
    /**
	 * @return the tId
	 */
	public Integer gettId() {
		return tId;
	}

	/**
	 * @param tId the tId to set
	 */
	public void settId(Integer tId) {
		this.tId = tId;
	}

	public String gettSiLable() {
        return tSiLable;
    }

    public void settSiLable(String tSiLable) {
        this.tSiLable = tSiLable;
    }

    public String gettSiArea() {
        return tSiArea;
    }

    public void settSiArea(String tSiArea) {
        this.tSiArea = tSiArea;
    }

    public String gettSiSource() {
        return tSiSource;
    }

    public void settSiSource(String tSiSource) {
        this.tSiSource = tSiSource;
    }

    public String gettComeNumber() {
        return tComeNumber;
    }

    public void settComeNumber(String tComeNumber) {
        this.tComeNumber = tComeNumber;
    }

    public String gettUserName() {
        return tUserName;
    }

    public void settUserName(String tUserName) {
        this.tUserName = tUserName;
    }

    public String gettUserSex() {
        return tUserSex;
    }

    public void settUserSex(String tUserSex) {
        this.tUserSex = tUserSex;
    }

    public String gettUserTelphone() {
        return tUserTelphone;
    }

    public void settUserTelphone(String tUserTelphone) {
        this.tUserTelphone = tUserTelphone;
    }
    
    
    @JsonFormat(pattern = "yyyy-MM-dd ", timezone = "GMT+8")
    public Date gettTestingTime() {
        return tTestingTime;
    }

    public void settTestingTime(Date tTestingTime) {
        this.tTestingTime = tTestingTime;
    }

    public String gettSiProject() {
        return tSiProject;
    }

    public void settSiProject(String tSiProject) {
        this.tSiProject = tSiProject;
    }

    public String gettSiNumber() {
        return tSiNumber;
    }

    public void settSiNumber(String tSiNumber) {
        this.tSiNumber = tSiNumber;
    }

    public String gettSaleName() {
        return tSaleName;
    }

    public void settSaleName(String tSaleName) {
        this.tSaleName = tSaleName;
    }
    
    
    @JsonFormat(pattern = "yyyy-MM-dd ", timezone = "GMT+8")
    public Date gettReportTime() {
        return tReportTime;
    }

    public void settReportTime(Date tReportTime) {
        this.tReportTime = tReportTime;
    }

    public String gettLogisticsNumber() {
        return tLogisticsNumber;
    }

    public void settLogisticsNumber(String tLogisticsNumber) {
        this.tLogisticsNumber = tLogisticsNumber;
    }

    public String gettTelephone() {
        return tTelephone;
    }

    public void settTelephone(String tTelephone) {
        this.tTelephone = tTelephone;
   }

    public Integer gettStatus() {
        return tStatus;
    }

    public void settStatus(Integer tStatus) {
        this.tStatus = tStatus;
    }
    @JsonFormat(pattern = "yyyy-MM-dd ", timezone = "GMT+8")
    public Date gettCreateDate() {
        return tCreateDate;
    }

    public void settCreateDate(Date tCreateDate) {
        this.tCreateDate = tCreateDate;
    }
    

    /**
	 * @return the tExportNumber
	 */
	public String gettExportNumber() {
		return tExportNumber;
	}

	/**
	 * @param tExportNumber the tExportNumber to set
	 */
	public void settExportNumber(String tExportNumber) {
		this.tExportNumber = tExportNumber;
	}

	
	

    /**
	 * @return the tPrincipal
	 */
	public String gettPrincipal() {
		return tPrincipal;
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


}
package com.letu.healthplatform.platformmanage.report.service;

import java.util.List;

import com.letu.healthplatform.platformmanage.report.model.ExcelSample;

public interface ExcelSampleService { 
	
	Object findExcelSamplePage(ExcelSample record,int page,int pageSize);
	
	Object findExcelSample(String tId);
	
	Object insertSelective(ExcelSample record);
	
	Object updateByPrimaryKeySelective(ExcelSample record);
	
	Object updateByStatus(ExcelSample record);
	
	Object deleteByPrimaryKey(String tIds);
	
	Object  insertBatch(List<ExcelSample> list);
	
}
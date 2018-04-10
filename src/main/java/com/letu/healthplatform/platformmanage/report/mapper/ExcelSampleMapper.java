package com.letu.healthplatform.platformmanage.report.mapper;

import java.util.List;

import com.letu.healthplatform.platformmanage.report.model.ExcelSample;

public interface ExcelSampleMapper {
	
	int deleteByPrimaryKey(Integer tId);

    int insert(ExcelSample record);

    int insertSelective(ExcelSample record);

    ExcelSample selectByPrimaryKey(Integer tId);

    int updateByPrimaryKeySelective(ExcelSample record);
    
    List<ExcelSample>  selectByParam(ExcelSample record);
    
    List<ExcelSample>   selectBySiNumber(List<String> list);
    
	int updateByStatus(ExcelSample record);
	
	int  insertBatch(List<ExcelSample> list);
    
}
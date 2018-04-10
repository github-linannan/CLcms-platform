package com.letu.healthplatform.platformmanage.report.mapper;

import java.util.List;
import java.util.Map;


import com.letu.healthplatform.platformmanage.report.model.SampleReport;

public interface SampleReportMapper {
    
    int deleteByPrimaryKey(Integer tId);

 
    int insert(SampleReport record);

    int insertSelective(SampleReport record);

 
    SampleReport selectByPrimaryKey(Integer tId);

 
    int updateByPrimaryKeySelective(SampleReport record);

   
    int updateByPrimaryKey(SampleReport record);
    
    
    int  updateBatchById(SampleReport record);
    
    List<Map<String,String>> selectByParam(SampleReport record);
    
    int insertBatch(List<SampleReport> list);
    
    int deleteByBatchId(SampleReport record);
    
    /***
     * 根据报告ID,查个人的相关信息
     * @param record
     * @return
     */
    List<Map<String,Object>>  selectReportById(SampleReport record);
    /***
     * 根据报告ID,查团队的相关信息  
     * @param record
     * @return
     */
    List<Map<String,Object>>  selectReportByAllId(SampleReport record);
    
    
}
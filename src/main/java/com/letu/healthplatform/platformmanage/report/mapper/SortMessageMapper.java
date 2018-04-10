package com.letu.healthplatform.platformmanage.report.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.letu.healthplatform.platformmanage.report.model.SampleReport;
import com.letu.healthplatform.platformmanage.report.model.SortMessage;
import com.letu.healthplatform.platformmanage.report.vo.SampleInfoReportVo;

public interface SortMessageMapper {
    
    int deleteByPrimaryKey(Integer tId);

 
    int insert(SortMessage record);

  
    int insertSelective(SortMessage record);

 
    SortMessage selectByPrimaryKey(Integer tId);

   
    int updateByPrimaryKeySelective(SortMessage record);

    
    int updateByPrimaryKey(SortMessage record);
    
    /***
     * 短信列表
     * @param record
     * @return
     */
    List<SortMessage>  selectByParam(SortMessage record);
    
    /***
     * 统计查询
     * @param record
     * @return
     */
    List<SortMessage>  queryByParam(Map<String,Object> map);
    
    
    List<SampleInfoReportVo> queryExportParam(Map<String,Object> map);
    
    List<SortMessage>  selectbyIds(@Param(value="ids")String ids);
    
    
    List<Map<String,Object>>  selectbyBatchIds(@Param(value="batchIds")String batchIds);
    
    
   int  updateBatchMessage(Map<String,Object> param);

   
   int  updateEmailStatus(@Param(value="batchIds")String batchIds);

   
   /***
    * 删除短信信息
    * @param 短信对象
    * @return
    */
   int  deleteBatchType(SortMessage record);

}
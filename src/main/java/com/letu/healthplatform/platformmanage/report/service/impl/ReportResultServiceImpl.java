package com.letu.healthplatform.platformmanage.report.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.letu.healthplatform.platformmanage.common.util.Rsp;
import com.letu.healthplatform.platformmanage.report.mapper.ReportResultMapper;
import com.letu.healthplatform.platformmanage.report.model.ReportResult;
import com.letu.healthplatform.platformmanage.report.service.ReportResultService;
/****
 * 
 * @author chenfei
 *
 */
@Service
public class ReportResultServiceImpl implements ReportResultService {

	
	@Autowired
	private ReportResultMapper  reportResultMapper;
	
	/***
	 * 查询报告结果
	 * @param body
	 * @return
	 */
	public Object  findReportBySampleNumber(String sampleNumber){
	  List<ReportResult>	list=reportResultMapper.selectBySampleNumber(sampleNumber);
	  return Rsp.succ("成功",list);
	}
	
	/***
	 * 批量新增报告结果
	 * @param body {}
	 * @return
	 */
	public Object  addReportBatch(String body){
	 List<ReportResult> reports=JSONObject.parseArray(body, ReportResult.class);
		
	  int i= reportResultMapper.insertBatch(reports);
		return Rsp.succ("保存成功");
	}
	
	/***
	 *修改
	 * @param report
	 * @return
	 */
	@Transactional
	public Object  updateReportBatch(String body,String sampleNumber){
		  int k=reportResultMapper.deleteByParamSampleNumber(sampleNumber);
		 List<ReportResult> reports=JSONObject.parseArray(body, ReportResult.class);
		  int i= reportResultMapper.insertBatch(reports);
	  if(i>0){
		  return Rsp.succ("修改成功");
	  }
	  return Rsp.fail("修改失败");
	}
	
	/***
	 * 新增
	 * @param report
	 * @return
	 */
	public Object  deleteReport(ReportResult report){
		  int i=reportResultMapper.deleteByPrimaryKey(report.getReportId());
		  if(i>0){
			  return Rsp.succ("删除成功");
		  }
		  return Rsp.fail("删除失败");
	}
}

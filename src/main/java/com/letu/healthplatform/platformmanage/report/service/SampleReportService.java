package com.letu.healthplatform.platformmanage.report.service;


import java.util.List;

import com.letu.healthplatform.platformmanage.report.model.SampleReport;

public interface SampleReportService {

	public Object  selectByParam(SampleReport   report, int currentPage, int pageSize);
	/***
	 * 添加 
	 * @param report
	 * @return
	 */
	public  boolean addSampleReport(SampleReport   report);
	public  boolean deleteSampleReport(String   id);
	public  boolean updateSampleReportStatus(SampleReport sampleReport);
	/***
	 * 报告确认完成
	 * @param sampleReport
	 * @return
	 */
	public  Object  confirmReportStatus(SampleReport sampleReport);
	
	/***
	 * 个人批量插入报告
	 * @param record
	 * @return
	 */
	Object insertBatch(List<SampleReport> list);
	

	/***
	 * 根据批次删除
	 * @param tBatchId
	 * @param tUserId
	 * @return
	 */
	Object deleteByBatchId(SampleReport record);
	   
}

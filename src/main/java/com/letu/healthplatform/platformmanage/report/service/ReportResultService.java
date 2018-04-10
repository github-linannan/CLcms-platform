package com.letu.healthplatform.platformmanage.report.service;

import com.letu.healthplatform.platformmanage.report.model.ReportResult;

public interface ReportResultService {
	/***
	 * 查询报告结果
	 * @param body
	 * @return
	 */
	public Object  findReportBySampleNumber(String sampleNumber);
	/***
	 * 新增报告
	 * @param body
	 * @return
	 */
	public Object  addReportBatch(String body);
	
	/***
	 *修改
	 * @param report
	 * @return
	 */
	public Object  updateReportBatch(String body,String sampleNumber);
	
	/***
	 * 新增
	 * @param report
	 * @return
	 */
	public Object  deleteReport(ReportResult report);
}

package com.letu.healthplatform.platformmanage.report.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.letu.healthplatform.platformmanage.report.model.ReportResult;
import com.letu.healthplatform.platformmanage.report.service.ReportResultService;

@RestController
@RequestMapping("/report")
public class ReportResultController {

	@Autowired
	private ReportResultService reportResultService;
	
	
	
	@GetMapping("/findReport")
	public Object findReportBySampleNumber(String sampleNumber){
	   return	reportResultService.findReportBySampleNumber(sampleNumber);
	}
	
	/***
	 * 批量新增
	 * @param body
	 * @return
	 */
	@PostMapping("/addReportBatch")
	public Object addReportBatch(String body){
	   return	reportResultService.addReportBatch(body);
	}
	
	/***
	 * 单个修改
	 * @param report
	 * @return
	 */
	@PostMapping("/updateReportBatch")
	public Object updateReportBatch(String body,String sampleNumber){
	 return 	reportResultService.updateReportBatch(body,sampleNumber);
	}
	/***
	 * 修改报告
	 * @param report
	 * @return
	 */
	@PostMapping("/deleteReport")
	public Object deleteReport(ReportResult  report){
		return reportResultService.deleteReport(report);
	}
	
}

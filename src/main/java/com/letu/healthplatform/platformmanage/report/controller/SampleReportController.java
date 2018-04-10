package com.letu.healthplatform.platformmanage.report.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.letu.healthplatform.platformmanage.common.util.Rsp;
import com.letu.healthplatform.platformmanage.report.model.SampleReport;
import com.letu.healthplatform.platformmanage.report.service.SampleReportService;

@RestController
@RequestMapping("/sampleReport")
public class SampleReportController {
	
	@Autowired
	private  SampleReportService  sampleReportService;

	@GetMapping(value="/selectByParam")
	public  Object  selectByParam(SampleReport sampleReport,@RequestParam(required = false,defaultValue="0")Integer page,@RequestParam(required = false,defaultValue="0")Integer pageSize){
	
		return sampleReportService.selectByParam(sampleReport, page, pageSize);
	}
	/***
	 * 修改报告 如添加 物流
	 * @param sampleReport
	 * @return
	 */
	@PostMapping(value="/updateSampleReportStatus")
	public  Object updateSampleReportStatus(SampleReport sampleReport){
		boolean b= sampleReportService.updateSampleReportStatus(sampleReport);
		if(b){
		return	Rsp.succ("修改成功");
		}else{
		return	Rsp.fail("失败");
		}
	}
	/***
	 * 报告确认完成
	 * @param sampleReport
	 * @return
	 */
	@PostMapping(value="/confirmReportStatus")
	public  Object  confirmReportStatus(SampleReport sampleReport){
		return sampleReportService.confirmReportStatus(sampleReport);
	}
	
}

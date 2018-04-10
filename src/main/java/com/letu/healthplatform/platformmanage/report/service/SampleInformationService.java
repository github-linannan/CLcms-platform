package com.letu.healthplatform.platformmanage.report.service;

import com.letu.healthplatform.platformmanage.report.model.SampleInformation;
import com.letu.healthplatform.platformmanage.report.vo.SampleInformationVo;

public interface SampleInformationService { 
	
	Object findSampleInformationPage(SampleInformationVo record,int page,int pageSize);
	
	Object findSampleInformation(String tId);
	
	Object insertSelective(SampleInformation record);
	
	Object updateByPrimaryKeySelective(SampleInformation record);
	
	Object deleteByPrimaryKey(String tIds);
	
}
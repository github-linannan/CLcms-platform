package com.letu.healthplatform.platformmanage.report.controller;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.letu.healthplatform.platformmanage.report.model.SampleInformation;
import com.letu.healthplatform.platformmanage.report.service.SampleInformationService;
import com.letu.healthplatform.platformmanage.report.vo.SampleInformationVo;

@RestController
@RequestMapping("/sampleInformation")
public class SampleInformationController {
	
	private static final Logger log = LogManager.getLogger(SampleInformationController.class);
	
	
	@Autowired
	private SampleInformationService SampleInformationService;
	
	@GetMapping(value="/findSampleInformationPage")
	public Object findSampleInformationPage(@ModelAttribute SampleInformationVo record,	@RequestParam(required=false,defaultValue="0") int page,
			@RequestParam(required=false,defaultValue="0") int pageSize){
	    return SampleInformationService.findSampleInformationPage(record,page,pageSize);
	}
	
	
	@GetMapping(value="/findSampleInformation/{tSiId}")
	public Object findSampleInformation(@PathVariable String  tSiId){
	     return SampleInformationService.findSampleInformation(tSiId);
	} 
	
	
	@PostMapping(value="/insert") public Object insert(@ModelAttribute SampleInformation record){
	 return SampleInformationService.insertSelective(record);
	}
	
	 @PostMapping(value="/update")
	 public Object update(@ModelAttribute SampleInformation record){
	  return SampleInformationService.updateByPrimaryKeySelective(record);
	}
	
	@GetMapping(value="/delete/{tSiId}")
	public Object delete(@PathVariable String  tSiId){
	  return SampleInformationService.deleteByPrimaryKey(tSiId);
	}
	

}

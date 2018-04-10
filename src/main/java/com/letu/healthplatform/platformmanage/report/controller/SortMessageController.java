package com.letu.healthplatform.platformmanage.report.controller;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alibaba.fastjson.JSON;
import com.letu.healthplatform.platformmanage.excel.ExcelExportUtil;
import com.letu.healthplatform.platformmanage.excel.ExcelImportUtil;
import com.letu.healthplatform.platformmanage.excel.code.ExcelPoiConstants;
import com.letu.healthplatform.platformmanage.excel.controller.MiniAbstractExcelView;
import com.letu.healthplatform.platformmanage.excel.entity.ExportParams;
import com.letu.healthplatform.platformmanage.excel.entity.ImportParams;
import com.letu.healthplatform.platformmanage.report.model.ExcelSample;
import com.letu.healthplatform.platformmanage.report.model.SortMessage;
import com.letu.healthplatform.platformmanage.report.service.SortMessageService;
import com.letu.healthplatform.platformmanage.report.vo.SampleInfoReportVo;

@RestController
@RequestMapping("/message")
public class SortMessageController extends MiniAbstractExcelView {

	
	private Logger log=LoggerFactory.getLogger(SortMessageController.class);
	
	@Autowired
	private SortMessageService  sortMessageService;
	
	@GetMapping(value="/selectByParam")
	public  Object  selectByParam(SortMessage sortMessage,@RequestParam(required = false,defaultValue="0")Integer page,@RequestParam(required = false,defaultValue="0")Integer pageSize){
		return sortMessageService.selectByParam(sortMessage, page, pageSize);
	}
	
	
	@GetMapping(value="/sendSortMessage")
	public Object sendSortMessage(String ids){
    
	return sortMessageService.sendSortMessage(ids);
	}
	/***
	 * 忽略短信发送
	 * @param ids
	 * @return
	 */
	@GetMapping(value="/ignoreSortMessage")
	public Object ignoreSortMessage(String ids){
		
		return sortMessageService.ignoreSortMessage(ids);
	}
	@GetMapping(value="/sendEmailMessage")
	public Object sendEmailMessage(String ids){
		
		return sortMessageService.sendEmailMessage(ids);
	}
	
	@GetMapping(value="/queryByParam")
	public  Object  queryByParam(@RequestParam(required = false)String tUserName,@RequestParam(required = false)String tBatchName,@RequestParam(required = false)String tSaleName,@RequestParam(required = false,defaultValue="0")Integer page,@RequestParam(required = false,defaultValue="0")Integer pageSize){
	   Map<String,Object> data=new HashMap<String,Object>();
	   if(StringUtils.isNotEmpty(tUserName)){
		   data.put("tUserName", tUserName);
	   }
	   if(StringUtils.isNotEmpty(tBatchName)){
		   data.put("tBatchName", tBatchName);
	   }
	   if(StringUtils.isNotEmpty(tSaleName)){
		   data.put("tSaleName", tSaleName);
	   }
		return sortMessageService.queryByParam(data, page, pageSize);
	}
		
	//导出数据
	@GetMapping(value="/excelExport")
	public  void  excelExport(@RequestParam(required = false)String tUserName,
			@RequestParam(required = false)String tBatchName,
			@RequestParam(required = false)String tSaleName,
			@RequestParam(required = false,defaultValue="0")Integer page,
			@RequestParam(required = false,defaultValue="0")Integer pageSize,
			HttpServletRequest request,HttpServletResponse response){
		Map<String,Object> data=new HashMap<String,Object>();
		   if(StringUtils.isNotEmpty(tUserName)){
			   data.put("tUserName", tUserName);
		   }
		   if(StringUtils.isNotEmpty(tBatchName)){
			   data.put("tBatchName", tBatchName);
		   }
		   if(StringUtils.isNotEmpty(tSaleName)){
			   data.put("tSaleName", tSaleName);
		   }
		Object sm = sortMessageService.queryExportParam(data, page, pageSize);
    	String  obj =JSON.toJSONString(sm);
    	//System.out.println(obj);
    	List<SampleInfoReportVo> list = JSON.parseArray(obj, SampleInfoReportVo.class);
		
    	Map<String,Object> modelMap =new HashMap<String,Object>();
		modelMap.put(ExcelPoiConstants.FILE_NAME,"样本信息");
		modelMap.put(ExcelPoiConstants.CLASS,SampleInfoReportVo.class);
//		modelMap.put(ExcelConstants.PARAMS,new ExportParams("测试信息表单", "导出人:"+ "张三",
//				"导出信息"));
		modelMap.put(ExcelPoiConstants.PARAMS,new ExportParams());
		modelMap.put(ExcelPoiConstants.DATA_LIST,list);
		try {
			renderMergedOutputModel(modelMap, request, response);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
		}
    	
	}
	
	
   @Override
   protected void renderMergedOutputModel(Map<String, Object> model,
		   HttpServletRequest request,HttpServletResponse response) throws Exception {
	   
       // 下载文件的默认名称
        String codedFileName = "临时文件";
        Workbook workbook = null;
        if(model.isEmpty()) {
        	  throw new RuntimeException("MODEL IS NULL");
        }
         workbook = ExcelExportUtil.exportExcel(
                (ExportParams) model.get(ExcelPoiConstants.PARAMS),
                (Class<?>) model.get(ExcelPoiConstants.CLASS),
                (Collection<?>) model.get(ExcelPoiConstants.DATA_LIST));
        if (model.containsKey(ExcelPoiConstants.FILE_NAME)) {
            codedFileName = (String) model.get(ExcelPoiConstants.FILE_NAME);
        }
        if (workbook instanceof HSSFWorkbook) {
            codedFileName += HSSF;
        } else {
            codedFileName += XSSF;
        }
        if (isIE(request)) {
            codedFileName = java.net.URLEncoder.encode(codedFileName, "UTF8");
        } else {
            codedFileName = new String(codedFileName.getBytes("UTF-8"), "ISO-8859-1");
        }
        // 告诉浏览器用什么软件可以打开此文件
        //response.setHeader("content-Type", "application/vnd.ms-excel");
        response.setHeader("content-disposition", "attachment;filename=" + codedFileName);
        ServletOutputStream out = response.getOutputStream();
        workbook.write(out);
        out.flush();
    }
   
   
   
   

	
	
}

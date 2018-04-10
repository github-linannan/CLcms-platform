package com.letu.healthplatform.platformmanage.report.controller;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import com.letu.healthplatform.platformmanage.common.code.Constants;
import com.letu.healthplatform.platformmanage.common.util.FileUtil;
import com.letu.healthplatform.platformmanage.common.util.Rsp;
import com.letu.healthplatform.platformmanage.common.util.TelphoneUtil;
import com.letu.healthplatform.platformmanage.excel.ExcelExportUtil;
import com.letu.healthplatform.platformmanage.excel.ExcelImportUtil;
import com.letu.healthplatform.platformmanage.excel.code.ExcelPoiConstants;
import com.letu.healthplatform.platformmanage.excel.controller.MiniAbstractExcelView;
import com.letu.healthplatform.platformmanage.excel.entity.ExportParams;
import com.letu.healthplatform.platformmanage.excel.entity.ImportParams;
import com.letu.healthplatform.platformmanage.report.model.ExcelSample;
import com.letu.healthplatform.platformmanage.report.service.ExcelSampleService;
import com.letu.healthplatform.platformmanage.report.vo.SampleInfoReportVo;

@RestController
@RequestMapping("/excelSample")
public class ExcelSampleController extends MiniAbstractExcelView {

     private static final Logger logger = LogManager.getLogger(ExcelSampleController.class);
	
	@Autowired
	private ExcelSampleService ExcelSampleService;
	
	@GetMapping(value="/findExcelSamplePage")
	public Object findDepartmentPage(@ModelAttribute ExcelSample record,
			@RequestParam(required=false,defaultValue="0") int page,
			@RequestParam(required=false,defaultValue="0") int pageSize){
	    return ExcelSampleService.findExcelSamplePage(record,page,pageSize);
	}
	
	
	@GetMapping(value="/findExcelSample/{tId}")
	public Object findExcelSample(@PathVariable String  tId){
	 return ExcelSampleService.findExcelSample(tId);
	}
	
	
	@PostMapping(value="/insert") 
	public Object insert(@ModelAttribute ExcelSample record){
	 return ExcelSampleService.insertSelective(record);
	}
	
	 @PostMapping(value="/update")
	 public Object update(@ModelAttribute ExcelSample record){
	  return ExcelSampleService.updateByPrimaryKeySelective(record);
	}
	
	@GetMapping(value="/delete/{tId}")
	public Object delete(@PathVariable String  tId){
	  return ExcelSampleService.deleteByPrimaryKey(tId);
	}
	
	
	@PostMapping(value="/updateByStatus")
	 public Object updateByStatus(@ModelAttribute ExcelSample record){
		  return ExcelSampleService.updateByStatus(record);
	}
	
	
    //导入Excel  数据
	@PostMapping(value = "/importExcel")
	public Object importExcel(HttpServletRequest request, HttpServletResponse response) {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		List<ExcelSample> list=null;
		for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
			MultipartFile file = entity.getValue();// 获取上传文件对象
			ImportParams params = new ImportParams();
			//params.setTitleRows(2);
			params.setHeadRows(1);//表头行一
			try {
				List<ExcelSample> excelSample = ExcelImportUtil.
						importExcel(file.getInputStream(),ExcelSample.class,params);
				if(excelSample.size()>0&&
						StringUtils.isNoneBlank(excelSample.get(0).gettComeNumber())) {
					list = new LinkedList<ExcelSample>();
					long time = System.currentTimeMillis();
					for (ExcelSample es : excelSample) {
						ExcelSample  esEntity = new ExcelSample();
						esEntity.settSiLable(es.gettSiLable());
						esEntity.settSiArea(es.gettSiArea());
						esEntity.settSiSource(es.gettSiSource());
						esEntity.settComeNumber(es.gettComeNumber());
						esEntity.settUserName(es.gettUserName());
						esEntity.settUserSex(es.gettUserSex());
						esEntity.settUserTelphone(es.gettUserTelphone());
						esEntity.settTestingTime(es.gettTestingTime());
						esEntity.settSiProject(es.gettSiProject());
						esEntity.settSiNumber(es.gettSiNumber());
						esEntity.settSaleName(es.gettSaleName());
						esEntity.settReportTime(es.gettReportTime());
						esEntity.settLogisticsNumber(es.gettLogisticsNumber());
						//esEntity.settTelephone(es.gettTelephone());//销售人员电话
						esEntity.settStatus(1);//1 未处理 2 处理
						esEntity.settExportNumber(String.valueOf(time));//导入批次编号  以时间戳
						esEntity.settPrincipal(es.gettPrincipal());
						esEntity.settPrincipalTelephone(es.gettPrincipalTelephone());
						esEntity.settSaleEmail(es.gettSaleEmail());//销售人员邮箱
						list.add(esEntity);
					}
					
					return ExcelSampleService.insertBatch(list);
				}
				
			} catch (Exception e) {
				logger.error(e);
				return Rsp.fail(Constants.FAIL_UPLOAD);
			}finally{
				try {
					file.getInputStream().close();
				} catch (IOException e) {
					e.printStackTrace();
					return Rsp.fail(Constants.FAIL_UPLOAD);
				}
			}
		}
		return Rsp.fail(Constants.FAIL_UPLOAD);
	}
	
	
	//没有设置格式的
	@GetMapping(value="/downloadExcelTemplate")
	public  void  downloadExcelTemplate(HttpServletRequest request,HttpServletResponse response){
    	Map<String,Object> modelMap =new HashMap<String,Object>();
		modelMap.put(ExcelPoiConstants.FILE_NAME,"样本信息模板");
		modelMap.put(ExcelPoiConstants.CLASS,SampleInfoReportVo.class);
//		modelMap.put(ExcelConstants.PARAMS,new ExportParams("测试信息表单", "导出人:"+ "张三",
//				"导出信息"));
		modelMap.put(ExcelPoiConstants.PARAMS,new ExportParams());
		modelMap.put(ExcelPoiConstants.DATA_LIST,new ArrayList<>());
		try {
			renderMergedOutputModel(modelMap, request, response);
		} catch (Exception e) {
			e.printStackTrace();
			//System.out.println(e);
		}
    	
	}
	
	
	
  //以流的形式下载excel (window/linux都可以用)
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
	
   
   //模板形式下载    linux系统不能用此方法
   @GetMapping("/downloadTemplate")
   public void downLoadTemplate(HttpServletResponse response){
       FileUtil.uploadFile(response, "sample-2018.xls");
   }
	
	
}

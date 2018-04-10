package com.letu.healthplatform.platformmanage.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.letu.healthplatform.platformmanage.common.page.DataPage;
import com.letu.healthplatform.platformmanage.common.util.Rsp;
import com.letu.healthplatform.platformmanage.sys.mapper.ProvinceMapper;
import com.letu.healthplatform.platformmanage.sys.model.Province;
import com.letu.healthplatform.platformmanage.sys.service.AreaService;

@Service
@Transactional(propagation = Propagation.REQUIRED,
	isolation = Isolation.DEFAULT,
	timeout=36000,
rollbackFor={RuntimeException.class, Exception.class})
public class AreaServiceImpl implements AreaService{

	@Autowired
	private ProvinceMapper  provinceMapper;
	

	  public  PageInfo<Province>  findAreaPage(Province province,int currentPage,int pageSize){
		  if(currentPage>0&&pageSize>0)
		  PageHelper.startPage(currentPage, pageSize);
		 List<Province> list=provinceMapper.findAreaPage(province);
		 return new PageInfo<>(list);
	  }


	  
	  
}

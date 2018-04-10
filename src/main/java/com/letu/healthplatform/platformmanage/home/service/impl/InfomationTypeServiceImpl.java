package com.letu.healthplatform.platformmanage.home.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.letu.healthplatform.platformmanage.common.util.Rsp;
import com.letu.healthplatform.platformmanage.home.exception.HomeException;
import com.letu.healthplatform.platformmanage.home.mapper.InformationTypeMapper;
import com.letu.healthplatform.platformmanage.home.model.InformationType;
import com.letu.healthplatform.platformmanage.home.service.InfomationTypeService;

@Service
@Transactional
public class InfomationTypeServiceImpl implements  InfomationTypeService{
	private final static Logger log=LoggerFactory.getLogger(InfomationTypeServiceImpl.class);
	@Autowired
	private InformationTypeMapper  informationTypeMapper;
	@Override
	public Object selectByParam(InformationType information, int currentPage, int pageSize) {
	  if(currentPage>0&&pageSize>0)
		  PageHelper.startPage(currentPage, pageSize);
		 List<InformationType> allinfomationtype=informationTypeMapper.selectByParam(information);
		 if(currentPage>0&&pageSize>0){
		    return new PageInfo<>(allinfomationtype);
		 }
		 return Rsp.succ("查询成功", allinfomationtype);
	}

	@Override
	public Object insert(InformationType information) {
		try{
		int i= informationTypeMapper.insertSelective(information);
		   if(i>0)
		  return Rsp.succ("修改成功");
		   else
		 return Rsp.fail("修改失败"); 
		}catch(Exception e){
          new HomeException("修改产品类型失败");
          log.error("InfomationTypeServiceImpl 修改失败");
          return Rsp.fail("修改失败");
		}
	}

	@Override
	public Object updateByPrimaryKey(InformationType information) {
		try{
		   int i=informationTypeMapper.updateByPrimaryKeySelective(information);
		   if(i>0)
		  return Rsp.succ("修改成功");
		   else
				 return Rsp.fail("修改失败"); 
		}catch(Exception e){
          new HomeException("修改产品类型失败");
          log.error("InfomationTypeServiceImpl 修改失败");
          return Rsp.fail("修改失败");
		}
	}

}

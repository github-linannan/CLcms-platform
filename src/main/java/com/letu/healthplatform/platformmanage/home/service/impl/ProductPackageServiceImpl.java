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
import com.letu.healthplatform.platformmanage.home.mapper.ProductPackageMapper;
import com.letu.healthplatform.platformmanage.home.model.ProductPackage;
import com.letu.healthplatform.platformmanage.home.service.ProductPackageService;

@Service
@Transactional
public class ProductPackageServiceImpl implements ProductPackageService{

	
	private final static Logger log=LoggerFactory.getLogger(ProductPackageServiceImpl.class);
	
	@Autowired
	private ProductPackageMapper productPackageMapper;
	
	
	
	  public  Object  selectByParam(ProductPackage proPackage,int currentPage,int pageSize){
		  if(currentPage>0&&pageSize>0)
		  PageHelper.startPage(currentPage, pageSize);
		 List<ProductPackage> allPackage=productPackageMapper.selectByParam(proPackage);
		  if(currentPage>0&&pageSize>0)
		    return new PageInfo<>(allPackage);
		  else
			  return Rsp.succ("查询成功", allPackage);
	  }
	
	public Object  insert(ProductPackage proPackage){
		try{
			 int i=productPackageMapper.insertSelective(proPackage);
			 if(i>0)
			 return Rsp.succ("插入成功");
			  else
				   return Rsp.fail("修改失败"); 
		}catch(Exception e){
              new HomeException("保存套餐失败");
              log.error("productPackageService 保存失败");
              return Rsp.fail("插件失败");
		}
	}
	
	public Object updateByPrimaryKey(ProductPackage proPackage){
		try{
		  int i= productPackageMapper.updateByPrimaryKeySelective(proPackage);
		if(i>0)
		   return Rsp.succ("修改成功");
		else
		   return Rsp.fail("修改失败"); 
		}catch(Exception e){
          new HomeException("修改套餐失败");
          log.error("productPackageService 修改失败");
          return Rsp.fail("修改失败");
		}
	}
	
}

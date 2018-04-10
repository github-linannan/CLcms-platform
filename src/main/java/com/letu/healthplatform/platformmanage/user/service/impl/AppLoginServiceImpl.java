package com.letu.healthplatform.platformmanage.user.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.letu.healthplatform.platformmanage.common.util.Rsp;
import com.letu.healthplatform.platformmanage.user.exception.AppUserException;
import com.letu.healthplatform.platformmanage.user.mapper.AppLoginMapper;
import com.letu.healthplatform.platformmanage.user.model.AppLogin;
import com.letu.healthplatform.platformmanage.user.service.AppLoginService;

@Service
@Transactional
public class AppLoginServiceImpl  implements  AppLoginService {

	private static final  Logger  log=LoggerFactory.getLogger(AppLoginServiceImpl.class);
	
	@Autowired
	private AppLoginMapper  appLoginMapper;
	
	@Override
	public Object selectByParam(AppLogin appLogin, int currentPage, int pageSize) {
		 if(currentPage>0&&pageSize>0)
			  PageHelper.startPage(currentPage, pageSize);
			 List<AppLogin> allAppLogin=appLoginMapper.selectByParam(appLogin);
			 if(currentPage>0&&pageSize>0){
			    return new PageInfo<>(allAppLogin);
			 }
			 return Rsp.succ("查询成功", allAppLogin);
	}

	@Override
	public Object updateStatusByPrimaryKey(AppLogin appLogin) {
		try{
				if(appLogin==null||appLogin.getLoginId()<=0){
					 return Rsp.fail("修改失败"); 
				}
				
				String password=com.letu.healthplatform.platformmanage.common.util.MD5.encryptPassword(appLogin.getLoginPassword());
				appLogin.setLoginPassword(password);
				
			   int i=appLoginMapper.updateByPrimaryKeySelective(appLogin);
			   if(i>0)
			  return Rsp.succ("修改成功");
			   else
					 return Rsp.fail("修改失败"); 
			}catch(Exception e){
	          new AppUserException("修改前端登录用户类型失败");
	          log.error("appUserServiceServiceImpl 修改失败");
	          return Rsp.fail("修改失败");
			}
	}

}

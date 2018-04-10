package com.letu.healthplatform.platformmanage.user.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.letu.healthplatform.platformmanage.common.util.Rsp;
import com.letu.healthplatform.platformmanage.user.mapper.AppUserInfoMapper;
import com.letu.healthplatform.platformmanage.user.model.AppUserInfo;
import com.letu.healthplatform.platformmanage.user.service.AppUserInfoService;
@Service
@Transactional
public class AppUserInfoServiceImpl  implements AppUserInfoService {
	@Autowired
	private   AppUserInfoMapper  appUserInfoMapper;
	@Override
	public Object selectByParam(AppUserInfo appUserInfo) {
	
		Map<String,Object> userInfoAll=appUserInfoMapper.selectInfoByPrimaryKey(appUserInfo);
			
			 return Rsp.succ("查询成功", userInfoAll);
	}

}

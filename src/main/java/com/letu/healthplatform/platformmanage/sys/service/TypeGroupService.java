package com.letu.healthplatform.platformmanage.sys.service;

import com.letu.healthplatform.platformmanage.sys.model.TypeGroup;

public interface TypeGroupService { 
	
	Object findTypeGroupPage(TypeGroup record,int page,int pageSize);
	
	Object findTypeGroup(String tId);
	
	Object insertSelective(TypeGroup record);
	
	Object updateByPrimaryKeySelective(TypeGroup record);
	
	
	Object deleteByPrimaryKey(String tIds);
	
	Object   findAllTypeGroup(TypeGroup record);
}
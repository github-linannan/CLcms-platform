package com.letu.healthplatform.platformmanage.sys.mapper;

import java.util.List;

import com.letu.healthplatform.platformmanage.sys.model.LoginRole;

public interface LoginRoleMapper {
	
	List<LoginRole> selectByPrimaryKey(Integer tLoginId);
	
	int deleteByPrimaryKey(Integer tLoginId);
	
    int insert(LoginRole record);

    int insertSelective(LoginRole record);
    
    int insertLoginRoleBatch(List<LoginRole> list);
}
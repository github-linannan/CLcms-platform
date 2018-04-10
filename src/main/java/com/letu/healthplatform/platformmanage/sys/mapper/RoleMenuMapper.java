package com.letu.healthplatform.platformmanage.sys.mapper;


import java.util.List;

import com.letu.healthplatform.platformmanage.sys.model.RoleMenu;

public interface RoleMenuMapper {
	
	List<RoleMenu> selectByPrimaryKey(Integer tRoleId);
	
	int deleteByPrimaryKey(Integer tRoleId);
	
    int insert(RoleMenu record);

    int insertSelective(RoleMenu record);
    
    int insertRoleMenuBatch(List<RoleMenu> list);
}
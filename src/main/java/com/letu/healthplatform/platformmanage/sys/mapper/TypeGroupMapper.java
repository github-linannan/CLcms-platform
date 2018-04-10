package com.letu.healthplatform.platformmanage.sys.mapper;

import java.util.List;

import com.letu.healthplatform.platformmanage.sys.model.TypeGroup;

public interface TypeGroupMapper {
	
	List<TypeGroup> selectByParam(TypeGroup record);
	
    int deleteByPrimaryKey(Integer id);

    int insert(TypeGroup record);

    int insertSelective(TypeGroup record);

    TypeGroup selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TypeGroup record);

    int updateByPrimaryKey(TypeGroup record);
    
    List<String>  selectAllTypeGroup(TypeGroup record);
    
}
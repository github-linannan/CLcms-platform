package com.letu.healthplatform.platformmanage.report.mapper;

import java.util.List;

import com.letu.healthplatform.platformmanage.report.model.SiLogin;
import com.letu.healthplatform.platformmanage.report.model.SiUser;

public interface SiLoginMapper {
	
    int insert(SiLogin record);

    int insertSelective(SiLogin record);
    
    int deleteByPrimaryKey(Integer tId);
    
    List<SiLogin> selectByPrimaryKey(Integer tSiId);

    int  deleteBatch(List<SiLogin> list);
    
}
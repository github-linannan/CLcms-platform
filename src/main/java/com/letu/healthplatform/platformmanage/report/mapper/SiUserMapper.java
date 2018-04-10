package com.letu.healthplatform.platformmanage.report.mapper;

import java.util.List;

import com.letu.healthplatform.platformmanage.report.model.SiUser;

public interface SiUserMapper {
	
    int insert(SiUser record);

    int insertSelective(SiUser record);
    
    int deleteByPrimaryKey(Integer tId);
    
    int insertBatch(List<SiUser> list);
    
    List<SiUser> selectByPrimaryKey(Integer tSiId);
    
    int  deleteBatch(List<SiUser> list);
    
    int updateUserLogistics(SiUser record);

}
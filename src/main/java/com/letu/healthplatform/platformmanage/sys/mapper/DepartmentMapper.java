package com.letu.healthplatform.platformmanage.sys.mapper;

import java.util.List;
import java.util.Map;

import com.letu.healthplatform.platformmanage.sys.model.Department;
import com.letu.healthplatform.platformmanage.sys.vo.HospitalDepartment;

public interface DepartmentMapper {
	
    int deleteByPrimaryKey(String[] tIds);

    int insert(Department record);

    int insertSelective(Department record);

    Department selectByPrimaryKey(Integer tId);
    
    List<HospitalDepartment> selectByParam(HospitalDepartment record);

    int updateByPrimaryKeySelective(Department record);

    int updateByPrimaryKey(Department record);
    
    int updateByStatus(Map<String, Object> params);
    
    
}
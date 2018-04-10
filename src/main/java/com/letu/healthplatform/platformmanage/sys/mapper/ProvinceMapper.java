package com.letu.healthplatform.platformmanage.sys.mapper;

import java.util.List;

import com.letu.healthplatform.platformmanage.sys.model.Province;

public interface ProvinceMapper {
   
    int deleteByPrimaryKey(Integer provinceId);

    int insert(Province record);

    int insertSelective(Province record);
  
    Province selectByPrimaryKey(Integer provinceId);

    int updateByPrimaryKeySelective(Province record);

    int updateByPrimaryKey(Province record);
 
    List<Province> findAreaPage(Province record);
}
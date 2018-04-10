package com.letu.healthplatform.platformmanage.report.mapper;

import java.util.List;

import com.letu.healthplatform.platformmanage.report.model.SampleInformation;
import com.letu.healthplatform.platformmanage.report.vo.SampleInformationVo;

public interface SampleInformationMapper {
	
    int deleteByPrimaryKey(Integer tSiId);

    int insert(SampleInformation record);

    int insertSelective(SampleInformation record);
    
    List<SampleInformationVo> selectByParam(SampleInformationVo record);

    SampleInformation selectByPrimaryKey(Integer tSiId);

    int updateByPrimaryKeySelective(SampleInformation record);

    int updateByPrimaryKey(SampleInformation record);
}
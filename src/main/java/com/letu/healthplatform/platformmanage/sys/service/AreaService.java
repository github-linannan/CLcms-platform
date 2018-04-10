package com.letu.healthplatform.platformmanage.sys.service;

import com.github.pagehelper.PageInfo;
import com.letu.healthplatform.platformmanage.sys.model.Province;

public interface AreaService {

    public  PageInfo<Province>  findAreaPage(Province province,int currentPage,int pageSize);
}

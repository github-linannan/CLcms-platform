/**
 * 乐土精准医疗
 */
package com.letu.healthplatform.platformmanage.goods.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.letu.healthplatform.platformmanage.common.code.Constants;
import com.letu.healthplatform.platformmanage.common.util.Rsp;
import com.letu.healthplatform.platformmanage.goods.mapper.GoodsTypeMapper;
import com.letu.healthplatform.platformmanage.goods.model.GoodsType;
import com.letu.healthplatform.platformmanage.goods.service.GoodsTypeService;

/**
 * @author dzb
 * @date 2017年12月4日 下午4:18:47
 * @version 1.0
 * @description 
 */
@Service
@Transactional(propagation = Propagation.REQUIRED,
	isolation = Isolation.DEFAULT,
	timeout=36000,
	rollbackFor={RuntimeException.class, Exception.class})
public class GoodsTypeServiceImpl  implements GoodsTypeService{
	
	private static final Logger log = LoggerFactory.getLogger(GoodsTypeServiceImpl.class);

	
	@Autowired
	private GoodsTypeMapper goodsTypeMapper;
	

	public Object  findGoodsTypePage(GoodsType goodsType,int currentPage,int pageSize){
		 List<GoodsType> list=null;
		try{
			if(currentPage>0&&pageSize>0)
		    PageHelper.startPage(currentPage, pageSize);
		    list=goodsTypeMapper.selectGoodsTypeAll(goodsType);
		    if(currentPage>0&&pageSize>0){
		    	 return new PageInfo<>(list);
		    }
		}catch (Exception e) {
			e.printStackTrace();
			log.error(Constants.ERROR_FIND, e);
			return Rsp.fail(Constants.ERROR_FIND);
		}
		 return Rsp.succ(Constants.SUCCESS_FIND, list);
	}

	
	@Override
	public Object findGoodsType(String goodstypeId) {
		GoodsType  gt = null;
		if(StringUtils.isBlank(goodstypeId)){
			return Rsp.fail(Constants.DEFAULT_KEY_ISNULL);
		}
		try{
			gt =goodsTypeMapper.selectByPrimaryKey(Integer.valueOf(goodstypeId));
		}catch (Exception e) {
			e.printStackTrace();
			log.error(Constants.ERROR_FIND, e);
			return Rsp.fail(Constants.ERROR_FIND);
		}
		 return Rsp.succ(Constants.SUCCESS_FIND,gt);
	}
	

	@Override
	public Object insertSelective(GoodsType record) {
		try{
			int a = goodsTypeMapper.insertSelective(record);
			return Rsp.succ(Constants.SUCCESS_INSERT);
		}catch (Exception e) {
			e.printStackTrace();
			log.error(Constants.ERROR_INSERT, e);
			return Rsp.fail(Constants.ERROR_INSERT);
		}
	}

	
	@Override
	public Object updateByPrimaryKeySelective(GoodsType record) {
		int a =0;
		try{
			 a = goodsTypeMapper.updateByPrimaryKeySelective(record);
			 return Rsp.succ(Constants.SUCCESS_UPDATE);
		}catch (Exception e) {
		    e.printStackTrace();
		    log.error(Constants.ERROR_UPDATE,e);
		    return Rsp.fail(Constants.ERROR_UPDATE);
		}
	}

	
	@Override
	public Object deleteByPrimaryKey(String goodstypeIds) {
		if(StringUtils.isBlank(goodstypeIds)){
			return Rsp.fail(Constants.DEFAULT_KEY_ISNULL);
		}
		try{
			int a = goodsTypeMapper.deleteByPrimaryKey(goodstypeIds.split(","));
			return Rsp.succ(Constants.SUCCESS_DELETE);
		}catch (Exception e) {
			e.printStackTrace();
			log.error(Constants.ERROR_DELETE, e);
			return Rsp.succ(Constants.ERROR_DELETE);
		}
	}


	/* (non-Javadoc)
	 * @see com.letu.healthplatform.platformmanage.goods.service.GoodsTypeService#updateByStatus(java.lang.String[])
	 */
	@Override
	public Object updateByStatus(String goodstypeIds) {
		if(StringUtils.isBlank(goodstypeIds)){
			return Rsp.fail(Constants.DEFAULT_KEY_ISNULL);
		}
		try{
			int a = goodsTypeMapper.updateByStatus(goodstypeIds.split(","));
			return Rsp.succ(Constants.SUCCESS_UPDATE);
		}catch (Exception e) {
			e.printStackTrace();
			log.error(Constants.SUCCESS_UPDATE, e);
			return Rsp.succ(Constants.SUCCESS_UPDATE);
		}
	}


}

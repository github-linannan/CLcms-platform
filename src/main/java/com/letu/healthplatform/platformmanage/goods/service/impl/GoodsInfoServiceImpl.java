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
import com.letu.healthplatform.platformmanage.goods.mapper.GoodsInfoMapper;
import com.letu.healthplatform.platformmanage.goods.model.GoodsInfo;
import com.letu.healthplatform.platformmanage.goods.service.GoodsInfoService;

/**
 * @author dongzhibo
 * @date 2017年11月29日 上午11:10:17
 * @version 1.0
 * @description
 */
@Service
@Transactional(propagation = Propagation.REQUIRED,
	isolation = Isolation.DEFAULT,
	timeout=36000,
	rollbackFor={RuntimeException.class, Exception.class})
public class GoodsInfoServiceImpl implements
  GoodsInfoService{
	
	private static final Logger log = LoggerFactory.getLogger(GoodsInfoService.class);
	
	@Autowired
	private GoodsInfoMapper goodsInfoMapper;

	/* (non-Javadoc)
	 * @see com.letu.healthplatform.platformmanage.goods.service.GoodsInfoService#deleteByPrimaryKey(java.lang.String)
	 */
	@Override
	public Object deleteByPrimaryKey(String goodsId) {
		if(StringUtils.isBlank(goodsId)){
			return Rsp.fail(Constants.DEFAULT_KEY_ISNULL);
		}
		try{
			int a = goodsInfoMapper.deleteByPrimaryKey(Integer.valueOf(goodsId));
			return Rsp.succ(Constants.SUCCESS_DELETE);
		}catch (Exception e) {
			e.printStackTrace();
			log.error(Constants.ERROR_DELETE, e);
			return Rsp.succ(Constants.ERROR_DELETE);
		}
	}

	/* (non-Javadoc)
	 * @see com.letu.healthplatform.platformmanage.goods.service.GoodsInfoService#insertSelective(com.letu.healthplatform.platformmanage.goods.model.GoodsInfo)
	 */
	@Override
	public Object insertSelective(GoodsInfo record) {
		try{
			int a = goodsInfoMapper.insertSelective(record);
			 return Rsp.succ(Constants.SUCCESS_INSERT);
		}catch (Exception e) {
			e.printStackTrace();
			log.error(Constants.ERROR_INSERT, e);
			return Rsp.fail(Constants.ERROR_INSERT);
		}
	}

	/* (non-Javadoc)
	 * @see com.letu.healthplatform.platformmanage.goods.service.GoodsInfoService#findGoodsInfo(java.lang.String)
	 */
	@Override
	public Object findGoodsInfo(String goodsId) {
		 GoodsInfo goods = null;
			if(StringUtils.isBlank(goodsId)){
				return Rsp.fail(Constants.DEFAULT_KEY_ISNULL);
			}
			try{
				goods =goodsInfoMapper.selectByPrimaryKey(Integer.valueOf(goodsId));
			}catch (Exception e) {
				e.printStackTrace();
				log.error(Constants.ERROR_FIND, e);
				return Rsp.fail(Constants.ERROR_FIND);
			}
			return Rsp.succ(Constants.SUCCESS_FIND,goods);
	}

	/* (non-Javadoc)
	 * @see com.letu.healthplatform.platformmanage.goods.service.GoodsInfoService#updateByPrimaryKeySelective(com.letu.healthplatform.platformmanage.goods.model.GoodsInfo)
	 */
	@Override
	public Object updateByPrimaryKeySelective(GoodsInfo record) {
		int a =0;
		try{
			 a = goodsInfoMapper.updateByPrimaryKeySelective(record);
			 return Rsp.succ(Constants.SUCCESS_UPDATE);
		}catch (Exception e) {
		    e.printStackTrace();
		    log.error(Constants.ERROR_UPDATE,e);
		    return Rsp.fail(Constants.ERROR_UPDATE);
		}
	}

	/* (non-Javadoc)
	 * @see com.letu.healthplatform.platformmanage.goods.service.GoodsInfoService#findGoodsInfoPage(com.letu.healthplatform.platformmanage.goods.model.GoodsInfo, int, int)
	 */
	@Override
	public Object findGoodsInfoPage(GoodsInfo goodsInfo, int page, int pageSize) {
		List<GoodsInfo> list=null;
		try{
			if(page>0&&pageSize>0)
		    PageHelper.startPage(page, pageSize);
		    list=goodsInfoMapper.selectByParam(goodsInfo);
		    if(page>0&&pageSize>0){
		    	 return new PageInfo<>(list);
		    }
		}catch (Exception e) {
			e.printStackTrace();
			log.error(Constants.ERROR_FIND, e);
			  return Rsp.fail(Constants.ERROR_FIND);
		}
		 return Rsp.succ(Constants.SUCCESS_FIND, list);
	}


	

	



	

}

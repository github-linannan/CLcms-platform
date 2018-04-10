package com.letu.healthplatform.platformmanage.goods.service.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.letu.healthplatform.platformmanage.common.code.Constants;
import com.letu.healthplatform.platformmanage.common.util.Arith;
import com.letu.healthplatform.platformmanage.common.util.Rsp;
import com.letu.healthplatform.platformmanage.goods.mapper.GoodsInfoMapper;
import com.letu.healthplatform.platformmanage.goods.mapper.GoodsMapper;
import com.letu.healthplatform.platformmanage.goods.model.Goods;
import com.letu.healthplatform.platformmanage.goods.model.GoodsInfo;
import com.letu.healthplatform.platformmanage.goods.model.GoodsType;
import com.letu.healthplatform.platformmanage.goods.service.GoodsService;
import com.letu.healthplatform.platformmanage.goods.vo.GoodsInfoVo;

@Service
@Transactional
public class GoodsServiceImpl implements  GoodsService{
	
	private static final Logger log = LoggerFactory.getLogger(GoodsServiceImpl.class);
	 
	@Autowired
	private GoodsMapper  goodsMapper;
	
	
	@Autowired
	private GoodsInfoMapper goodsInfoMapper;


	public  List<Goods>  findGoodsByIds(String ids){
		 List<Goods> list =null;
		try {
			list = goodsMapper.selectGoodsByIds(ids.split(","));
		} catch (Exception e) {
			e.printStackTrace();
			log.error("查询商品详情信息error:",e);
		}
		return list;
	}
	


	/* (non-Javadoc)
	 * @see com.letu.healthplatform.platformmanage.goods.service.GoodsService#findPages(java.lang.String)
	 */
	@Override
	public Object findGoodsPage(GoodsInfoVo goodsInfoVo,int currentPage,int pageSize) {
		List<GoodsInfoVo> list=null;
		try{
			if(currentPage>0&&pageSize>0)
		    PageHelper.startPage(currentPage, pageSize);
		    list=goodsMapper.selectByParam(goodsInfoVo);
		    if(currentPage>0&&pageSize>0){
		    	 return new PageInfo<>(list);
		    }
		}catch (Exception e) {
			e.printStackTrace();
			log.error(Constants.ERROR_FIND, e);
			return Rsp.succ(Constants.ERROR_FIND);
		}
		 return Rsp.succ(Constants.SUCCESS_FIND, list);
	}




	/* (non-Javadoc)
	 * @see com.letu.healthplatform.platformmanage.goods.service.GoodsService#deleteByPrimaryKey(java.lang.String)
	 */
	@Override
	public Object deleteByPrimaryKey(String goodsId) {
		if(StringUtils.isBlank(goodsId)){
			return Rsp.fail(Constants.DEFAULT_KEY_ISNULL);
		}
		try{
			int a = goodsMapper.deleteByPrimaryKey(Integer.valueOf(goodsId));
			return Rsp.succ(Constants.SUCCESS_DELETE);
		}catch (Exception e) {
			e.printStackTrace();
			log.error(Constants.ERROR_DELETE, e);
			return Rsp.fail(Constants.ERROR_DELETE);
		}
	}




	@Override
	public Object updateGoodsStatus(String goodsIds,int goodsStatus) {
		if(StringUtils.isBlank(goodsIds)&&StringUtils.isBlank(String.valueOf(goodsStatus))){
			return Rsp.fail(Constants.DEFAULT_KEY_ISNULL);
		}
		Map<String, Object> params =new HashMap<String, Object>();
		try {
			params.put("goodsStatus", goodsStatus);
			List<String> list = Arrays.asList(goodsIds.split(","));
			params.put("list", list);
			int a =goodsMapper.updateGoodsStatus(params);
			return Rsp.succ(Constants.SUCCESS_UPDATE);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(Constants.ERROR_UPDATE, e);
			return Rsp.fail(Constants.ERROR_UPDATE);
		}
	}





	/* (non-Javadoc)
	 * @see com.letu.healthplatform.platformmanage.goods.service.GoodsService#insertSelective(com.letu.healthplatform.platformmanage.goods.model.Goods)
	 */
	@Override
	public Object insertSelective(Goods record,GoodsInfo goodsInfo) {
		try{
			//折后价格
			record.setGoodsDiscountPrice(Arith.round(Arith.mul(record.getGoodsPrice(), record.getGoodsDiscount())));
			int a = goodsMapper.insertSelective(record);
			//log.info("商品主键的ID:"+record.getGoodsId());
			if(a>0){
				 goodsInfo.setGoodsId(record.getGoodsId());
				 int b =goodsInfoMapper.insert(goodsInfo);
				 if(b>0){
					 return Rsp.succ(Constants.SUCCESS_INSERT);
				 }
			}
		}catch (Exception e) {
			e.printStackTrace();
			log.error(Constants.ERROR_INSERT, e);
			return Rsp.fail(Constants.ERROR_INSERT);
		}
		return Rsp.fail(Constants.ERROR_INSERT);
	}






	/* (non-Javadoc)
	 * @see com.letu.healthplatform.platformmanage.goods.service.GoodsService#findGoods(java.lang.String)
	 */
	@Override
	public Object findGoods(String goodsId) {
		 GoodsInfoVo goods = null;
		if(StringUtils.isBlank(goodsId)){
			return Rsp.fail(Constants.DEFAULT_KEY_ISNULL);
		}
		try{
			goods =goodsMapper.selectByPrimaryKey(Integer.valueOf(goodsId));
		}catch (Exception e) {
			e.printStackTrace();
			log.error(Constants.ERROR_FIND, e);
			return Rsp.fail(Constants.ERROR_FIND);
		}
		 return Rsp.succ(Constants.SUCCESS_FIND,goods);
	}


	/* (non-Javadoc)
	 * @see com.letu.healthplatform.platformmanage.goods.service.GoodsService#updateByPrimaryKeySelective(com.letu.healthplatform.platformmanage.goods.model.Goods)
	 */
	@Override
	public Object updateByPrimaryKeySelective(Goods record,GoodsInfo goodsInfo) {
		int a =0;
		if(StringUtils.isBlank(String.valueOf(record.getGoodsId()))){
			return Rsp.fail(Constants.DEFAULT_KEY_ISNULL);
		}
		try{
			 //折后价格
			 record.setGoodsDiscountPrice(Arith.round(Arith.mul(record.getGoodsPrice(), record.getGoodsDiscount())));
			 a = goodsMapper.updateByPrimaryKeySelective(record);
			 if(a>0)
			 {
				 int b = goodsInfoMapper.updateByGoodsId(goodsInfo);
				 if(b>0){
					 return Rsp.succ(Constants.SUCCESS_UPDATE); 
				 }
			 }
		}catch (Exception e) {
		    e.printStackTrace();
		    log.error(Constants.ERROR_UPDATE,e);
		    return Rsp.fail(Constants.ERROR_UPDATE);
		}
		return Rsp.fail(Constants.ERROR_UPDATE);
	}


	
	

}

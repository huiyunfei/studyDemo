package com.example.esdemo.dao;


import com.example.esdemo.entity.TbMallGoods;
import com.example.esdemo.entity.TbMallSellInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface MallInfoMapper {

	/**
	 * 获取所有商家
	 * @return
	 */
	public List<TbMallSellInfo> listAllSell();

	/**
	 * 获取所有商品
	 * @return
	 */
	public List<TbMallGoods> listAllGoods();

	/**
	 *  统计商品月销量
	 * @param goodsId
	 * @return
	 */
	public Integer countMonthGoodsAmount(@Param("goodsId") Integer goodsId);

}

package com.example.esdemo.task;

import com.example.esdemo.dao.MallGoodsInfoRepository;
import com.example.esdemo.dao.MallInfoMapper;
import com.example.esdemo.dao.MallSellInfoRepository;
import com.example.esdemo.entity.MallGoodsInfo;
import com.example.esdemo.entity.MallSellInfo;
import com.example.esdemo.entity.TbMallGoods;
import com.example.esdemo.entity.TbMallSellInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by hui.yunfei@qq.com on 2019/8/20
 */
public class EsTask {


    private static Logger LOGGER = LoggerFactory.getLogger(EsTask.class);

    @Autowired
    private MallInfoMapper mallSellInfoMapper;

    @Autowired
    private MallSellInfoRepository mallSellInfoRepository;

    @Autowired
    private MallGoodsInfoRepository mallGoodsInfoRepository;

    /**
     * 商城-初始化商家
     */
    @RequestMapping(value="/initMallSellInfo")
    public void initMallSellInfo() {
        LOGGER.info("商城-初始化商家，跑批开始！");
        long start = System.currentTimeMillis();
        List<MallSellInfo> mallSellInfoList = new LinkedList<>();

        List<TbMallSellInfo> tbMallSellInfoList = mallSellInfoMapper.listAllSell();

        for (TbMallSellInfo tbMallSellInfo : tbMallSellInfoList) {
            MallSellInfo mallSellInfo = tbMallSellInfo.convert2MallSellInfo();

            mallSellInfoList.add(mallSellInfo);
        }

        if (tbMallSellInfoList.size() > 0) {
            mallSellInfoRepository.saveAll(mallSellInfoList);;
        }

        long end = System.currentTimeMillis();
        LOGGER.info("商城-初始化商家成功。。。。。。。。执行消耗时间： {}", (end - start));

    }

    /**
     * 商城-初始化商品
     */
    @RequestMapping(value="/initMallGoodsInfo")
    public void initMallGoodsInfo() {
        LOGGER.info("商城-初始化商品，跑批开始！");
        long start = System.currentTimeMillis();

        List<TbMallGoods> mallGoodsList = mallSellInfoMapper.listAllGoods();

        List<MallGoodsInfo> mallGoodsInfoList = new LinkedList<>();

        for (TbMallGoods mallGoods : mallGoodsList) {
            MallGoodsInfo mallGoodsInfo = mallGoods.convert2MallGoodsInfo();

            //商品折扣
            if (mallGoodsInfo.getGoodsSellPrice() != null && mallGoodsInfo.getGoodsMarketPrice() != null && mallGoodsInfo.getGoodsMarketPrice().doubleValue() > 0) {
                DecimalFormat decimalFormat = new DecimalFormat("0.####");
                String goodsDiscount = decimalFormat.format(mallGoodsInfo.getGoodsSellPrice().doubleValue() / mallGoodsInfo.getGoodsMarketPrice().doubleValue());

                mallGoodsInfo.setGoodsDiscount(new BigDecimal(goodsDiscount));
            }

            //商品月销量
            Integer monthGoodsAmount = mallSellInfoMapper.countMonthGoodsAmount(Integer.valueOf(mallGoodsInfo.getId()));
            mallGoodsInfo.setMonthGoodsAmount(monthGoodsAmount + mallGoods.getVirtualSalesCount());

            mallGoodsInfoList.add(mallGoodsInfo);
        }

        if (mallGoodsInfoList.size() > 0) {
            mallGoodsInfoRepository.saveAll(mallGoodsInfoList);
        }

        long end = System.currentTimeMillis();
        LOGGER.info("商城-初始化商家商品。。。。。。。。执行消耗时间： {}", (end - start));

    }
}








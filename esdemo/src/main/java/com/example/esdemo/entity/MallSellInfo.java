package com.example.esdemo.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Mapping;
import org.springframework.data.elasticsearch.annotations.Setting;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;

@Document(indexName = "mall", type = "sell")
@Mapping(mappingPath = "mall_sell_mapping.json")//设置mapping
@Setting(settingPath = "elasticsearch_setting.json")//设置setting
public class MallSellInfo implements Comparator<MallSellInfo>{

	@Id
	private String id;

	/**
	 * 商家名称
	 */
	private String sellName;

	/**
	 * 商家状态（0：正常，1：关闭）
	 */
	private String sellStatus;


	/**
	 * 冗余店长状态（0：正常，1：关闭），用于查询店长是否可用
	 */
	private String sellUserStatus;

	/**
	 * 多少起包邮
	 */
	private BigDecimal sellSendPrice;

	/**
	 * 邮费
	 */
	private BigDecimal postage;

	/**
	 *	商家分类标签
	 */
	private String label;

	/**
	 *  商家月销量
	 */
	private Integer monthOrderAmount;

    /**
     * 子数据：菜品列表
     */
    @Transient
    private List<MallGoodsInfo> goodsList;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSellName() {
		return sellName;
	}

	public void setSellName(String sellName) {
		this.sellName = sellName;
	}

	public String getSellStatus() {
		return sellStatus;
	}

	public void setSellStatus(String sellStatus) {
		this.sellStatus = sellStatus;
	}

	public String getSellUserStatus() {
		return sellUserStatus;
	}

	public void setSellUserStatus(String sellUserStatus) {
		this.sellUserStatus = sellUserStatus;
	}

	public BigDecimal getSellSendPrice() {
		return sellSendPrice;
	}

	public void setSellSendPrice(BigDecimal sellSendPrice) {
		this.sellSendPrice = sellSendPrice;
	}

	public BigDecimal getPostage() {
		return postage;
	}

	public void setPostage(BigDecimal postage) {
		this.postage = postage;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Integer getMonthOrderAmount() {
		return monthOrderAmount;
	}

	public void setMonthOrderAmount(Integer monthOrderAmount) {
		this.monthOrderAmount = monthOrderAmount;
	}

	public List<MallGoodsInfo> getGoodsList() {
		return goodsList;
	}

	public void setGoodsList(List<MallGoodsInfo> goodsList) {
		this.goodsList = goodsList;
	}

	@Override
	public int compare(MallSellInfo o1, MallSellInfo o2) {

		if(StringUtils.isEmpty(o1.getSellStatus()) || StringUtils.isEmpty(o2.getSellStatus())) {
			return -1;
		}

		if(Integer.valueOf(o1.getSellStatus()) > Integer.valueOf(o2.getSellStatus())){
			return 1;
		} else{
			return o1.getSellName().compareTo(o2.getSellName());
		}
	}
}

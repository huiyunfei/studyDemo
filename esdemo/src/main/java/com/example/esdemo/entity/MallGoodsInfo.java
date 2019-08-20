package com.example.esdemo.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Mapping;
import org.springframework.data.elasticsearch.annotations.Parent;
import org.springframework.data.elasticsearch.annotations.Setting;

import java.math.BigDecimal;
import java.util.Comparator;

@Document(indexName = "mall", type = "goods")
@Mapping(mappingPath = "mall_goods_mapping.json")//设置mapping
@Setting(settingPath = "elasticsearch_setting.json")//设置setting
public class MallGoodsInfo implements Comparator<MallGoodsInfo>{

	@Id
	private String id;

	/**
	 * 店铺id
	 */
    @Parent(type = "sell")
	private String parentId;

	/**
	 * 店铺名称
	 */
	private String parentName;
    
    /**
     * 商品名称
     */
    private String goodsName;
    
    /**
     * 销售价
     */
    private BigDecimal goodsSellPrice;
    
    /**
     * 市场价
     */
    private BigDecimal goodsMarketPrice;

	/**
	 * 商品折扣
	 */
	private BigDecimal goodsDiscount;
    
    /**
     * 商品图片
     */
    private String goodsImgUrlOne;
    
    /**
     * 商品上下架（0：上架  1：下架）
     */
    private String goodsStatus;

    /**
     * 审核状态(0:未审核 1:不通过 其它：通过)
     */
    private String examineStatus;
    
    /**
     * 0、已删除，1、未删除
     */
    private Integer isDelete;
    
    /**
     * 首页显示顺序
     */
    private Integer homeSequence;
    
    /**
     * 商家分类标签
    */
    private String sellLabel;
    
    /**
     * 商品月销量
     */
    private Integer monthGoodsAmount;
    
    /**
     * 商品库存
     */
    private Integer goodsNowNumber;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public BigDecimal getGoodsSellPrice() {
		return goodsSellPrice;
	}

	public void setGoodsSellPrice(BigDecimal goodsSellPrice) {
		this.goodsSellPrice = goodsSellPrice;
	}

	public BigDecimal getGoodsMarketPrice() {
		return goodsMarketPrice;
	}

	public void setGoodsMarketPrice(BigDecimal goodsMarketPrice) {
		this.goodsMarketPrice = goodsMarketPrice;
	}

	public String getGoodsImgUrlOne() {
		return goodsImgUrlOne;
	}

	public void setGoodsImgUrlOne(String goodsImgUrlOne) {
		this.goodsImgUrlOne = goodsImgUrlOne;
	}

	public String getGoodsStatus() {
		return goodsStatus;
	}

	public void setGoodsStatus(String goodsStatus) {
		this.goodsStatus = goodsStatus;
	}

	public String getExamineStatus() {
		return examineStatus;
	}

	public void setExamineStatus(String examineStatus) {
		this.examineStatus = examineStatus;
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	public Integer getHomeSequence() {
		return homeSequence;
	}

	public void setHomeSequence(Integer homeSequence) {
		this.homeSequence = homeSequence;
	}

	public String getSellLabel() {
		return sellLabel;
	}

	public void setSellLabel(String sellLabel) {
		this.sellLabel = sellLabel;
	}

	public Integer getMonthGoodsAmount() {
		return monthGoodsAmount;
	}

	public void setMonthGoodsAmount(Integer monthGoodsAmount) {
		this.monthGoodsAmount = monthGoodsAmount;
	}

	public Integer getGoodsNowNumber() {
		return goodsNowNumber;
	}

	public void setGoodsNowNumber(Integer goodsNowNumber) {
		this.goodsNowNumber = goodsNowNumber;
	}

	public BigDecimal getGoodsDiscount() {
		return goodsDiscount;
	}

	public void setGoodsDiscount(BigDecimal goodsDiscount) {
		this.goodsDiscount = goodsDiscount;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null){
			return false;
		}
		if (this == obj) {
			return true;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		MallGoodsInfo other = (MallGoodsInfo) obj;
		if (other.getId() == null) {
			return false;
		}
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)){
			return false;
		}
		return true;
	}
	
	@Override
    public int hashCode() {
//        MallGoodsInfo goods = (MallGoodsInfo) this;
        return id.hashCode();
    }

	@Override
	public int compare(MallGoodsInfo o1, MallGoodsInfo o2) {

		return o1.getHomeSequence() - o2.getHomeSequence();
	}

}

package com.example.esdemo.entity;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 商城-商品实体类
 */
public class TbMallGoods implements Serializable {
    private static final long serialVersionUID = -7988490698897769063L;

    private Integer id;
    private String goodsName;   //商品名称
    private String goodsCategory;   //商品类别
    private Integer sellId;     //绑定的店家id外键
    private BigDecimal goodsSellPrice;  //销售价
    private BigDecimal goodsMarketPrice;    //市场价
    private String goodsDescription;    //商品详情
    private String goodsImgUrlOne;  //商品图片1
    private String goodsImgUrlTwo;  //商品图片2
    private String goodsImgUrlThree;  //商品图片3
    private String goodsImgUrlFour;  //商品图片4
    private String goodsImgUrlFive;  //商品图片5
    private Long salesCount;    //商品销量
    private Integer goodsGrade; //商品评分
    private String goodsStatus; //商品上下架（0：上架  1：下架）
    private String examineStatus;   //审核状态(0:未审核 1:不通过 其它：通过)
    private Date createTime;
    private Date updateTime;
    private Integer goodsNowNumber; //商品库存
    private Integer isDelete; //是否删除(0:是    1:不是)
    private Integer hasSpec; //是否有规格：0、否，1、是
    private Integer homeSequence; //首页显示顺序
    private Integer virtualSalesCount; //虚拟销量

    private Integer parentId;
    private String parentName;
    private String sellLabel;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsCategory() {
        return goodsCategory;
    }

    public void setGoodsCategory(String goodsCategory) {
        this.goodsCategory = goodsCategory;
    }

    public Integer getSellId() {
        return sellId;
    }

    public void setSellId(Integer sellId) {
        this.sellId = sellId;
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

    public String getGoodsDescription() {
        return goodsDescription;
    }

    public void setGoodsDescription(String goodsDescription) {
        this.goodsDescription = goodsDescription;
    }

    public String getGoodsImgUrlOne() {
        return goodsImgUrlOne;
    }

    public void setGoodsImgUrlOne(String goodsImgUrlOne) {
        this.goodsImgUrlOne = goodsImgUrlOne;
    }

    public String getGoodsImgUrlTwo() {
        return goodsImgUrlTwo;
    }

    public void setGoodsImgUrlTwo(String goodsImgUrlTwo) {
        this.goodsImgUrlTwo = goodsImgUrlTwo;
    }

    public String getGoodsImgUrlThree() {
        return goodsImgUrlThree;
    }

    public void setGoodsImgUrlThree(String goodsImgUrlThree) {
        this.goodsImgUrlThree = goodsImgUrlThree;
    }

    public String getGoodsImgUrlFour() {
        return goodsImgUrlFour;
    }

    public void setGoodsImgUrlFour(String goodsImgUrlFour) {
        this.goodsImgUrlFour = goodsImgUrlFour;
    }

    public String getGoodsImgUrlFive() {
        return goodsImgUrlFive;
    }

    public void setGoodsImgUrlFive(String goodsImgUrlFive) {
        this.goodsImgUrlFive = goodsImgUrlFive;
    }

    public Long getSalesCount() {
        return salesCount;
    }

    public void setSalesCount(Long salesCount) {
        this.salesCount = salesCount;
    }

    public Integer getGoodsGrade() {
        return goodsGrade;
    }

    public void setGoodsGrade(Integer goodsGrade) {
        this.goodsGrade = goodsGrade;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getGoodsNowNumber() {
        return goodsNowNumber;
    }

    public void setGoodsNowNumber(Integer goodsNowNumber) {
        this.goodsNowNumber = goodsNowNumber;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Integer getHasSpec() {
        return hasSpec;
    }

    public void setHasSpec(Integer hasSpec) {
        this.hasSpec = hasSpec;
    }

    public Integer getHomeSequence() {
        return homeSequence;
    }

    public void setHomeSequence(Integer homeSequence) {
        this.homeSequence = homeSequence;
    }

    public Integer getVirtualSalesCount() {
        return virtualSalesCount;
    }

    public void setVirtualSalesCount(Integer virtualSalesCount) {
        this.virtualSalesCount = virtualSalesCount;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getSellLabel() {
        return sellLabel;
    }

    public void setSellLabel(String sellLabel) {
        this.sellLabel = sellLabel;
    }


    public MallGoodsInfo convert2MallGoodsInfo() {
        MallGoodsInfo mallGoodsInfo = new MallGoodsInfo();

        mallGoodsInfo.setId(this.getId().toString());
        mallGoodsInfo.setParentId(this.getParentId().toString());
        mallGoodsInfo.setParentName(this.getParentName());
        mallGoodsInfo.setGoodsName(this.getGoodsName());
        mallGoodsInfo.setGoodsSellPrice(this.getGoodsSellPrice());
        mallGoodsInfo.setGoodsMarketPrice(this.getGoodsMarketPrice());
        mallGoodsInfo.setGoodsImgUrlOne(this.getGoodsImgUrlOne());
        mallGoodsInfo.setGoodsStatus(this.getGoodsStatus());
        mallGoodsInfo.setExamineStatus(this.getExamineStatus());
        mallGoodsInfo.setIsDelete(this.getIsDelete());
        mallGoodsInfo.setHomeSequence(this.getHomeSequence());
        mallGoodsInfo.setSellLabel(this.getSellLabel());
        mallGoodsInfo.setGoodsNowNumber(this.getGoodsNowNumber());

        return mallGoodsInfo;
    }
}

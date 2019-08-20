package com.example.esdemo.entity;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 商城-店铺实体类
 */
public class TbMallSellInfo implements Serializable {
    private static final long serialVersionUID = 4240388957911257920L;

    private Integer id;

    /**
     * 商家名称
     */
    private String sellName;

    /**
     * 商家店址
     */
    private String sellAddress;

    /**
     * 发货地址
     */
    private String deliveryAddress;

    /**
     * 商家座机
     */
    private String sellCall;

    /**
     * 商家手机
     */
    private String sellPhone;

    /**
     * 商家状态（0：正常，1：关闭）
     */
    private String sellStatus;

    /**
     * 多少起包邮
     */
    private BigDecimal sellSendPrice;

    /**
     * 邮费
     */
    private BigDecimal postage;

    /**
     * 店长id
     */
    private Integer merchantAccountId;

    /**
     * 店家评分
     */
    private Double sellGrade;

    /**
     * 商户总销量
     */
    private int sellSalesVolume;

    /**
     * 店家好评率
     */
    private Double sellPraiseRate;

    /**
     * 商家图片
     */
    private String sellImgUrlOne;

    /**
     * 商家图片2
     */
    private String sellImgUrlTwo;

    /**
     * 商家图片3
     */
    private String sellImgUrlThree;

    /**
     * 佣金比例
     */
    private String commissionRate;

    /**
     * 店铺介绍
     */
    private String sellInTroduction;

    /**
     * 合同编号
     */
    private String contractNumber;

    /**
     * 合同到期时间
     */
    private Date contractExpiredTime;

    /**
     * 营业执照到期时间
     */
    private Date businessLicenseExpiredTime;

    /**
     * 经营许可证编号
     */
    private String businessPermitNumber;

    /**
     * 经营许可证到期时间
     */
    private Date businessPermitExpiredTime;

    /**
     * 主营产品
     */
    private String mainProducts;

    private Date createTime;

    private Date updateTime;

    /**
     * 商家虚拟月销量
     */
    private Integer virtualSalesCount;

    /**
     * 商家分类标签
     */
    private String label;


    private String sellUserStatus;
    private Integer monthOrderAmount;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSellName() {
        return sellName;
    }

    public void setSellName(String sellName) {
        this.sellName = sellName;
    }

    public String getSellAddress() {
        return sellAddress;
    }

    public void setSellAddress(String sellAddress) {
        this.sellAddress = sellAddress;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getSellCall() {
        return sellCall;
    }

    public void setSellCall(String sellCall) {
        this.sellCall = sellCall;
    }

    public String getSellPhone() {
        return sellPhone;
    }

    public void setSellPhone(String sellPhone) {
        this.sellPhone = sellPhone;
    }

    public String getSellStatus() {
        return sellStatus;
    }

    public void setSellStatus(String sellStatus) {
        this.sellStatus = sellStatus;
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

    public Integer getMerchantAccountId() {
        return merchantAccountId;
    }

    public void setMerchantAccountId(Integer merchantAccountId) {
        this.merchantAccountId = merchantAccountId;
    }

    public Double getSellGrade() {
        return sellGrade;
    }

    public void setSellGrade(Double sellGrade) {
        this.sellGrade = sellGrade;
    }

    public int getSellSalesVolume() {
        return sellSalesVolume;
    }

    public void setSellSalesVolume(int sellSalesVolume) {
        this.sellSalesVolume = sellSalesVolume;
    }

    public Double getSellPraiseRate() {
        return sellPraiseRate;
    }

    public void setSellPraiseRate(Double sellPraiseRate) {
        this.sellPraiseRate = sellPraiseRate;
    }

    public String getSellImgUrlOne() {
        return sellImgUrlOne;
    }

    public void setSellImgUrlOne(String sellImgUrlOne) {
        this.sellImgUrlOne = sellImgUrlOne;
    }

    public String getSellImgUrlTwo() {
        return sellImgUrlTwo;
    }

    public void setSellImgUrlTwo(String sellImgUrlTwo) {
        this.sellImgUrlTwo = sellImgUrlTwo;
    }

    public String getSellImgUrlThree() {
        return sellImgUrlThree;
    }

    public void setSellImgUrlThree(String sellImgUrlThree) {
        this.sellImgUrlThree = sellImgUrlThree;
    }

    public String getCommissionRate() {
        return commissionRate;
    }

    public void setCommissionRate(String commissionRate) {
        this.commissionRate = commissionRate;
    }

    public String getSellInTroduction() {
        return sellInTroduction;
    }

    public void setSellInTroduction(String sellInTroduction) {
        this.sellInTroduction = sellInTroduction;
    }

    public String getContractNumber() {
        return contractNumber;
    }

    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
    }

    public Date getContractExpiredTime() {
        return contractExpiredTime;
    }

    public void setContractExpiredTime(Date contractExpiredTime) {
        this.contractExpiredTime = contractExpiredTime;
    }

    public Date getBusinessLicenseExpiredTime() {
        return businessLicenseExpiredTime;
    }

    public void setBusinessLicenseExpiredTime(Date businessLicenseExpiredTime) {
        this.businessLicenseExpiredTime = businessLicenseExpiredTime;
    }

    public String getBusinessPermitNumber() {
        return businessPermitNumber;
    }

    public void setBusinessPermitNumber(String businessPermitNumber) {
        this.businessPermitNumber = businessPermitNumber;
    }

    public Date getBusinessPermitExpiredTime() {
        return businessPermitExpiredTime;
    }

    public void setBusinessPermitExpiredTime(Date businessPermitExpiredTime) {
        this.businessPermitExpiredTime = businessPermitExpiredTime;
    }

    public String getMainProducts() {
        return mainProducts;
    }

    public void setMainProducts(String mainProducts) {
        this.mainProducts = mainProducts;
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

    public Integer getVirtualSalesCount() {
        return virtualSalesCount;
    }

    public void setVirtualSalesCount(Integer virtualSalesCount) {
        this.virtualSalesCount = virtualSalesCount;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getSellUserStatus() {
        return sellUserStatus;
    }

    public void setSellUserStatus(String sellUserStatus) {
        this.sellUserStatus = sellUserStatus;
    }

    public Integer getMonthOrderAmount() {
        return monthOrderAmount;
    }

    public void setMonthOrderAmount(Integer monthOrderAmount) {
        this.monthOrderAmount = monthOrderAmount;
    }

    public MallSellInfo convert2MallSellInfo() {
        MallSellInfo mallSellInfo = new MallSellInfo();

        mallSellInfo.setId(this.getId().toString());
        mallSellInfo.setSellName(this.getSellName());
        mallSellInfo.setSellStatus(this.getSellStatus());
        mallSellInfo.setSellSendPrice(this.getSellSendPrice());
        mallSellInfo.setPostage(this.getPostage());
        mallSellInfo.setLabel(this.getLabel());
        mallSellInfo.setSellUserStatus(this.getSellUserStatus());
        mallSellInfo.setMonthOrderAmount(this.getMonthOrderAmount());

        return mallSellInfo;
    }

    @Override
    public String toString() {
        return "TbMallSellInfo{" +
                "id=" + id +
                ", sellName='" + sellName + '\'' +
                ", sellAddress='" + sellAddress + '\'' +
                ", deliveryAddress='" + deliveryAddress + '\'' +
                ", sellCall='" + sellCall + '\'' +
                ", sellPhone='" + sellPhone + '\'' +
                ", sellStatus='" + sellStatus + '\'' +
                ", sellSendPrice=" + sellSendPrice +
                ", postage=" + postage +
                ", merchantAccountId=" + merchantAccountId +
                ", sellGrade=" + sellGrade +
                ", sellSalesVolume=" + sellSalesVolume +
                ", sellPraiseRate=" + sellPraiseRate +
                ", sellImgUrlOne='" + sellImgUrlOne + '\'' +
                ", sellImgUrlTwo='" + sellImgUrlTwo + '\'' +
                ", sellImgUrlThree='" + sellImgUrlThree + '\'' +
                ", commissionRate='" + commissionRate + '\'' +
                ", sellInTroduction='" + sellInTroduction + '\'' +
                ", contractNumber='" + contractNumber + '\'' +
                ", contractExpiredTime=" + contractExpiredTime +
                ", businessLicenseExpiredTime=" + businessLicenseExpiredTime +
                ", businessPermitNumber='" + businessPermitNumber + '\'' +
                ", businessPermitExpiredTime=" + businessPermitExpiredTime +
                ", mainProducts='" + mainProducts + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", virtualSalesCount=" + virtualSalesCount +
                ", label='" + label + '\'' +
                ", sellUserStatus='" + sellUserStatus + '\'' +
                ", monthOrderAmount=" + monthOrderAmount +
                '}';
    }
}

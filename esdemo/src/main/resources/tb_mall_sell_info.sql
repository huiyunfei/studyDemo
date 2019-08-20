/*
Navicat MySQL Data Transfer

Source Server         : lifeplus_test
Source Server Version : 50725
Source Host           : rm-wz9fw4q99ky07rh0p7o.mysql.rds.aliyuncs.com:3306
Source Database       : lifeplus_test

Target Server Type    : MYSQL
Target Server Version : 50725
File Encoding         : 65001

Date: 2019-08-20 14:10:13
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tb_mall_sell_info
-- ----------------------------
DROP TABLE IF EXISTS `tb_mall_sell_info`;
CREATE TABLE `tb_mall_sell_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sellName` varchar(255) DEFAULT NULL COMMENT '商家名称',
  `sellAddress` varchar(255) DEFAULT NULL COMMENT '商家店址',
  `deliveryAddress` varchar(255) DEFAULT NULL COMMENT '发货地址',
  `sellCall` varchar(50) DEFAULT NULL COMMENT '商家座机',
  `sellPhone` varchar(50) DEFAULT NULL COMMENT '商家手机',
  `sellStatus` varchar(2) DEFAULT NULL COMMENT '商家状态（0：正常，1：关闭）',
  `sellSendPrice` decimal(50,2) DEFAULT NULL COMMENT '多少起包邮',
  `postage` decimal(50,2) DEFAULT NULL COMMENT '邮费',
  `sellSalesVolume` int(11) DEFAULT '0' COMMENT '商户总销量',
  `merchantAccountId` int(11) DEFAULT NULL COMMENT '店长id',
  `sellGrade` decimal(15,5) DEFAULT NULL COMMENT '店家评分',
  `sellPraiseRate` decimal(10,0) DEFAULT NULL COMMENT '店家好评率',
  `sellImgUrlOne` varchar(255) DEFAULT NULL COMMENT '商家图片',
  `sellImgUrlTwo` varchar(255) DEFAULT NULL COMMENT '商家图片2',
  `sellImgUrlThree` varchar(255) DEFAULT NULL COMMENT '商家图片3',
  `commissionRate` varchar(255) DEFAULT NULL COMMENT '佣金比例',
  `sellIntroduction` varchar(255) DEFAULT NULL COMMENT '店铺介绍',
  `contractNumber` varchar(255) DEFAULT NULL COMMENT '合同编号',
  `contractExpiredTime` datetime DEFAULT NULL COMMENT '合同到期时间',
  `businessLicenseName` varchar(255) DEFAULT NULL COMMENT '营业执照全称',
  `businessLicenseExpiredTime` datetime DEFAULT NULL COMMENT '营业执照到期时间',
  `businessPermitNumber` varchar(255) DEFAULT NULL COMMENT '经营许可证编号',
  `businessPermitExpiredTime` datetime DEFAULT NULL COMMENT '经营许可证到期时间',
  `mainProducts` varchar(255) DEFAULT NULL COMMENT '主营产品',
  `createTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updateTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `virtualSalesCount` int(11) DEFAULT '0' COMMENT '虚拟销量',
  `label` varchar(200) DEFAULT NULL COMMENT '商家分类标签',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='商家表';

-- ----------------------------
-- Records of tb_mall_sell_info
-- ----------------------------
INSERT INTO `tb_mall_sell_info` VALUES ('1', '测试商家1', '上海市科海大楼asda', '上海市', null, '18650123456', '0', '100.00', '0.01', '0', '1', '4.00000', '1', null, null, null, null, '一家有态度的奸商', 'D514511113212', '2019-08-06 00:00:00', 'asdasdasd', null, null, null, null, '2019-08-05 20:32:48', '2019-08-05 20:32:48', '12', '攀岩,峡谷,钓鱼');
INSERT INTO `tb_mall_sell_info` VALUES ('2', '测试商家2', '上海市绿地广场', '浙江省温州市', null, '13456000011', '0', '200.00', '18.00', '0', '2', '1.00000', null, null, null, null, null, '浙江温州皮革厂', 'D513123213212', null, null, null, null, null, null, '2019-08-05 20:36:39', '2019-08-05 20:36:39', '0', '登山');
INSERT INTO `tb_mall_sell_info` VALUES ('3', null, null, null, null, null, '1', null, null, null, '10', null, null, null, null, null, null, null, 'D514576735155', null, null, null, null, null, null, '2019-08-09 17:38:21', '2019-08-09 17:38:21', '0', null);
INSERT INTO `tb_mall_sell_info` VALUES ('4', null, null, null, null, null, '1', null, null, null, '11', null, null, null, null, null, null, null, 'D756798342377', null, null, null, null, null, null, '2019-08-09 17:41:07', '2019-08-09 17:41:07', '0', null);
INSERT INTO `tb_mall_sell_info` VALUES ('5', '爱买买不买滚', '上海', '贵州兴义阿萨德', '021-11001100', '18338388383', '1', '1212.00', '12.00', null, '12', '4.00000', '1', 'http://yghn-yunweibu.oss-cn-shenzhen.aliyuncs.com/undefined/test2.jpg', null, null, '12', '', 'D876343724456', '2019-08-10 00:00:00', 'adadadadad', '2019-08-07 00:00:00', '', '0000-00-00 00:00:00', '', '2019-08-09 17:59:42', '2019-08-09 17:59:42', '0', '丛林,峡谷,登山,越野,其他,攀岩');
INSERT INTO `tb_mall_sell_info` VALUES ('6', '大一统', '大一统', '大一统', '', '', '0', '1.00', '0.00', null, '13', null, null, '', null, null, '0', '按时发生的发生', '1111', '2020-06-22 16:00:00', '大一统', '0000-00-00 00:00:00', '', '2022-10-10 16:00:00', '大一统', '2019-08-15 18:25:16', '2019-08-15 18:25:16', '0', '丛林,登山,攀岩,峡谷,徒步,自行车穿越,其他');

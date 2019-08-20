/*
Navicat MySQL Data Transfer

Source Server         : lifeplus_test
Source Server Version : 50725
Source Host           : rm-wz9fw4q99ky07rh0p7o.mysql.rds.aliyuncs.com:3306
Source Database       : lifeplus_test

Target Server Type    : MYSQL
Target Server Version : 50725
File Encoding         : 65001

Date: 2019-08-20 14:09:59
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tb_mall_goods
-- ----------------------------
DROP TABLE IF EXISTS `tb_mall_goods`;
CREATE TABLE `tb_mall_goods` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `goodsName` varchar(255) DEFAULT NULL COMMENT '商品名称',
  `goodsCategory` int(11) DEFAULT NULL COMMENT '商品类别id',
  `sellId` int(11) DEFAULT NULL COMMENT '绑定的店家id外键',
  `goodsSellPrice` decimal(10,2) DEFAULT NULL COMMENT '销售价',
  `goodsMarketPrice` decimal(10,2) DEFAULT NULL COMMENT '市场价',
  `goodsDescription` varchar(1000) DEFAULT NULL COMMENT '商品详情',
  `goodsImgUrlOne` varchar(255) DEFAULT NULL COMMENT '商品图片1',
  `goodsImgUrlTwo` varchar(255) DEFAULT NULL COMMENT '商品图片2',
  `goodsImgUrlThree` varchar(255) DEFAULT NULL COMMENT '商品图片3',
  `goodsImgUrlFour` varchar(255) DEFAULT NULL COMMENT '商品图片4',
  `goodsImgUrlFive` varchar(255) DEFAULT NULL COMMENT '商品图片5',
  `salesCount` int(255) DEFAULT '0' COMMENT '商品销量',
  `goodsGrade` int(255) DEFAULT NULL COMMENT '商品评分',
  `goodsStatus` varchar(2) DEFAULT NULL COMMENT '商品上下架（0：上架  1：下架）',
  `examineStatus` varchar(2) DEFAULT '2' COMMENT '审核状态(0:未审核 1:不通过 其它：通过)',
  `createTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `goodsNowNumber` int(11) DEFAULT '0' COMMENT '商品库存 -999为无上限',
  `isDelete` int(2) DEFAULT '1' COMMENT '是否删除(0:是 1:不是)',
  `hasSpec` tinyint(2) DEFAULT NULL COMMENT '是否有规格：0、否，1、是',
  `homeSequence` int(11) DEFAULT NULL COMMENT '首页显示顺序',
  `virtualSalesCount` int(11) DEFAULT '0' COMMENT '虚拟销量',
  PRIMARY KEY (`id`),
  KEY `idx_sellId` (`sellId`),
  KEY `idx_createTime` (`createTime`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COMMENT='商品表';

-- ----------------------------
-- Records of tb_mall_goods
-- ----------------------------
INSERT INTO `tb_mall_goods` VALUES ('1', '攀岩商品189', '1', '1', '55.00', '99.00', null, null, null, null, null, null, '0', null, '0', '2', '2019-08-05 20:42:20', '2019-08-05 20:42:20', '-999', '1', '1', '1', '11');
INSERT INTO `tb_mall_goods` VALUES ('2', '攀岩商品2', '2', '1', '66.00', '120.00', 'http://yghn-yunweibu.oss-cn-shenzhen.aliyuncs.com/undefined/1565078444(1).png;http://yghn-yunweibu.oss-cn-shenzhen.aliyuncs.com/undefined/1565078444(1).png;', 'http://yghn-yunweibu.oss-cn-shenzhen.aliyuncs.com/undefined/1565078444(1).png', 'http://yghn-yunweibu.oss-cn-shenzhen.aliyuncs.com/undefined/1565078444(1).png', 'http://yghn-yunweibu.oss-cn-shenzhen.aliyuncs.com/undefined/1565078444(1).png', null, null, '0', null, '0', '2', '2019-08-04 12:43:05', '2019-08-04 12:43:05', '-999', '1', null, '2', '2');
INSERT INTO `tb_mall_goods` VALUES ('3', '登山商品1', '5', '2', '98.00', '150.00', '', 'http://yghn-yunweibu.oss-cn-shenzhen.aliyuncs.com/undefined/1565078444(1).png', null, null, null, null, '0', null, '0', '2', '2019-08-04 20:43:36', '2019-08-04 20:43:36', '-999', '1', null, '1', '0');
INSERT INTO `tb_mall_goods` VALUES ('4', '露营专用帐篷', '4', '2', '66.00', '88.00', 'http://yghn-yunweibu.oss-cn-shenzhen.aliyuncs.com/undefined/1565078444(1).png;', 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1565083473490&di=715252a094bb98f663cac231e32a7b06&imgtype=0&src=http%3A%2F%2Fbrup.shengri.cn%2Fgoods%2F2016%2F03%2FFtGCeAD680ZA53L-7OPRDJqOEMOi.jpg', null, null, null, null, '0', null, '0', '2', '2019-08-04 14:38:39', '2019-08-04 14:38:39', '-999', '1', null, null, '255');
INSERT INTO `tb_mall_goods` VALUES ('5', '攀岩商品3', '1', '5', '100.00', '159.00', 'http://yghn-yunweibu.oss-cn-shenzhen.aliyuncs.com/undefined/1565078444(1).png;http://yghn-yunweibu.oss-cn-shenzhen.aliyuncs.com/undefined/1565078444(1).png;', null, null, null, null, null, '0', null, '0', '2', '2019-08-06 06:37:35', '2019-08-06 06:37:35', '-999', '1', null, '4', '1');
INSERT INTO `tb_mall_goods` VALUES ('6', '攀岩商品4', '2', '1', '2000.00', '169.00', 'http://yghn-yunweibu.oss-cn-shenzhen.aliyuncs.com/undefined/1565078444(1).png;', 'http://yghn-yunweibu.oss-cn-shenzhen.aliyuncs.com/undefined/1565078444(1).png', null, null, null, null, '0', null, '0', '2', '2019-08-06 07:05:18', '2019-08-06 07:05:18', '-999', '1', null, '3', '1');
INSERT INTO `tb_mall_goods` VALUES ('7', '二号', null, '6', '1.00', '1.00', 'http://yghn-yunweibu.oss-cn-shenzhen.aliyuncs.com/undefined/1565078444(1).png;', null, null, null, null, null, '0', null, '0', '2', '2019-08-06 15:46:30', '2019-08-06 15:46:30', '-999', '1', null, null, '0');
INSERT INTO `tb_mall_goods` VALUES ('8', '一号', '2', '1', '0.01', '0.01', '', null, null, null, null, null, '0', null, '0', '2', '2019-08-12 18:26:57', '2019-08-12 18:26:57', '-999', '1', null, null, '0');
INSERT INTO `tb_mall_goods` VALUES ('9', '露营专用帐篷', '2', '1', '66.00', '88.00', 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1565084933419&di=d6e55f3efeb94ba502bb932aed05ce0a&imgtype=0&src=http%3A%2F%2Fimg003.hc360.cn%2Fm6%2FM04%2F8A%2FFB%2FwKhQolb7OziEOH4DAAAAAMDZVpQ800.jpg;', 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1565083473490&di=715252a094bb98f663cac231e32a7b06&imgtype=0&src=http%3A%2F%2Fbrup.shengri.cn%2Fgoods%2F2016%2F03%2FFtGCeAD680ZA53L-7OPRDJqOEMOi.jpg', null, null, null, null, '0', null, '0', '2', '2019-08-19 02:35:01', '2019-08-19 02:35:01', '0', '1', null, null, '255');
INSERT INTO `tb_mall_goods` VALUES ('10', '哈哈哈', '17', '1', '5.00', '5.00', '', null, null, null, null, null, '0', null, '0', '2', '2019-08-19 06:53:50', '2019-08-19 06:53:50', '20', '1', null, null, '100');
INSERT INTO `tb_mall_goods` VALUES ('11', '测试商品999', '1', '1', '0.01', '2.00', '', '', null, null, null, null, '0', null, '0', '2', '2019-08-19 16:08:31', '2019-08-19 16:08:31', '995', '1', '0', null, '888');

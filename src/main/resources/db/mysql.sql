/*
Date: 2015/09/14 
*/

use labassistant;

SET FOREIGN_KEY_CHECKS=0;
-- -----------------------------------
-- Table structure for `t_user` 用户表
-- -----------------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `userid` varchar(40) NOT NULL,
  `nickname` varchar(40) NOT NULL,
  `pwd` varchar(40) NOT NULL,
  `email` varchar(100) NOT NULL,
  `telno` varchar(20),
  `provinceid` int NOT NULL DEFAULT -1,
  `cityid` int NOT NULL DEFAULT -1,
  `collegeid` int NOT NULL DEFAULT -1,
  `majorid` int NOT NULL DEFAULT -1,
  `educationid` int NOT NULL DEFAULT -1,
  `titleid` int NOT NULL DEFAULT -1,
  `nstate` int NOT NULL DEFAULT 0,
  `nsource` int NOT NULL DEFAULT 0,
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO t_user VALUES ('4028c681494b994701494b99aba50000', 'admin', '25d55ad283aa400af464c76d713c07ad', '12345678@qq.com', '', 0, 0, 0, 0, 0, 0, 0, 0);


-- -----------------------------------------------
-- Table structure for `t_supplier` 供应商表
-- -----------------------------------------------
DROP TABLE IF EXISTS `t_supplier`;
CREATE TABLE `t_supplier` (
  `supplierid` int NOT NULL,
  `suppliername` varchar(100) NOT NULL,
  PRIMARY KEY (`supplierid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
-- ----------------------------
-- Records of t_supplier
-- ----------------------------
INSERT INTO t_supplier VALUES (1001, '海尔');


-- ---------------------------------------------------------
-- Table structure for `t_expinstructionsmain` 实验说明书主表
-- ---------------------------------------------------------
DROP TABLE IF EXISTS `t_expinstructionsmain`;
CREATE TABLE `t_expinstructionsmain` (
  `expinstructionid` varchar(40) NOT NULL,
  `experimentname` varchar(200),
  `experimentdesc` varchar(1000),
  `experimenttheory` text,
  `provideuser` varchar(40),
  `supplierid` varchar(40),
  `suppliername` varchar(40),
  `productnum` varchar(40),
  `expcategoryid` varchar(40),
  `expsubcategoryid` varchar(40),
  `createdate` date,
  `expversion` int,
  `allowdownload` int,
  `filterstr` varchar(400),
  `reviewcount` int,
  `downloadcount` int,
  PRIMARY KEY (`expinstructionid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- ----------------------------
-- Records of t_expinstructionsmain
-- ----------------------------
INSERT INTO t_expinstructionsmain VALUES ('4028c681494b994701494b99bab60000', 'ELISA检测血清TNF-a浓度', '我也不知道', '还是不知道', '', 1001, '海尔', 'hhd', 1, 11, '2015-09-16', 1, 1, '', 0, 0);
INSERT INTO t_expinstructionsmain VALUES ('4028c681494b994701494b99bab61111', 'ELISA检测血清TNF-b浓度', '我也不知道', '还是不知道', '', 1001, '海尔', 'hhd', 1, 11, '2015-09-16', 1, 1, '', 0, 20);
INSERT INTO t_expinstructionsmain VALUES ('4028c681494b994701494b99bab62222', 'ELISA检测血清TNF-c浓度', '我也不知道', '还是不知道', '', 1001, '海尔', 'hhd', 1, 11, '2015-09-16', 1, 1, '', 0, 5);

-- ----------------------------
-- Records of t_supplier
-- ----------------------------
INSERT INTO t_supplier VALUES (1001, '海尔');


-- -----------------------------------------------
-- Table structure for `t_expprocess` 实验步骤表
-- -----------------------------------------------
DROP TABLE IF EXISTS `t_expprocess`;
CREATE TABLE `t_expprocess` (
  `expstepid` varchar(40) NOT NULL,
  `expinstructionid` varchar(40) NOT NULL,
  `stepnum` int,
  `expsetpdesc` text,
  `expsteptime` decimal(5,2) DEFAULT 0,
  PRIMARY KEY (`expstepid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- ----------------------------
-- Records of t_expprocess
-- ----------------------------
INSERT INTO t_expprocess VALUES ('4028c681494b994701494b99bab70000', '4028c681494b994701494b99bab60000', 1, '用包被缓冲液溶解抗原，使抗原浓度为10-20 μg/ml，加100 μl/孔，4℃放置', 10);
INSERT INTO t_expprocess VALUES ('4028c681494b994701494b99bab70001', '4028c681494b994701494b99bab60000', 2, '弃去液体后，在吸水纸拍干，每孔加入300μl PBST 洗涤，振荡', 10);
INSERT INTO t_expprocess VALUES ('4028c681494b994701494b99bab70002', '4028c681494b994701494b99bab60000', 3, '弃去液体后，在吸水纸拍干，每孔加入300μl PBST 洗涤，振荡', 10);
INSERT INTO t_expprocess VALUES ('4028c681494b994701494b99bab70003', '4028c681494b994701494b99bab60000', 4, '弃去液体后，在吸水纸拍干，每孔加入300μl PBST 洗涤，振荡', 10);
INSERT INTO t_expprocess VALUES ('4028c681494b994701494b99bab70004', '4028c681494b994701494b99bab60000', 5, '每孔加入150 μl 1％ BSA 37℃封闭1 小时', 10);
INSERT INTO t_expprocess VALUES ('4028c681494b994701494b99bab70005', '4028c681494b994701494b99bab60000', 6, '弃去液体后，在吸水纸拍干，每孔加入300μl PBST 洗涤，振荡', 10);


-- -----------------------------------------------
-- Table structure for `t_myexpprocess` 我的实验步骤表
-- -----------------------------------------------
DROP TABLE IF EXISTS `t_myexpprocess`;
CREATE TABLE `t_myexpprocess` (
  `myexpstepid` varchar(40) NOT NULL,
  `myexpid` varchar(40) NOT NULL,
  `expinstructionid` varchar(40) NOT NULL,
  `expstepid` varchar(40) NOT NULL,
  `stepnum` int,
  `expsetpdesc` text,
  `expsteptime` decimal(5,2) DEFAULT 0,
  `isusetimer` int,
  `processmemo` varchar(500),
  PRIMARY KEY (`myexpstepid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- ----------------------------
-- Records of t_myexpprocess
-- ----------------------------
INSERT INTO t_myexpprocess VALUES ('4039c681494b994701494b99aba50000', '4039c681494b994701494b99aba51236', '4028c681494b994701494b99bab60000', '4028c681494b994701494b00bab60000', 0, '', 5, 1, '');
INSERT INTO t_myexpprocess VALUES ('4039c681494b994701494b99aba50010', '4039c681494b994701494b99aba51237', '4028c681494b994701494b99bab61111', '4028c681494b994701494b00bab60123', 0, '', 5, 1, '');


-- -----------------------------------------------
-- Table structure for `t_myexpmain` 我的实验主表
-- -----------------------------------------------
DROP TABLE IF EXISTS `t_myexpmain`;
CREATE TABLE `t_myexpmain` (
  `myexpid` varchar(40) NOT NULL,
  `userid` varchar(40),
  `createtime` datetime,
  `createyear` int,
  `createmonth` int,
  `finishtime` datetime,
  `expversion` int,
  `isreviewed` int,
  `iscreatereport` int,
  `isupload` int,
  `reportname` varchar(100),
  `reportlocation` varchar(1000),
  `reportserverpath` varchar(1000),
  `expstate` int,
  `expmeno` varchar(500),
  PRIMARY KEY (`myexpid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- ----------------------------
-- Records of t_myexpmain
-- ----------------------------
INSERT INTO t_myexpmain VALUES ('4039c681494b994701494b99aba51236', '4028c681494b994701494b99aba50000', '2015-09-16', 2015, 09, '2015-09-16', 1, 0, 0, 0, '', '', '', 2, '');
INSERT INTO t_myexpmain VALUES ('4039c681494b994701494b99aba51237', '4028c681494b994701494b99aba50000', '2015-09-16', 2015, 09, '2015-09-16', 1, 0, 0, 0, '', '', '', 0, '');


-- -----------------------------------------------
-- Table structure for `t_reagentlevelone` 试剂一级分类表
-- -----------------------------------------------
DROP TABLE IF EXISTS `t_reagentlevelone`;
CREATE TABLE `t_reagentlevelone` (
  `levelonesortid` int NOT NULL,
  `levelonesortname` varchar(200),
  PRIMARY KEY (`levelonesortid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- -----------------------------------------------
-- Table structure for `t_reagentleveltwo` 试剂二级分类表
-- -----------------------------------------------
DROP TABLE IF EXISTS `t_reagentleveltwo`;
CREATE TABLE `t_reagentleveltwo` (
  `leveltwosortid` int NOT NULL,
  `leveltwosortname` varchar(200),
  `levelonesortid` int,
  PRIMARY KEY (`leveltwosortid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- -----------------------------------------------
-- Table structure for `t_reagent` 试剂表
-- -----------------------------------------------
DROP TABLE IF EXISTS `t_reagent`;
CREATE TABLE `t_reagent` (
  `reagentid` int NOT NULL,
  `reagentname` varchar(100) NOT NULL,
  `reagentcommonname` varchar(500),
  `supplierid` int,
  `levelonesortid` int,
  `leveltwosortid` int,
  `originplace` varchar(50),
  `productno` varchar(50),
  `agents` varchar(50),
  `specification` varchar(50),
  `price` int,
  `chemicalname` varchar(100),
  `casno` varchar(50),
  `arrivaldate` date,
  `memo` varchar(500),
  PRIMARY KEY (`reagentid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
-- ----------------------------
-- Records of t_reagent
-- ----------------------------
INSERT INTO t_reagent VALUES (111, '洗涤液', '洗涤液', 1001, 11,1,'','','','',20,'','','2016-09-10','');
INSERT INTO t_reagent VALUES (112, '洗涤液1', '洗涤液1', 1001, 11,1,'','','','',20,'','','2016-09-10','');
INSERT INTO t_reagent VALUES (113, '洗涤液2', '洗涤液2', 1001, 11,1,'','','','',20,'','','2016-09-10','');


-- -----------------------------------------------
-- Table structure for `t_expreagent` 实验试剂表
-- -----------------------------------------------
DROP TABLE IF EXISTS `t_expreagent`;
CREATE TABLE `t_expreagent` (
  `expreagentid` varchar(32) NOT NULL,
  `expinstructionid` varchar(40),
  `reagentid` int,
  `reagentname` varchar(100),
  `reagentcommonname` varchar(500),
  `createMethod` varchar(1000),
  `reagentspec` varchar(50),
  `useamount` int,
  PRIMARY KEY (`expreagentid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
-- ----------------------------
-- Records of t_expreagent
-- ----------------------------
INSERT INTO t_expreagent VALUES ('4028c681494b994701494b99aba50000','4028c681494b994701494b99aba50000', 111, '洗涤液', '洗涤液', '洗涤液', '洗涤液', 50);
INSERT INTO t_expreagent VALUES ('4028c681494b994701494b99aba50001','4028c681494b994701494b99aba50000', 112, '洗涤液1', '洗涤液1', '洗涤液1', '洗涤液1', 500);
INSERT INTO t_expreagent VALUES ('4028c681494b994701494b99aba50002','4028c681494b994701494b99aba50000', 113, '洗涤液2', '洗涤液2', '洗涤液2', '洗涤液2', 60);

-- -----------------------------------------------
-- Table structure for `t_map` 地图相关表
-- -----------------------------------------------
DROP TABLE IF EXISTS `t_map`;
CREATE TABLE `t_map` (
  `mapid` varchar(32) NOT NULL,
  `userid` varchar(40) NOT NULL,
  `longitude` nvarchar(40),
  `latitude` nvarchar(40),
  `reagentname` varchar(100), 
  PRIMARY KEY (`mapid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
-- ----------------------------
-- Records of t_map
-- ----------------------------
INSERT INTO t_map VALUES ('2c9283f54fcf25eb014fcf2648530000', '4028c681494b994701494b99aba50000', '121.43657326690004', '31.18331495056','包被稀释液');
INSERT INTO t_map VALUES ('2c9283f54fcf29b3014fcf29cfdd0000', '2c9283f54fcf29b3014fcf2a3f530001', '121.43760323516177', '31.18471009979488','洗涤液');
INSERT INTO t_map VALUES ('2c9283f54fcf29b3014fcf2a915f0002', '2c9283f54fcf29b3014fcf2abba70003', '121.4375174044733', '31.184416385875302','封闭液');
INSERT INTO t_map VALUES ('2c9283f54fcf29b3014fcf2ac4bb0004', '2c9283f54fcf29b3014fcf2b23e50005', '121.44498467437076', '31.189335973819038','显色剂');
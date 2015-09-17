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
-- Table structure for `t_expprocess` 实验步骤表(未完)
-- -----------------------------------------------
DROP TABLE IF EXISTS `t_expprocess`;
CREATE TABLE `t_expprocess` (
  `expinstructionid` varchar(40) NOT NULL,
  `stepnum` int,
  `stepdetail` varchar(500),
  PRIMARY KEY (`expinstructionid`)
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


-- -----------------------------------------------
-- Table structure for `t_expreagent` 实验试剂表
-- -----------------------------------------------
DROP TABLE IF EXISTS `t_expreagent`;
CREATE TABLE `t_expreagent` (
  `expinstructionid` varchar(40) NOT NULL,
  `reagentid` int,
  `reagentname` varchar(100),
  `reagentcommonname` varchar(500),
  `createMathod` varchar(1000),
  `reagentspec` varchar(50),
  `useamount` int,
  PRIMARY KEY (`expinstructionid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


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
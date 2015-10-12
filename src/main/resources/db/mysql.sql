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
  `provinceid` varchar(40),
  `cityid` varchar(40),
  `collegeid` varchar(40),
  `majorid` varchar(40),
  `educationid` varchar(40),
  `titleid` varchar(40),
  `nstate` int NOT NULL DEFAULT 0,
  `nsource` int NOT NULL DEFAULT 0,
  `f_validcode` varchar(32),
  `f_timestamp` timestamp,
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO t_user VALUES ('4028c681494b994701494b99aba50000', 'admin', '25d55ad283aa400af464c76d713c07ad', '1416091730@qq.com', '', '', '', '', '', '', '', 0, 0, '', '2015-10-09 10:34:12');


-- -----------------------------------
-- Table structure for `t_teacher` 导师表
-- -----------------------------------
DROP TABLE IF EXISTS `t_teacher`;
CREATE TABLE `t_teacher` (
  `teacherid` varchar(40) NOT NULL,
  `nickname` varchar(40) NOT NULL,
  `pwd` varchar(40) NOT NULL,
  `email` varchar(100) NOT NULL,
  `telno` varchar(20),
  `provinceid` varchar(40),
  `cityid` varchar(40),
  `collegeid` varchar(40),
  `majorid` varchar(40),
  `educationid` varchar(40),
  `titleid` varchar(40),
  `nstate` int NOT NULL DEFAULT 0,
  `nsource` int NOT NULL DEFAULT 0,
  PRIMARY KEY (`teacherid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- -----------------------------------
-- Table structure for `t_teacher` 学生导师对应表
-- -----------------------------------
DROP TABLE IF EXISTS `t_userteachermap`;
CREATE TABLE `t_userteachermap` (
  `userteachermapid` varchar(40) NOT NULL,
  `userid` varchar(40) NOT NULL,
  `teacherid` varchar(40) NOT NULL,
  PRIMARY KEY (`userteachermapid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- -----------------------------------
-- Table structure for `t_province` 省份表
-- -----------------------------------
DROP TABLE IF EXISTS `t_province`;
CREATE TABLE `t_province` (
  `provinceid` varchar(40) NOT NULL,
  `provincename` varchar(50) NOT NULL,
  PRIMARY KEY (`provinceid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
-- ----------------------------
-- Records of t_province
-- ----------------------------
INSERT INTO t_province VALUES ('7728c681494b994701494b00aba50000', '浙江省');
INSERT INTO t_province VALUES ('7728c681494b994701494b00aba51111', '上海市');

-- -----------------------------------
-- Table structure for `t_city` 城市表
-- -----------------------------------
DROP TABLE IF EXISTS `t_city`;
CREATE TABLE `t_city` (
  `cityid` varchar(40) NOT NULL,
  `provinceid` varchar(40) NOT NULL,
  `cityname` varchar(50) NOT NULL,
  PRIMARY KEY (`cityid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
-- ----------------------------
-- Records of t_city
-- ----------------------------
INSERT INTO t_city VALUES ('7828c681494b994701494b00aba50000', '7728c681494b994701494b00aba50000', '温州市');
INSERT INTO t_city VALUES ('7828c681494b994701494b00aba51111', '7728c681494b994701494b00aba50000', '杭州市');

-- -----------------------------------
-- Table structure for `t_college` 院校表
-- -----------------------------------
DROP TABLE IF EXISTS `t_college`;
CREATE TABLE `t_college` (
  `collegeid` varchar(40) NOT NULL,
  `collegename` varchar(50) NOT NULL,
  `cityid` varchar(40) NOT NULL,
  `provinceid` varchar(40) NOT NULL,
  PRIMARY KEY (`collegeid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- -----------------------------------
-- Table structure for `t_major` 专业表
-- -----------------------------------
DROP TABLE IF EXISTS `t_major`;
CREATE TABLE `t_major` (
  `majorid` varchar(40) NOT NULL,
  `majorname` varchar(50) NOT NULL,
  PRIMARY KEY (`majorid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- -----------------------------------
-- Table structure for `t_education` 学历表
-- -----------------------------------
DROP TABLE IF EXISTS `t_education`;
CREATE TABLE `t_education` (
  `educationid` varchar(40) NOT NULL,
  `educationname` varchar(50) NOT NULL,
  PRIMARY KEY (`educationid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- -----------------------------------
-- Table structure for `t_title` 职称表
-- -----------------------------------
DROP TABLE IF EXISTS `t_title`;
CREATE TABLE `t_title` (
  `titleid` varchar(40) NOT NULL,
  `titlename` varchar(50) NOT NULL,
  PRIMARY KEY (`titleid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- -----------------------------------------------
-- Table structure for `t_supplier` 供应商表
-- -----------------------------------------------
DROP TABLE IF EXISTS `t_supplier`;
CREATE TABLE `t_supplier` (
  `supplierid` varchar(40) NOT NULL,
  `suppliername` varchar(100) NOT NULL,
  `suppliertype` int,
  `contacts` varchar(20),
  `telno` varchar(20),
  `mobilephone` varchar(20),
  `email` varchar(50),
  `address` varchar(200),
  PRIMARY KEY (`supplierid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
-- ----------------------------
-- Records of t_supplier
-- ----------------------------
INSERT INTO t_supplier (`supplierid`, `suppliername`, `suppliertype`, `contacts`, `telno`, `mobilephone`, `email`, `address`) VALUES ('4037d681494b994701494b99aba50001', '试剂供应商一', '0', '1', '1', '1', '1', '1');
INSERT INTO t_supplier (`supplierid`, `suppliername`, `suppliertype`, `contacts`, `telno`, `mobilephone`, `email`, `address`) VALUES ('4037d681494b994701494b99aba50002', '试剂供应商二', '0', '1', '1', '1', '1', '1');
INSERT INTO t_supplier (`supplierid`, `suppliername`, `suppliertype`, `contacts`, `telno`, `mobilephone`, `email`, `address`) VALUES ('4037d681494b994701494b99aba50003', '试剂供应商三', '0', '1', '1', '1', '1', '1');
INSERT INTO t_supplier (`supplierid`, `suppliername`, `suppliertype`, `contacts`, `telno`, `mobilephone`, `email`, `address`) VALUES ('4037d681494b994701494b99aba50004', '试剂供应商四', '0', '1', '1', '1', '1', '1');
INSERT INTO t_supplier (`supplierid`, `suppliername`, `suppliertype`, `contacts`, `telno`, `mobilephone`, `email`, `address`) VALUES ('4037d681494b994701494b99aba50005', '试剂供应商五', '0', '1', '1', '1', '1', '1');

INSERT INTO t_supplier (`supplierid`, `suppliername`, `suppliertype`, `contacts`, `telno`, `mobilephone`, `email`, `address`) VALUES ('4037d681494b994701494b99aba10001', '耗材供应商一', '1', '1', '1', '1', '1', '1');
INSERT INTO t_supplier (`supplierid`, `suppliername`, `suppliertype`, `contacts`, `telno`, `mobilephone`, `email`, `address`) VALUES ('4037d681494b994701494b99aba10002', '耗材供应商二', '1', '1', '1', '1', '1', '1');
INSERT INTO t_supplier (`supplierid`, `suppliername`, `suppliertype`, `contacts`, `telno`, `mobilephone`, `email`, `address`) VALUES ('4037d681494b994701494b99aba10003', '耗材供应商三', '1', '1', '1', '1', '1', '1');
INSERT INTO t_supplier (`supplierid`, `suppliername`, `suppliertype`, `contacts`, `telno`, `mobilephone`, `email`, `address`) VALUES ('4037d681494b994701494b99aba10004', '耗材供应商四', '1', '1', '1', '1', '11', '1');
INSERT INTO t_supplier (`supplierid`, `suppliername`, `suppliertype`, `contacts`, `telno`, `mobilephone`, `email`, `address`) VALUES ('4037d681494b994701494b99aba10005', '耗材供应商五', '1', '1', '1', '1', '1', '1');

INSERT INTO t_supplier (`supplierid`, `suppliername`, `suppliertype`, `contacts`, `telno`, `mobilephone`, `email`, `address`) VALUES ('4037d681494b994701494b99aba20001', '设备供应商一', '2', '1', '1', '1', '1', '1');
INSERT INTO t_supplier (`supplierid`, `suppliername`, `suppliertype`, `contacts`, `telno`, `mobilephone`, `email`, `address`) VALUES ('4037d681494b994701494b99aba20002', '设备供应商二', '2', '1', '1', '1', '1', '1');
INSERT INTO t_supplier (`supplierid`, `suppliername`, `suppliertype`, `contacts`, `telno`, `mobilephone`, `email`, `address`) VALUES ('4037d681494b994701494b99aba20003', '设备供应商三', '2', '1', '1', '1', '1', '1');


-- -----------------------------------------------
-- Table structure for `t_reagentlevelone` 试剂一级分类表
-- -----------------------------------------------
DROP TABLE IF EXISTS `t_reagentlevelone`;
CREATE TABLE `t_reagentlevelone` (
  `levelonesortid` varchar(40) NOT NULL,
  `levelonesortname` varchar(200),
  PRIMARY KEY (`levelonesortid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
-- ----------------------------
-- Records of t_reagentlevelone
-- ----------------------------
INSERT INTO t_reagentlevelone (`levelonesortid`, `levelonesortname`) VALUES ('b828bf3f-6e48-11e5-bc1f-002564e7234d', '抗体');
INSERT INTO t_reagentlevelone (`levelonesortid`, `levelonesortname`) VALUES ('e1336fd9-6e48-11e5-bc1f-002564e7234d', '化学试剂');
INSERT INTO t_reagentlevelone (`levelonesortid`, `levelonesortname`) VALUES ('f6ea7189-6e48-11e5-bc1f-002564e7234d', '生物化学');
INSERT INTO t_reagentlevelone (`levelonesortid`, `levelonesortname`) VALUES ('06995ee5-6e49-11e5-bc1f-002564e7234d', 'ELISA试剂盒');
INSERT INTO t_reagentlevelone (`levelonesortid`, `levelonesortname`) VALUES ('11486637-6e49-11e5-bc1f-002564e7234d', '蛋白质/抗原/多肽');
INSERT INTO t_reagentlevelone (`levelonesortid`, `levelonesortname`) VALUES ('16b53844-6e49-11e5-bc1f-002564e7234d', '蛋白检测');
INSERT INTO t_reagentlevelone (`levelonesortid`, `levelonesortname`) VALUES ('25f8065c-6e49-11e5-bc1f-002564e7234d', '蛋白分析');
INSERT INTO t_reagentlevelone (`levelonesortid`, `levelonesortname`) VALUES ('2f9a8766-6e49-11e5-bc1f-002564e7234d', '蛋白纯化');

-- -----------------------------------------------
-- Table structure for `t_reagentleveltwo` 试剂二级分类表
-- -----------------------------------------------
DROP TABLE IF EXISTS `t_reagentleveltwo`;
CREATE TABLE `t_reagentleveltwo` (
  `leveltwosortid` varchar(40) NOT NULL,
  `leveltwosortname` varchar(200),
  `levelonesortid` varchar(40),
  PRIMARY KEY (`leveltwosortid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
-- ----------------------------
-- Records of t_reagentleveltwo
-- ----------------------------
INSERT INTO t_reagentleveltwo (`leveltwosortid`, `leveltwosortname`, `levelonesortid`) VALUES ('49a48f98-6e49-11e5-bc1f-002564e7234d', '信号分子抗体', 'b828bf3f-6e48-11e5-bc1f-002564e7234d');
INSERT INTO t_reagentleveltwo (`leveltwosortid`, `leveltwosortname`, `levelonesortid`) VALUES ('7dcfcdf1-6e49-11e5-bc1f-002564e7234d', '结构蛋白抗体', 'b828bf3f-6e48-11e5-bc1f-002564e7234d');
INSERT INTO t_reagentleveltwo (`leveltwosortid`, `leveltwosortname`, `levelonesortid`) VALUES ('8356815d-6e49-11e5-bc1f-002564e7234d', '膜受体抗体', 'b828bf3f-6e48-11e5-bc1f-002564e7234d');
INSERT INTO t_reagentleveltwo (`leveltwosortid`, `leveltwosortname`, `levelonesortid`) VALUES ('8800c20e-6e49-11e5-bc1f-002564e7234d', '合成降解蛋白抗体', 'b828bf3f-6e48-11e5-bc1f-002564e7234d');
INSERT INTO t_reagentleveltwo (`leveltwosortid`, `leveltwosortname`, `levelonesortid`) VALUES ('8ca5057e-6e49-11e5-bc1f-002564e7234d', '裂解液对照', 'b828bf3f-6e48-11e5-bc1f-002564e7234d');
INSERT INTO t_reagentleveltwo (`leveltwosortid`, `leveltwosortname`, `levelonesortid`) VALUES ('bcdbc5da-6e49-11e5-bc1f-002564e7234d', 'EDTA', 'e1336fd9-6e48-11e5-bc1f-002564e7234d');
INSERT INTO t_reagentleveltwo (`leveltwosortid`, `leveltwosortname`, `levelonesortid`) VALUES ('c1fccda2-6e49-11e5-bc1f-002564e7234d', 'DTT', 'e1336fd9-6e48-11e5-bc1f-002564e7234d');
INSERT INTO t_reagentleveltwo (`leveltwosortid`, `leveltwosortname`, `levelonesortid`) VALUES ('c679d5dc-6e49-11e5-bc1f-002564e7234d', 'Tris', 'e1336fd9-6e48-11e5-bc1f-002564e7234d');
INSERT INTO t_reagentleveltwo (`leveltwosortid`, `leveltwosortname`, `levelonesortid`) VALUES ('ca8509b0-6e49-11e5-bc1f-002564e7234d', 'MOPS', 'e1336fd9-6e48-11e5-bc1f-002564e7234d');
INSERT INTO t_reagentleveltwo (`leveltwosortid`, `leveltwosortname`, `levelonesortid`) VALUES ('e57ce389-6e49-11e5-bc1f-002564e7234d', '螯合剂/变性剂', 'f6ea7189-6e48-11e5-bc1f-002564e7234d');
INSERT INTO t_reagentleveltwo (`leveltwosortid`, `leveltwosortname`, `levelonesortid`) VALUES ('ea6ebc7e-6e49-11e5-bc1f-002564e7234d', '半抗原反应', 'f6ea7189-6e48-11e5-bc1f-002564e7234d');
INSERT INTO t_reagentleveltwo (`leveltwosortid`, `leveltwosortname`, `levelonesortid`) VALUES ('ef070782-6e49-11e5-bc1f-002564e7234d', '药物标准品', 'f6ea7189-6e48-11e5-bc1f-002564e7234d');
INSERT INTO t_reagentleveltwo (`leveltwosortid`, `leveltwosortname`, `levelonesortid`) VALUES ('02efcf8b-6e4a-11e5-bc1f-002564e7234d', '人ELISA试剂盒', '06995ee5-6e49-11e5-bc1f-002564e7234d');
INSERT INTO t_reagentleveltwo (`leveltwosortid`, `leveltwosortname`, `levelonesortid`) VALUES ('0892e505-6e4a-11e5-bc1f-002564e7234d', '小鼠ELISA试剂盒', '06995ee5-6e49-11e5-bc1f-002564e7234d');
INSERT INTO t_reagentleveltwo (`leveltwosortid`, `leveltwosortname`, `levelonesortid`) VALUES ('1fc905ed-6e4a-11e5-bc1f-002564e7234d', '免疫球蛋白', '11486637-6e49-11e5-bc1f-002564e7234d');
INSERT INTO t_reagentleveltwo (`leveltwosortid`, `leveltwosortname`, `levelonesortid`) VALUES ('23f8645e-6e4a-11e5-bc1f-002564e7234d', '封闭肽', '11486637-6e49-11e5-bc1f-002564e7234d');
INSERT INTO t_reagentleveltwo (`leveltwosortid`, `leveltwosortname`, `levelonesortid`) VALUES ('56c55c6e-6e4a-11e5-bc1f-002564e7234d', 'Western Blot', '16b53844-6e49-11e5-bc1f-002564e7234d');
INSERT INTO t_reagentleveltwo (`leveltwosortid`, `leveltwosortname`, `levelonesortid`) VALUES ('5a2dce5f-6e4a-11e5-bc1f-002564e7234d', '人蛋白和抗原', '16b53844-6e49-11e5-bc1f-002564e7234d');
INSERT INTO t_reagentleveltwo (`leveltwosortid`, `leveltwosortname`, `levelonesortid`) VALUES ('5e59685c-6e4a-11e5-bc1f-002564e7234d', '蛋白结构分析', '25f8065c-6e49-11e5-bc1f-002564e7234d');
INSERT INTO t_reagentleveltwo (`leveltwosortid`, `leveltwosortname`, `levelonesortid`) VALUES ('62d5b6cb-6e4a-11e5-bc1f-002564e7234d', '蛋白检测试剂盒', '25f8065c-6e49-11e5-bc1f-002564e7234d');
INSERT INTO t_reagentleveltwo (`leveltwosortid`, `leveltwosortname`, `levelonesortid`) VALUES ('73d77a24-6e4a-11e5-bc1f-002564e7234d', '蛋白提取', '2f9a8766-6e49-11e5-bc1f-002564e7234d');
INSERT INTO t_reagentleveltwo (`leveltwosortid`, `leveltwosortname`, `levelonesortid`) VALUES ('7833b41b-6e4a-11e5-bc1f-002564e7234d', '蛋白电泳试剂', '2f9a8766-6e49-11e5-bc1f-002564e7234d');


-- -----------------------------------------------
-- Table structure for `t_reagent` 试剂表
-- -----------------------------------------------
DROP TABLE IF EXISTS `t_reagent`;
CREATE TABLE `t_reagent` (
  `reagentid` varchar(40) NOT NULL,
  `reagentname` varchar(100) NOT NULL,
  `reagentcommonname` varchar(500),
  `levelonesortid` varchar(40),
  `leveltwosortid` varchar(40),
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
INSERT INTO `t_reagent` (`reagentid`,`reagentname`,`reagentcommonname`,`levelonesortid`,`leveltwosortid`,`originplace`,`productno`,`agents`,`specification`,`price`,`chemicalname`,`casno`,`arrivaldate`,`memo`) VALUES ('0d647dee-6e4b-11e5-bc1f-002564e7234d','植物激素','植物激素','b828bf3f-6e48-11e5-bc1f-002564e7234d','49a48f98-6e49-11e5-bc1f-002564e7234d',NULL,NULL,NULL,NULL,20,NULL,NULL,'2015-10-09',NULL);
INSERT INTO `t_reagent` (`reagentid`,`reagentname`,`reagentcommonname`,`levelonesortid`,`leveltwosortid`,`originplace`,`productno`,`agents`,`specification`,`price`,`chemicalname`,`casno`,`arrivaldate`,`memo`) VALUES ('0eb9cf47-6e4c-11e5-bc1f-002564e7234d','Anti-Ras','Anti-Ras','b828bf3f-6e48-11e5-bc1f-002564e7234d','49a48f98-6e49-11e5-bc1f-002564e7234d',NULL,NULL,NULL,NULL,20,NULL,NULL,'2015-10-09',NULL);
INSERT INTO `t_reagent` (`reagentid`,`reagentname`,`reagentcommonname`,`levelonesortid`,`leveltwosortid`,`originplace`,`productno`,`agents`,`specification`,`price`,`chemicalname`,`casno`,`arrivaldate`,`memo`) VALUES ('0ec0a140-6e4c-11e5-bc1f-002564e7234d','HDAC-3 Antibody','HDAC-3 Antibody','b828bf3f-6e48-11e5-bc1f-002564e7234d','49a48f98-6e49-11e5-bc1f-002564e7234d',NULL,NULL,NULL,NULL,20,NULL,NULL,'2015-10-09',NULL);
INSERT INTO `t_reagent` (`reagentid`,`reagentname`,`reagentcommonname`,`levelonesortid`,`leveltwosortid`,`originplace`,`productno`,`agents`,`specification`,`price`,`chemicalname`,`casno`,`arrivaldate`,`memo`) VALUES ('2213288c-6e4e-11e5-bc1f-002564e7234d','单克隆抗体','单克隆抗体','b828bf3f-6e48-11e5-bc1f-002564e7234d','7dcfcdf1-6e49-11e5-bc1f-002564e7234d',NULL,NULL,NULL,NULL,20,NULL,NULL,'2015-10-09',NULL);
INSERT INTO `t_reagent` (`reagentid`,`reagentname`,`reagentcommonname`,`levelonesortid`,`leveltwosortid`,`originplace`,`productno`,`agents`,`specification`,`price`,`chemicalname`,`casno`,`arrivaldate`,`memo`) VALUES ('2219d51f-6e4e-11e5-bc1f-002564e7234d','GAPDH抗体','GAPDH抗体','b828bf3f-6e48-11e5-bc1f-002564e7234d','7dcfcdf1-6e49-11e5-bc1f-002564e7234d',NULL,NULL,NULL,NULL,20,NULL,NULL,'2015-10-09',NULL);
INSERT INTO `t_reagent` (`reagentid`,`reagentname`,`reagentcommonname`,`levelonesortid`,`leveltwosortid`,`originplace`,`productno`,`agents`,`specification`,`price`,`chemicalname`,`casno`,`arrivaldate`,`memo`) VALUES ('221daa9a-6e4e-11e5-bc1f-002564e7234d','羊抗人白蛋白（Alb）抗血清','羊抗人白蛋白（Alb）抗血清','b828bf3f-6e48-11e5-bc1f-002564e7234d','7dcfcdf1-6e49-11e5-bc1f-002564e7234d',NULL,NULL,NULL,NULL,20,NULL,NULL,'2015-10-09',NULL);
INSERT INTO `t_reagent` (`reagentid`,`reagentname`,`reagentcommonname`,`levelonesortid`,`leveltwosortid`,`originplace`,`productno`,`agents`,`specification`,`price`,`chemicalname`,`casno`,`arrivaldate`,`memo`) VALUES ('2227dcde-6e4e-11e5-bc1f-002564e7234d','Histone H3','Histone H3','b828bf3f-6e48-11e5-bc1f-002564e7234d','8356815d-6e49-11e5-bc1f-002564e7234d',NULL,NULL,NULL,NULL,20,NULL,NULL,'2015-10-09',NULL);
INSERT INTO `t_reagent` (`reagentid`,`reagentname`,`reagentcommonname`,`levelonesortid`,`leveltwosortid`,`originplace`,`productno`,`agents`,`specification`,`price`,`chemicalname`,`casno`,`arrivaldate`,`memo`) VALUES ('222c497f-6e4e-11e5-bc1f-002564e7234d','Anti-PLA2R1','Anti-PLA2R1','b828bf3f-6e48-11e5-bc1f-002564e7234d','8356815d-6e49-11e5-bc1f-002564e7234d',NULL,NULL,NULL,NULL,20,NULL,NULL,'2015-10-09',NULL);
INSERT INTO `t_reagent` (`reagentid`,`reagentname`,`reagentcommonname`,`levelonesortid`,`leveltwosortid`,`originplace`,`productno`,`agents`,`specification`,`price`,`chemicalname`,`casno`,`arrivaldate`,`memo`) VALUES ('2230b870-6e4e-11e5-bc1f-002564e7234d','抗鼠RANKL','抗鼠RANKL','b828bf3f-6e48-11e5-bc1f-002564e7234d','8356815d-6e49-11e5-bc1f-002564e7234d',NULL,NULL,NULL,NULL,20,NULL,NULL,'2015-10-09',NULL);
INSERT INTO `t_reagent` (`reagentid`,`reagentname`,`reagentcommonname`,`levelonesortid`,`leveltwosortid`,`originplace`,`productno`,`agents`,`specification`,`price`,`chemicalname`,`casno`,`arrivaldate`,`memo`) VALUES ('2234ae70-6e4e-11e5-bc1f-002564e7234d','羊抗人胱抑素C','羊抗人胱抑素C','b828bf3f-6e48-11e5-bc1f-002564e7234d','8800c20e-6e49-11e5-bc1f-002564e7234d',NULL,NULL,NULL,NULL,20,NULL,NULL,'2015-10-09',NULL);
INSERT INTO `t_reagent` (`reagentid`,`reagentname`,`reagentcommonname`,`levelonesortid`,`leveltwosortid`,`originplace`,`productno`,`agents`,`specification`,`price`,`chemicalname`,`casno`,`arrivaldate`,`memo`) VALUES ('223993d6-6e4e-11e5-bc1f-002564e7234d','HHV-4 BSLF2 Antibody','HHV-4 BSLF2 Antibody','b828bf3f-6e48-11e5-bc1f-002564e7234d','8800c20e-6e49-11e5-bc1f-002564e7234d',NULL,NULL,NULL,NULL,20,NULL,NULL,'2015-10-09',NULL);
INSERT INTO `t_reagent` (`reagentid`,`reagentname`,`reagentcommonname`,`levelonesortid`,`leveltwosortid`,`originplace`,`productno`,`agents`,`specification`,`price`,`chemicalname`,`casno`,`arrivaldate`,`memo`) VALUES ('223d9f6c-6e4e-11e5-bc1f-002564e7234d','葡萄糖转运蛋白3抗体','葡萄糖转运蛋白3抗体','b828bf3f-6e48-11e5-bc1f-002564e7234d','8800c20e-6e49-11e5-bc1f-002564e7234d',NULL,NULL,NULL,NULL,20,NULL,NULL,'2015-10-09',NULL);
INSERT INTO `t_reagent` (`reagentid`,`reagentname`,`reagentcommonname`,`levelonesortid`,`leveltwosortid`,`originplace`,`productno`,`agents`,`specification`,`price`,`chemicalname`,`casno`,`arrivaldate`,`memo`) VALUES ('22463682-6e4e-11e5-bc1f-002564e7234d','FITC ** anti-Interleukin 7','FITC ** anti-Interleukin 7','b828bf3f-6e48-11e5-bc1f-002564e7234d','8ca5057e-6e49-11e5-bc1f-002564e7234d',NULL,NULL,NULL,NULL,20,NULL,NULL,'2015-10-09',NULL);
INSERT INTO `t_reagent` (`reagentid`,`reagentname`,`reagentcommonname`,`levelonesortid`,`leveltwosortid`,`originplace`,`productno`,`agents`,`specification`,`price`,`chemicalname`,`casno`,`arrivaldate`,`memo`) VALUES ('224f3065-6e4e-11e5-bc1f-002564e7234d','GPR85 Over-expression Lysate','GPR85 Over-expression Lysate','b828bf3f-6e48-11e5-bc1f-002564e7234d','8ca5057e-6e49-11e5-bc1f-002564e7234d',NULL,NULL,NULL,NULL,20,NULL,NULL,'2015-10-09',NULL);
INSERT INTO `t_reagent` (`reagentid`,`reagentname`,`reagentcommonname`,`levelonesortid`,`leveltwosortid`,`originplace`,`productno`,`agents`,`specification`,`price`,`chemicalname`,`casno`,`arrivaldate`,`memo`) VALUES ('2257f2f6-6e4e-11e5-bc1f-002564e7234d','LHX4 Over-expression Lysate','LHX4 Over-expression Lysate','b828bf3f-6e48-11e5-bc1f-002564e7234d','8ca5057e-6e49-11e5-bc1f-002564e7234d',NULL,NULL,NULL,NULL,20,NULL,NULL,'2015-10-09',NULL);
INSERT INTO `t_reagent` (`reagentid`,`reagentname`,`reagentcommonname`,`levelonesortid`,`leveltwosortid`,`originplace`,`productno`,`agents`,`specification`,`price`,`chemicalname`,`casno`,`arrivaldate`,`memo`) VALUES ('225e70bf-6e4e-11e5-bc1f-002564e7234d','Sybr Green I','Sybr Green I','e1336fd9-6e48-11e5-bc1f-002564e7234d','bcdbc5da-6e49-11e5-bc1f-002564e7234d',NULL,NULL,NULL,NULL,20,NULL,NULL,'2015-10-09',NULL);
INSERT INTO `t_reagent` (`reagentid`,`reagentname`,`reagentcommonname`,`levelonesortid`,`leveltwosortid`,`originplace`,`productno`,`agents`,`specification`,`price`,`chemicalname`,`casno`,`arrivaldate`,`memo`) VALUES ('2262f886-6e4e-11e5-bc1f-002564e7234d','聚乙二醇衍生物','聚乙二醇衍生物','e1336fd9-6e48-11e5-bc1f-002564e7234d','bcdbc5da-6e49-11e5-bc1f-002564e7234d',NULL,NULL,NULL,NULL,20,NULL,NULL,'2015-10-09',NULL);
INSERT INTO `t_reagent` (`reagentid`,`reagentname`,`reagentcommonname`,`levelonesortid`,`leveltwosortid`,`originplace`,`productno`,`agents`,`specification`,`price`,`chemicalname`,`casno`,`arrivaldate`,`memo`) VALUES ('22673d8c-6e4e-11e5-bc1f-002564e7234d','脱氧胆酸钠','脱氧胆酸钠','e1336fd9-6e48-11e5-bc1f-002564e7234d','c1fccda2-6e49-11e5-bc1f-002564e7234d',NULL,NULL,NULL,NULL,20,NULL,NULL,'2015-10-09',NULL);
INSERT INTO `t_reagent` (`reagentid`,`reagentname`,`reagentcommonname`,`levelonesortid`,`leveltwosortid`,`originplace`,`productno`,`agents`,`specification`,`price`,`chemicalname`,`casno`,`arrivaldate`,`memo`) VALUES ('226b94cf-6e4e-11e5-bc1f-002564e7234d','内源性生物素封闭液','内源性生物素封闭液','e1336fd9-6e48-11e5-bc1f-002564e7234d','c1fccda2-6e49-11e5-bc1f-002564e7234d',NULL,NULL,NULL,NULL,20,NULL,NULL,'2015-10-09',NULL);
INSERT INTO `t_reagent` (`reagentid`,`reagentname`,`reagentcommonname`,`levelonesortid`,`leveltwosortid`,`originplace`,`productno`,`agents`,`specification`,`price`,`chemicalname`,`casno`,`arrivaldate`,`memo`) VALUES ('22705277-6e4e-11e5-bc1f-002564e7234d','三羟甲基氨基甲烷','三羟甲基氨基甲烷','e1336fd9-6e48-11e5-bc1f-002564e7234d','c679d5dc-6e49-11e5-bc1f-002564e7234d',NULL,NULL,NULL,NULL,20,NULL,NULL,'2015-10-09',NULL);
INSERT INTO `t_reagent` (`reagentid`,`reagentname`,`reagentcommonname`,`levelonesortid`,`leveltwosortid`,`originplace`,`productno`,`agents`,`specification`,`price`,`chemicalname`,`casno`,`arrivaldate`,`memo`) VALUES ('22776fd9-6e4e-11e5-bc1f-002564e7234d','农药速测卡','农药速测卡','e1336fd9-6e48-11e5-bc1f-002564e7234d','c679d5dc-6e49-11e5-bc1f-002564e7234d',NULL,NULL,NULL,NULL,20,NULL,NULL,'2015-10-09',NULL);
INSERT INTO `t_reagent` (`reagentid`,`reagentname`,`reagentcommonname`,`levelonesortid`,`leveltwosortid`,`originplace`,`productno`,`agents`,`specification`,`price`,`chemicalname`,`casno`,`arrivaldate`,`memo`) VALUES ('227bb30f-6e4e-11e5-bc1f-002564e7234d','CD31','CD31','e1336fd9-6e48-11e5-bc1f-002564e7234d','ca8509b0-6e49-11e5-bc1f-002564e7234d',NULL,NULL,NULL,NULL,20,NULL,NULL,'2015-10-09',NULL);
INSERT INTO `t_reagent` (`reagentid`,`reagentname`,`reagentcommonname`,`levelonesortid`,`leveltwosortid`,`originplace`,`productno`,`agents`,`specification`,`price`,`chemicalname`,`casno`,`arrivaldate`,`memo`) VALUES ('227f9cdd-6e4e-11e5-bc1f-002564e7234d','CD54','CD54','e1336fd9-6e48-11e5-bc1f-002564e7234d','ca8509b0-6e49-11e5-bc1f-002564e7234d',NULL,NULL,NULL,NULL,20,NULL,NULL,'2015-10-09',NULL);
INSERT INTO `t_reagent` (`reagentid`,`reagentname`,`reagentcommonname`,`levelonesortid`,`leveltwosortid`,`originplace`,`productno`,`agents`,`specification`,`price`,`chemicalname`,`casno`,`arrivaldate`,`memo`) VALUES ('cee1ee8f-6e4a-11e5-bc1f-002564e7234d','UCALLM 抗体','UCALLM 抗体','b828bf3f-6e48-11e5-bc1f-002564e7234d','49a48f98-6e49-11e5-bc1f-002564e7234d',NULL,NULL,NULL,NULL,20,NULL,NULL,'2015-10-09',NULL);


-- -----------------------------------------------
-- Table structure for `t_reagentmap` 试剂厂商对应表
-- -----------------------------------------------
DROP TABLE IF EXISTS `t_reagentmap`;
CREATE TABLE `t_reagentmap` (
  `reagentmapid` varchar(32) NOT NULL,
  `reagentid` varchar(40) NOT NULL,
  `supplierid` varchar(40) NOT NULL,
  `issuggestion` int DEFAULT 0,
  PRIMARY KEY (`reagentmapid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
-- ----------------------------
-- Records of t_reagentmap
-- ----------------------------
INSERT INTO `t_ReagentMap` (`reagentmapid`,`reagentid`,`supplierid`,`issuggestion`) VALUES ('96269999219408897','0d647dee-6e4b-11e5-bc1f-002564e7234d','4037d681494b994701494b99aba50001',0);
INSERT INTO `t_ReagentMap` (`reagentmapid`,`reagentid`,`supplierid`,`issuggestion`) VALUES ('96269999219408898','226b94cf-6e4e-11e5-bc1f-002564e7234d','4037d681494b994701494b99aba50002',0);
INSERT INTO `t_ReagentMap` (`reagentmapid`,`reagentid`,`supplierid`,`issuggestion`) VALUES ('96269999219408899','226b94cf-6e4e-11e5-bc1f-002564e7234d','4037d681494b994701494b99aba50004',1);
INSERT INTO `t_ReagentMap` (`reagentmapid`,`reagentid`,`supplierid`,`issuggestion`) VALUES ('96269999219408900','22705277-6e4e-11e5-bc1f-002564e7234d','4037d681494b994701494b99aba50001',0);
INSERT INTO `t_ReagentMap` (`reagentmapid`,`reagentid`,`supplierid`,`issuggestion`) VALUES ('96269999219408901','22705277-6e4e-11e5-bc1f-002564e7234d','4037d681494b994701494b99aba50003',1);
INSERT INTO `t_ReagentMap` (`reagentmapid`,`reagentid`,`supplierid`,`issuggestion`) VALUES ('96269999219408902','22776fd9-6e4e-11e5-bc1f-002564e7234d','4037d681494b994701494b99aba50001',0);
INSERT INTO `t_ReagentMap` (`reagentmapid`,`reagentid`,`supplierid`,`issuggestion`) VALUES ('96269999219408903','22776fd9-6e4e-11e5-bc1f-002564e7234d','4037d681494b994701494b99aba50002',1);
INSERT INTO `t_ReagentMap` (`reagentmapid`,`reagentid`,`supplierid`,`issuggestion`) VALUES ('96269999219408904','227bb30f-6e4e-11e5-bc1f-002564e7234d','4037d681494b994701494b99aba50001',1);
INSERT INTO `t_ReagentMap` (`reagentmapid`,`reagentid`,`supplierid`,`issuggestion`) VALUES ('96269999219408905','227bb30f-6e4e-11e5-bc1f-002564e7234d','4037d681494b994701494b99aba50003',0);
INSERT INTO `t_ReagentMap` (`reagentmapid`,`reagentid`,`supplierid`,`issuggestion`) VALUES ('96269999219408906','227f9cdd-6e4e-11e5-bc1f-002564e7234d','4037d681494b994701494b99aba50005',1);
INSERT INTO `t_ReagentMap` (`reagentmapid`,`reagentid`,`supplierid`,`issuggestion`) VALUES ('96269999219408907','227f9cdd-6e4e-11e5-bc1f-002564e7234d','4037d681494b994701494b99aba50002',0);
INSERT INTO `t_ReagentMap` (`reagentmapid`,`reagentid`,`supplierid`,`issuggestion`) VALUES ('96269999219408908','cee1ee8f-6e4a-11e5-bc1f-002564e7234d','4037d681494b994701494b99aba50003',0);
INSERT INTO `t_ReagentMap` (`reagentmapid`,`reagentid`,`supplierid`,`issuggestion`) VALUES ('96269999219408909','cee1ee8f-6e4a-11e5-bc1f-002564e7234d','4037d681494b994701494b99aba50001',1);
INSERT INTO `t_ReagentMap` (`reagentmapid`,`reagentid`,`supplierid`,`issuggestion`) VALUES ('96269999219408910','cee1ee8f-6e4a-11e5-bc1f-002564e7234d','4037d681494b994701494b99aba50002',0);
INSERT INTO `t_ReagentMap` (`reagentmapid`,`reagentid`,`supplierid`,`issuggestion`) VALUES ('96269999219408911','cee1ee8f-6e4a-11e5-bc1f-002564e7234d','4037d681494b994701494b99aba50004',0);
INSERT INTO `t_ReagentMap` (`reagentmapid`,`reagentid`,`supplierid`,`issuggestion`) VALUES ('96269999219408912','0d647dee-6e4b-11e5-bc1f-002564e7234d','4037d681494b994701494b99aba50002',0);
INSERT INTO `t_ReagentMap` (`reagentmapid`,`reagentid`,`supplierid`,`issuggestion`) VALUES ('96269999219408913','0d647dee-6e4b-11e5-bc1f-002564e7234d','4037d681494b994701494b99aba50003',1);
INSERT INTO `t_ReagentMap` (`reagentmapid`,`reagentid`,`supplierid`,`issuggestion`) VALUES ('96269999219408914','0eb9cf47-6e4c-11e5-bc1f-002564e7234d','4037d681494b994701494b99aba50001',0);
INSERT INTO `t_ReagentMap` (`reagentmapid`,`reagentid`,`supplierid`,`issuggestion`) VALUES ('96269999219408915','0eb9cf47-6e4c-11e5-bc1f-002564e7234d','4037d681494b994701494b99aba50002',1);
INSERT INTO `t_ReagentMap` (`reagentmapid`,`reagentid`,`supplierid`,`issuggestion`) VALUES ('96269999219408916','0ec0a140-6e4c-11e5-bc1f-002564e7234d','4037d681494b994701494b99aba50001',0);
INSERT INTO `t_ReagentMap` (`reagentmapid`,`reagentid`,`supplierid`,`issuggestion`) VALUES ('96269999219408917','0ec0a140-6e4c-11e5-bc1f-002564e7234d','4037d681494b994701494b99aba50002',1);
INSERT INTO `t_ReagentMap` (`reagentmapid`,`reagentid`,`supplierid`,`issuggestion`) VALUES ('96269999219408918','2213288c-6e4e-11e5-bc1f-002564e7234d','4037d681494b994701494b99aba50002',1);
INSERT INTO `t_ReagentMap` (`reagentmapid`,`reagentid`,`supplierid`,`issuggestion`) VALUES ('96269999219408919','2213288c-6e4e-11e5-bc1f-002564e7234d','4037d681494b994701494b99aba50003',0);
INSERT INTO `t_ReagentMap` (`reagentmapid`,`reagentid`,`supplierid`,`issuggestion`) VALUES ('96269999219408920','2219d51f-6e4e-11e5-bc1f-002564e7234d','4037d681494b994701494b99aba50001',1);
INSERT INTO `t_ReagentMap` (`reagentmapid`,`reagentid`,`supplierid`,`issuggestion`) VALUES ('96269999219408921','2219d51f-6e4e-11e5-bc1f-002564e7234d','4037d681494b994701494b99aba50002',0);
INSERT INTO `t_ReagentMap` (`reagentmapid`,`reagentid`,`supplierid`,`issuggestion`) VALUES ('96269999219408922','221daa9a-6e4e-11e5-bc1f-002564e7234d','4037d681494b994701494b99aba50001',0);
INSERT INTO `t_ReagentMap` (`reagentmapid`,`reagentid`,`supplierid`,`issuggestion`) VALUES ('96269999219408923','221daa9a-6e4e-11e5-bc1f-002564e7234d','4037d681494b994701494b99aba50002',1);
INSERT INTO `t_ReagentMap` (`reagentmapid`,`reagentid`,`supplierid`,`issuggestion`) VALUES ('96269999219408924','2227dcde-6e4e-11e5-bc1f-002564e7234d','4037d681494b994701494b99aba50002',1);
INSERT INTO `t_ReagentMap` (`reagentmapid`,`reagentid`,`supplierid`,`issuggestion`) VALUES ('96269999219408925','2227dcde-6e4e-11e5-bc1f-002564e7234d','4037d681494b994701494b99aba50003',0);
INSERT INTO `t_ReagentMap` (`reagentmapid`,`reagentid`,`supplierid`,`issuggestion`) VALUES ('96269999219408926','222c497f-6e4e-11e5-bc1f-002564e7234d','4037d681494b994701494b99aba50001',1);
INSERT INTO `t_ReagentMap` (`reagentmapid`,`reagentid`,`supplierid`,`issuggestion`) VALUES ('96269999219408927','222c497f-6e4e-11e5-bc1f-002564e7234d','4037d681494b994701494b99aba50002',0);
INSERT INTO `t_ReagentMap` (`reagentmapid`,`reagentid`,`supplierid`,`issuggestion`) VALUES ('96269999219408928','2230b870-6e4e-11e5-bc1f-002564e7234d','4037d681494b994701494b99aba50001',0);
INSERT INTO `t_ReagentMap` (`reagentmapid`,`reagentid`,`supplierid`,`issuggestion`) VALUES ('96269999219408929','2230b870-6e4e-11e5-bc1f-002564e7234d','4037d681494b994701494b99aba50002',1);
INSERT INTO `t_ReagentMap` (`reagentmapid`,`reagentid`,`supplierid`,`issuggestion`) VALUES ('96269999219408930','2234ae70-6e4e-11e5-bc1f-002564e7234d','4037d681494b994701494b99aba50002',0);
INSERT INTO `t_ReagentMap` (`reagentmapid`,`reagentid`,`supplierid`,`issuggestion`) VALUES ('96269999219408931','2234ae70-6e4e-11e5-bc1f-002564e7234d','4037d681494b994701494b99aba50004',1);
INSERT INTO `t_ReagentMap` (`reagentmapid`,`reagentid`,`supplierid`,`issuggestion`) VALUES ('96269999219408932','223993d6-6e4e-11e5-bc1f-002564e7234d','4037d681494b994701494b99aba50001',0);
INSERT INTO `t_ReagentMap` (`reagentmapid`,`reagentid`,`supplierid`,`issuggestion`) VALUES ('96269999219408933','223993d6-6e4e-11e5-bc1f-002564e7234d','4037d681494b994701494b99aba50003',1);
INSERT INTO `t_ReagentMap` (`reagentmapid`,`reagentid`,`supplierid`,`issuggestion`) VALUES ('96269999219408934','223d9f6c-6e4e-11e5-bc1f-002564e7234d','4037d681494b994701494b99aba50001',0);
INSERT INTO `t_ReagentMap` (`reagentmapid`,`reagentid`,`supplierid`,`issuggestion`) VALUES ('96269999219408935','223d9f6c-6e4e-11e5-bc1f-002564e7234d','4037d681494b994701494b99aba50002',1);
INSERT INTO `t_ReagentMap` (`reagentmapid`,`reagentid`,`supplierid`,`issuggestion`) VALUES ('96269999219408936','22463682-6e4e-11e5-bc1f-002564e7234d','4037d681494b994701494b99aba50001',1);
INSERT INTO `t_ReagentMap` (`reagentmapid`,`reagentid`,`supplierid`,`issuggestion`) VALUES ('96269999219408937','22463682-6e4e-11e5-bc1f-002564e7234d','4037d681494b994701494b99aba50003',0);
INSERT INTO `t_ReagentMap` (`reagentmapid`,`reagentid`,`supplierid`,`issuggestion`) VALUES ('96269999219408938','224f3065-6e4e-11e5-bc1f-002564e7234d','4037d681494b994701494b99aba50005',1);
INSERT INTO `t_ReagentMap` (`reagentmapid`,`reagentid`,`supplierid`,`issuggestion`) VALUES ('96269999219408939','224f3065-6e4e-11e5-bc1f-002564e7234d','4037d681494b994701494b99aba50002',0);
INSERT INTO `t_ReagentMap` (`reagentmapid`,`reagentid`,`supplierid`,`issuggestion`) VALUES ('96269999219408940','2257f2f6-6e4e-11e5-bc1f-002564e7234d','4037d681494b994701494b99aba50003',0);
INSERT INTO `t_ReagentMap` (`reagentmapid`,`reagentid`,`supplierid`,`issuggestion`) VALUES ('96269999219408941','2257f2f6-6e4e-11e5-bc1f-002564e7234d','4037d681494b994701494b99aba50001',1);
INSERT INTO `t_ReagentMap` (`reagentmapid`,`reagentid`,`supplierid`,`issuggestion`) VALUES ('96269999219408942','225e70bf-6e4e-11e5-bc1f-002564e7234d','4037d681494b994701494b99aba50002',1);
INSERT INTO `t_ReagentMap` (`reagentmapid`,`reagentid`,`supplierid`,`issuggestion`) VALUES ('96269999219408943','225e70bf-6e4e-11e5-bc1f-002564e7234d','4037d681494b994701494b99aba50004',0);
INSERT INTO `t_ReagentMap` (`reagentmapid`,`reagentid`,`supplierid`,`issuggestion`) VALUES ('96269999219408944','2262f886-6e4e-11e5-bc1f-002564e7234d','4037d681494b994701494b99aba50002',1);
INSERT INTO `t_ReagentMap` (`reagentmapid`,`reagentid`,`supplierid`,`issuggestion`) VALUES ('96269999219408945','2262f886-6e4e-11e5-bc1f-002564e7234d','4037d681494b994701494b99aba50004',0);
INSERT INTO `t_ReagentMap` (`reagentmapid`,`reagentid`,`supplierid`,`issuggestion`) VALUES ('96269999219408946','22673d8c-6e4e-11e5-bc1f-002564e7234d','4037d681494b994701494b99aba50002',0);
INSERT INTO `t_ReagentMap` (`reagentmapid`,`reagentid`,`supplierid`,`issuggestion`) VALUES ('96269999219408947','22673d8c-6e4e-11e5-bc1f-002564e7234d','4037d681494b994701494b99aba50003',1);


-- -----------------------------------------------
-- Table structure for `t_consumable` 耗材表
-- -----------------------------------------------
DROP TABLE IF EXISTS `t_consumable`;
CREATE TABLE `t_consumable` (
  `consumableid` varchar(40) NOT NULL,
  `consumablename` varchar(100) NOT NULL,
  `consumabletype` varchar(20),
  PRIMARY KEY (`consumableid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
-- ----------------------------
-- Records of `t_consumable`
-- ----------------------------
INSERT INTO t_consumable (`consumableid`,`consumablename`,`consumabletype`) VALUES ('92bf9891-6e51-11e5-bc1f-002564e7234d', '1000µl微量吸头', '1000µl');
INSERT INTO t_consumable (`consumableid`,`consumablename`,`consumabletype`) VALUES ('578aea5e-6e52-11e5-bc1f-002564e7234d','50ul枪头','50ul');
INSERT INTO t_consumable (`consumableid`,`consumablename`,`consumabletype`) VALUES ('57920500-6e52-11e5-bc1f-002564e7234d','watson顶级吸头','96支/盒');
INSERT INTO t_consumable (`consumableid`,`consumablename`,`consumabletype`) VALUES ('57986d3a-6e52-11e5-bc1f-002564e7234d','0.2ml透明96孔PCR板','96孔');
INSERT INTO t_consumable (`consumableid`,`consumablename`,`consumabletype`) VALUES ('579ee2d2-6e52-11e5-bc1f-002564e7234d','DropArray 96','96孔');
INSERT INTO t_consumable (`consumableid`,`consumablename`,`consumabletype`) VALUES ('57a2d41a-6e52-11e5-bc1f-002564e7234d','BD细胞培养瓶','25ml');
INSERT INTO t_consumable (`consumableid`,`consumablename`,`consumabletype`) VALUES ('57a7833a-6e52-11e5-bc1f-002564e7234d','细胞培养转瓶','2000ml');


-- -----------------------------------------------
-- Table structure for `t_consumablemap` 耗材厂商对应表
-- -----------------------------------------------
DROP TABLE IF EXISTS `t_consumablemap`;
CREATE TABLE `t_consumablemap` (
  `consumablemapid` varchar(32) NOT NULL,
  `consumableid` varchar(40) NOT NULL,
  `supplierid` varchar(40) NOT NULL,
  `issuggestion` int DEFAULT 0,
  PRIMARY KEY (`consumablemapid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
-- ----------------------------
-- Records of `t_consumablemap`
-- ----------------------------
INSERT INTO `t_consumablemap` (`consumablemapid`,`consumableid`,`supplierid`,`issuggestion`) VALUES ('96269999219408948','92bf9891-6e51-11e5-bc1f-002564e7234d','4037d681494b994701494b99aba10001',0);
INSERT INTO `t_consumablemap` (`consumablemapid`,`consumableid`,`supplierid`,`issuggestion`) VALUES ('96269999219408949','92bf9891-6e51-11e5-bc1f-002564e7234d','4037d681494b994701494b99aba10002',1);
INSERT INTO `t_consumablemap` (`consumablemapid`,`consumableid`,`supplierid`,`issuggestion`) VALUES ('96269999219408950','578aea5e-6e52-11e5-bc1f-002564e7234d','4037d681494b994701494b99aba10001',0);
INSERT INTO `t_consumablemap` (`consumablemapid`,`consumableid`,`supplierid`,`issuggestion`) VALUES ('96269999219408951','578aea5e-6e52-11e5-bc1f-002564e7234d','4037d681494b994701494b99aba10003',1);
INSERT INTO `t_consumablemap` (`consumablemapid`,`consumableid`,`supplierid`,`issuggestion`) VALUES ('96269999219408952','578aea5e-6e52-11e5-bc1f-002564e7234d','4037d681494b994701494b99aba10005',0);
INSERT INTO `t_consumablemap` (`consumablemapid`,`consumableid`,`supplierid`,`issuggestion`) VALUES ('96269999219408953','57920500-6e52-11e5-bc1f-002564e7234d','4037d681494b994701494b99aba10002',0);
INSERT INTO `t_consumablemap` (`consumablemapid`,`consumableid`,`supplierid`,`issuggestion`) VALUES ('96269999219408954','57920500-6e52-11e5-bc1f-002564e7234d','4037d681494b994701494b99aba10003',1);
INSERT INTO `t_consumablemap` (`consumablemapid`,`consumableid`,`supplierid`,`issuggestion`) VALUES ('96269999219408955','57986d3a-6e52-11e5-bc1f-002564e7234d','4037d681494b994701494b99aba10003',1);
INSERT INTO `t_consumablemap` (`consumablemapid`,`consumableid`,`supplierid`,`issuggestion`) VALUES ('96269999219408956','57986d3a-6e52-11e5-bc1f-002564e7234d','4037d681494b994701494b99aba10004',1);
INSERT INTO `t_consumablemap` (`consumablemapid`,`consumableid`,`supplierid`,`issuggestion`) VALUES ('96269999219408957','579ee2d2-6e52-11e5-bc1f-002564e7234d','4037d681494b994701494b99aba10005',1);
INSERT INTO `t_consumablemap` (`consumablemapid`,`consumableid`,`supplierid`,`issuggestion`) VALUES ('96269999219408958','579ee2d2-6e52-11e5-bc1f-002564e7234d','4037d681494b994701494b99aba10001',0);
INSERT INTO `t_consumablemap` (`consumablemapid`,`consumableid`,`supplierid`,`issuggestion`) VALUES ('96269999219408959','57a2d41a-6e52-11e5-bc1f-002564e7234d','4037d681494b994701494b99aba10005',1);
INSERT INTO `t_consumablemap` (`consumablemapid`,`consumableid`,`supplierid`,`issuggestion`) VALUES ('96269999219408960','57a2d41a-6e52-11e5-bc1f-002564e7234d','4037d681494b994701494b99aba10001',0);
INSERT INTO `t_consumablemap` (`consumablemapid`,`consumableid`,`supplierid`,`issuggestion`) VALUES ('96269999219408961','57a7833a-6e52-11e5-bc1f-002564e7234d','4037d681494b994701494b99aba10002',0);
INSERT INTO `t_consumablemap` (`consumablemapid`,`consumableid`,`supplierid`,`issuggestion`) VALUES ('96269999219408962','57a7833a-6e52-11e5-bc1f-002564e7234d','4037d681494b994701494b99aba10003',1);
INSERT INTO `t_consumablemap` (`consumablemapid`,`consumableid`,`supplierid`,`issuggestion`) VALUES ('96269999219408963','92bf9891-6e51-11e5-bc1f-002564e7234d','4037d681494b994701494b99aba10002',0);
INSERT INTO `t_consumablemap` (`consumablemapid`,`consumableid`,`supplierid`,`issuggestion`) VALUES ('96269999219408964','92bf9891-6e51-11e5-bc1f-002564e7234d','4037d681494b994701494b99aba10003',0);

-- -----------------------------------------------
-- Table structure for `t_equipment` 设备表
-- -----------------------------------------------
DROP TABLE IF EXISTS `t_equipment`;
CREATE TABLE `t_equipment` (
  `equipmentid` varchar(40) NOT NULL,
  `equipmentname` varchar(100) NOT NULL,
  PRIMARY KEY (`equipmentid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
-- ----------------------------
-- Records of `t_equipment`
-- ----------------------------
INSERT INTO `t_equipment` (`equipmentid`,`equipmentname`) VALUES ('ef1ec255-6e53-11e5-bc1f-002564e7234d','漩涡混合器');
INSERT INTO `t_equipment` (`equipmentid`,`equipmentname`) VALUES ('ef257ac2-6e53-11e5-bc1f-002564e7234d','推拉模式注射泵');
INSERT INTO `t_equipment` (`equipmentid`,`equipmentname`) VALUES ('ef2903d6-6e53-11e5-bc1f-002564e7234d','AH-2010新型药剂型高压均质机');
INSERT INTO `t_equipment` (`equipmentid`,`equipmentname`) VALUES ('ef2f778f-6e53-11e5-bc1f-002564e7234d','进口冻干机用真空泵');
INSERT INTO `t_equipment` (`equipmentid`,`equipmentname`) VALUES ('ef33683d-6e53-11e5-bc1f-002564e7234d','三目体式显微镜');
INSERT INTO `t_equipment` (`equipmentid`,`equipmentname`) VALUES ('ef373556-6e53-11e5-bc1f-002564e7234d','小型玻片离心机');


-- -----------------------------------------------
-- Table structure for `t_equipmentmap` 设备厂商对应表
-- -----------------------------------------------
DROP TABLE IF EXISTS `t_equipmentmap`;
CREATE TABLE `t_equipmentmap` (
  `equipmentmapid` varchar(32) NOT NULL,
  `equipmentid` varchar(40) NOT NULL,
  `supplierid` varchar(40) NOT NULL,
  `issuggestion` int DEFAULT 0,
  PRIMARY KEY (`equipmentmapid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
-- ----------------------------
-- Records of `t_equipment`
-- ----------------------------
INSERT INTO `t_equipmentmap` (`equipmentmapid`,`equipmentid`,`supplierid`,`issuggestion`) VALUES ('96269999219408965','ef1ec255-6e53-11e5-bc1f-002564e7234d','4037d681494b994701494b99aba20001',0);
INSERT INTO `t_equipmentmap` (`equipmentmapid`,`equipmentid`,`supplierid`,`issuggestion`) VALUES ('96269999219408966','ef257ac2-6e53-11e5-bc1f-002564e7234d','4037d681494b994701494b99aba20002',0);
INSERT INTO `t_equipmentmap` (`equipmentmapid`,`equipmentid`,`supplierid`,`issuggestion`) VALUES ('96269999219408967','ef2903d6-6e53-11e5-bc1f-002564e7234d','4037d681494b994701494b99aba20003',0);
INSERT INTO `t_equipmentmap` (`equipmentmapid`,`equipmentid`,`supplierid`,`issuggestion`) VALUES ('96269999219408968','ef2f778f-6e53-11e5-bc1f-002564e7234d','4037d681494b994701494b99aba20003',0);
INSERT INTO `t_equipmentmap` (`equipmentmapid`,`equipmentid`,`supplierid`,`issuggestion`) VALUES ('96269999219408969','ef33683d-6e53-11e5-bc1f-002564e7234d','4037d681494b994701494b99aba20002',0);
INSERT INTO `t_equipmentmap` (`equipmentmapid`,`equipmentid`,`supplierid`,`issuggestion`) VALUES ('96269999219408970','ef373556-6e53-11e5-bc1f-002564e7234d','4037d681494b994701494b99aba20001',0);
INSERT INTO `t_equipmentmap` (`equipmentmapid`,`equipmentid`,`supplierid`,`issuggestion`) VALUES ('96269999219408971','ef1ec255-6e53-11e5-bc1f-002564e7234d','4037d681494b994701494b99aba20002',0);
INSERT INTO `t_equipmentmap` (`equipmentmapid`,`equipmentid`,`supplierid`,`issuggestion`) VALUES ('96269999219408972','ef257ac2-6e53-11e5-bc1f-002564e7234d','4037d681494b994701494b99aba20003',0);
INSERT INTO `t_equipmentmap` (`equipmentmapid`,`equipmentid`,`supplierid`,`issuggestion`) VALUES ('96269999219408973','ef2903d6-6e53-11e5-bc1f-002564e7234d','4037d681494b994701494b99aba20001',0);
INSERT INTO `t_equipmentmap` (`equipmentmapid`,`equipmentid`,`supplierid`,`issuggestion`) VALUES ('96269999219408974','ef2f778f-6e53-11e5-bc1f-002564e7234d','4037d681494b994701494b99aba20001',0);
INSERT INTO `t_equipmentmap` (`equipmentmapid`,`equipmentid`,`supplierid`,`issuggestion`) VALUES ('96269999219408975','ef33683d-6e53-11e5-bc1f-002564e7234d','4037d681494b994701494b99aba20003',0);
INSERT INTO `t_equipmentmap` (`equipmentmapid`,`equipmentid`,`supplierid`,`issuggestion`) VALUES ('96269999219408976','ef373556-6e53-11e5-bc1f-002564e7234d','4037d681494b994701494b99aba20002',0);


-- -----------------------------------------------
-- Table structure for `t_expcategory` 实验大类表
-- -----------------------------------------------
DROP TABLE IF EXISTS `t_expcategory`;
CREATE TABLE `t_expcategory` (
  `expcategoryid` varchar(40) NOT NULL,
  `expcategoryname` varchar(100) NOT NULL,
  PRIMARY KEY (`expcategoryid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
-- ----------------------------
-- Records of t_expcategory
-- ----------------------------
INSERT INTO t_expcategory VALUES ('4028c681494b994701494b00bab50000', '蛋白质测试');
INSERT INTO `t_expcategory` (`expCategoryID`,`expCategoryName`) VALUES ('24201826039496705','DNA实验技术');
INSERT INTO `t_expcategory` (`expCategoryID`,`expCategoryName`) VALUES ('24201826039496706','RNA实验技术');
INSERT INTO `t_expcategory` (`expCategoryID`,`expCategoryName`) VALUES ('24201826039496707','感受态细胞的制备及其转化');
INSERT INTO `t_expcategory` (`expCategoryID`,`expCategoryName`) VALUES ('24201826039496708','PCR技术');
INSERT INTO `t_expcategory` (`expCategoryID`,`expCategoryName`) VALUES ('24201826039496709','蛋白质技术');

-- -----------------------------------------------
-- Table structure for `t_expsubcategory` 实验子类表
-- -----------------------------------------------
DROP TABLE IF EXISTS `t_expsubcategory`;
CREATE TABLE `t_expsubcategory` (
  `expsubcategoryid` varchar(40) NOT NULL,
  `expsubcategoryname` varchar(100) NOT NULL,
  `expcategoryid` varchar(40) NOT NULL,
  PRIMARY KEY (`expsubcategoryid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
-- ----------------------------
-- Records of t_expsubcategory
-- ----------------------------
-- INSERT INTO t_expsubcategory VALUES ('4028c681494b994701494b11bab50000', '蛋白质测试', '4028c681494b994701494b00bab50000');
INSERT INTO `t_expsubcategory` (`expSubCategoryID`,`expCategoryID`,`expSubCategoryName`) VALUES ('24201826039496710','24201826039496705','DNA蛋白相互作用');
INSERT INTO `t_expsubcategory` (`expSubCategoryID`,`expCategoryID`,`expSubCategoryName`) VALUES ('24201826039496711','24201826039496705','DNA原位杂交技术');
INSERT INTO `t_expsubcategory` (`expSubCategoryID`,`expCategoryID`,`expSubCategoryName`) VALUES ('24201826039496712','24201826039496705','DNA基础知识');
INSERT INTO `t_expsubcategory` (`expSubCategoryID`,`expCategoryID`,`expSubCategoryName`) VALUES ('24201826039496713','24201826039496705','DNA测序');
INSERT INTO `t_expsubcategory` (`expSubCategoryID`,`expCategoryID`,`expSubCategoryName`) VALUES ('24201826039496714','24201826039496706','RNA原位杂交');
INSERT INTO `t_expsubcategory` (`expSubCategoryID`,`expCategoryID`,`expSubCategoryName`) VALUES ('24201826039496715','24201826039496706','RNA的免疫沉淀');
INSERT INTO `t_expsubcategory` (`expSubCategoryID`,`expCategoryID`,`expSubCategoryName`) VALUES ('24201826039496716','24201826039496706','引物延伸实验');

-- ---------------------------------------------------------
-- Table structure for `t_expinstruction` 实验说明书主表
-- ---------------------------------------------------------
DROP TABLE IF EXISTS `t_expinstruction`;
CREATE TABLE `t_expinstruction` (
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
INSERT INTO t_expinstruction VALUES ('4028c681494b994701494b99bab60000', 'ELISA检测血清TNF-a浓度', '我也不知道', '还是不知道', '', '4037d681494b994701494b99aba50000', '海尔', 'hhd', '24201826039496705', '24201826039496710', '2015-09-16', 1, 1, '', 0, 0);
INSERT INTO t_expinstruction VALUES ('4028c681494b994701494b99bab61111', 'ELISA检测血清TNF-b浓度', '我也不知道', '还是不知道', '', '4037d681494b994701494b99aba50000', '海尔', 'hhd', '24201826039496705', '24201826039496710', '2015-09-16', 1, 1, '', 0, 20);
INSERT INTO t_expinstruction VALUES ('4028c681494b994701494b99bab62222', 'ELISA检测血清TNF-c浓度', '我也不知道', '还是不知道', '', '4037d681494b994701494b99aba50000', '海尔', 'hhd', '24201826039496705', '24201826039496710', '2015-09-16', 1, 1, '', 0, 5);


-- -----------------------------------------------
-- Table structure for `t_expstep` 实验步骤表
-- -----------------------------------------------
DROP TABLE IF EXISTS `t_expstep`;
CREATE TABLE `t_expstep` (
  `expstepid` varchar(40) NOT NULL,
  `expinstructionid` varchar(40) NOT NULL,
  `stepnum` int,
  `expstepdesc` text,
  `expsteptime` decimal(5,2) DEFAULT 0,
  PRIMARY KEY (`expstepid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
-- ----------------------------
-- Records of t_expstep
-- ----------------------------
INSERT INTO t_expstep VALUES ('4028c681494b994701494b99bab70000', '4028c681494b994701494b99bab60000', 1, '用包被缓冲液溶解抗原，使抗原浓度为10-20 μg/ml，加100 μl/孔，4℃放置', 10);
INSERT INTO t_expstep VALUES ('4028c681494b994701494b99bab70001', '4028c681494b994701494b99bab60000', 2, '弃去液体后，在吸水纸拍干，每孔加入300μl PBST 洗涤，振荡', 10);
INSERT INTO t_expstep VALUES ('4028c681494b994701494b99bab70002', '4028c681494b994701494b99bab60000', 3, '弃去液体后，在吸水纸拍干，每孔加入300μl PBST 洗涤，振荡', 10);
INSERT INTO t_expstep VALUES ('4028c681494b994701494b99bab70003', '4028c681494b994701494b99bab60000', 4, '弃去液体后，在吸水纸拍干，每孔加入300μl PBST 洗涤，振荡', 10);
INSERT INTO t_expstep VALUES ('4028c681494b994701494b99bab70004', '4028c681494b994701494b99bab60000', 5, '每孔加入150 μl 1％ BSA 37℃封闭1 小时', 10);
INSERT INTO t_expstep VALUES ('4028c681494b994701494b99bab70005', '4028c681494b994701494b99bab60000', 6, '弃去液体后，在吸水纸拍干，每孔加入300μl PBST 洗涤，振荡', 10);


-- -----------------------------------------------
-- Table structure for `t_expreagent` 实验试剂表
-- -----------------------------------------------
DROP TABLE IF EXISTS `t_expreagent`;
CREATE TABLE `t_expreagent` (
  `expreagentid` varchar(32) NOT NULL,
  `expinstructionid` varchar(40),
  `reagentid` varchar(40),
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
INSERT INTO `t_expreagent` (`expreagentid`,`expinstructionid`,`reagentid`,`reagentname`,`reagentcommonname`,`createMethod`,`reagentspec`,`useamount`) VALUES ('4028c681494b994701494b99aba50000','4028c681494b994701494b99bab60000','0eb9cf47-6e4c-11e5-bc1f-002564e7234d','Anti-Ras','Anti-Ras','洗涤液','洗涤液',50);
INSERT INTO `t_expreagent` (`expreagentid`,`expinstructionid`,`reagentid`,`reagentname`,`reagentcommonname`,`createMethod`,`reagentspec`,`useamount`) VALUES ('4028c681494b994701494b99aba50001','4028c681494b994701494b99bab60000','0d647dee-6e4b-11e5-bc1f-002564e7234d','植物激素','植物激素','洗涤液1','洗涤液1',500);
INSERT INTO `t_expreagent` (`expreagentid`,`expinstructionid`,`reagentid`,`reagentname`,`reagentcommonname`,`createMethod`,`reagentspec`,`useamount`) VALUES ('4028c681494b994701494b99aba50002','4028c681494b994701494b99bab60000','2213288c-6e4e-11e5-bc1f-002564e7234d','单克隆抗体','单克隆抗体','单克隆抗体','洗涤液2',60);
INSERT INTO `t_expreagent` (`expreagentid`,`expinstructionid`,`reagentid`,`reagentname`,`reagentcommonname`,`createMethod`,`reagentspec`,`useamount`) VALUES ('96269999219408977','4028c681494b994701494b99bab60000','2234ae70-6e4e-11e5-bc1f-002564e7234d','羊抗人胱抑素C','羊抗人胱抑素C','1','1',10);
INSERT INTO `t_expreagent` (`expreagentid`,`expinstructionid`,`reagentid`,`reagentname`,`reagentcommonname`,`createMethod`,`reagentspec`,`useamount`) VALUES ('96269999219408978','4028c681494b994701494b99bab61111','22463682-6e4e-11e5-bc1f-002564e7234d','FITC ** anti-Interleukin 7','FITC ** anti-Interleukin 7','1','1',10);
INSERT INTO `t_expreagent` (`expreagentid`,`expinstructionid`,`reagentid`,`reagentname`,`reagentcommonname`,`createMethod`,`reagentspec`,`useamount`) VALUES ('96269999219408979','4028c681494b994701494b99bab61111','225e70bf-6e4e-11e5-bc1f-002564e7234d','Sybr Green I','Sybr Green I','1','1',10);
INSERT INTO `t_expreagent` (`expreagentid`,`expinstructionid`,`reagentid`,`reagentname`,`reagentcommonname`,`createMethod`,`reagentspec`,`useamount`) VALUES ('96269999219408980','4028c681494b994701494b99bab61111','22705277-6e4e-11e5-bc1f-002564e7234d','三羟甲基氨基甲烷','三羟甲基氨基甲烷','2','2',20);
INSERT INTO `t_expreagent` (`expreagentid`,`expinstructionid`,`reagentid`,`reagentname`,`reagentcommonname`,`createMethod`,`reagentspec`,`useamount`) VALUES ('96269999219408981','4028c681494b994701494b99bab61111','0eb9cf47-6e4c-11e5-bc1f-002564e7234d','Anti-Ras','Anti-Ras','2','2',20);
INSERT INTO `t_expreagent` (`expreagentid`,`expinstructionid`,`reagentid`,`reagentname`,`reagentcommonname`,`createMethod`,`reagentspec`,`useamount`) VALUES ('96269999219408982','4028c681494b994701494b99bab61111','2219d51f-6e4e-11e5-bc1f-002564e7234d','GAPDH抗体','GAPDH抗体','2','2',20);
INSERT INTO `t_expreagent` (`expreagentid`,`expinstructionid`,`reagentid`,`reagentname`,`reagentcommonname`,`createMethod`,`reagentspec`,`useamount`) VALUES ('96269999219408983','4028c681494b994701494b99bab62222','2230b870-6e4e-11e5-bc1f-002564e7234d','抗鼠RANKL','抗鼠RANKL','3','3',30);
INSERT INTO `t_expreagent` (`expreagentid`,`expinstructionid`,`reagentid`,`reagentname`,`reagentcommonname`,`createMethod`,`reagentspec`,`useamount`) VALUES ('96269999219408984','4028c681494b994701494b99bab62222','224f3065-6e4e-11e5-bc1f-002564e7234d','GPR85 Over-expression Lysate','GPR85 Over-expression Lysate','3','3',31);
INSERT INTO `t_expreagent` (`expreagentid`,`expinstructionid`,`reagentid`,`reagentname`,`reagentcommonname`,`createMethod`,`reagentspec`,`useamount`) VALUES ('96269999219408985','4028c681494b994701494b99bab62222','22776fd9-6e4e-11e5-bc1f-002564e7234d','农药速测卡','农药速测卡','3','3',25);


-- -----------------------------------------------
-- Table structure for `t_expconsumable` 实验耗材表
-- -----------------------------------------------
DROP TABLE IF EXISTS `t_expconsumable`;
CREATE TABLE `t_expconsumable` (
  `expconsumableid` varchar(40) NOT NULL,
  `expinstructionid` varchar(40),
  `consumableid` varchar(40),
  `consumabletype` varchar(20),
  `consumablecount` int,
  `consumablefactory` varchar(100),
  PRIMARY KEY (`expconsumableid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
-- ----------------------------
-- Records of t_expconsumable
-- ----------------------------
INSERT INTO `labassistant`.`t_expconsumable` (`expconsumableid`, `expinstructionid`, `consumableid`, `consumabletype`, `consumablecount`, `consumablefactory`) VALUES ('0b211cbf-6e57-11e5-bc1f-002564e7234d', '4028c681494b994701494b99bab60000', '578aea5e-6e52-11e5-bc1f-002564e7234d', '50ul', '10', 'no');
INSERT INTO `labassistant`.`t_expconsumable` (`expconsumableid`, `expinstructionid`, `consumableid`, `consumabletype`, `consumablecount`, `consumablefactory`) VALUES ('1249c2f5-6e57-11e5-bc1f-002564e7234d', '4028c681494b994701494b99bab60000', '57a2d41a-6e52-11e5-bc1f-002564e7234d', '25ml', '5', 'no');
INSERT INTO `labassistant`.`t_expconsumable` (`expconsumableid`, `expinstructionid`, `consumableid`, `consumabletype`, `consumablecount`, `consumablefactory`) VALUES ('5e86183f-6e57-11e5-bc1f-002564e7234d', '4028c681494b994701494b99bab61111', '57920500-6e52-11e5-bc1f-002564e7234d', '100ml', '20', 'no');
INSERT INTO `labassistant`.`t_expconsumable` (`expconsumableid`, `expinstructionid`, `consumableid`, `consumabletype`, `consumablecount`, `consumablefactory`) VALUES ('6294070b-6e57-11e5-bc1f-002564e7234d', '4028c681494b994701494b99bab61111', '579ee2d2-6e52-11e5-bc1f-002564e7234d', '96T', '2', 'no');
INSERT INTO `labassistant`.`t_expconsumable` (`expconsumableid`, `expinstructionid`, `consumableid`, `consumabletype`, `consumablecount`, `consumablefactory`) VALUES ('66d48e70-6e57-11e5-bc1f-002564e7234d', '4028c681494b994701494b99bab62222', '92bf9891-6e51-11e5-bc1f-002564e7234d', '1000ul', '2', 'no');
INSERT INTO `labassistant`.`t_expconsumable` (`expconsumableid`, `expinstructionid`, `consumableid`, `consumabletype`, `consumablecount`, `consumablefactory`) VALUES ('6a70172a-6e57-11e5-bc1f-002564e7234d', '4028c681494b994701494b99bab62222', '57a7833a-6e52-11e5-bc1f-002564e7234d', '2000ml', '2', 'no');
INSERT INTO `labassistant`.`t_expconsumable` (`expconsumableid` ,`expinstructionid`, `consumableid`, `consumabletype`, `consumablecount`, `consumablefactory`) VALUES ('4028c791494b994701494b99aba50000','4028c681494b994701494b99bab60000','92bf9891-6e51-11e5-bc1f-002564e7234d','96T',2,'不知道');


-- -----------------------------------------------
-- Table structure for `t_expequipment` 实验设备表
-- -----------------------------------------------
DROP TABLE IF EXISTS `t_expequipment`;
CREATE TABLE `t_expequipment` (
  `expequipmentid` varchar(40) NOT NULL,
  `expinstructionid` varchar(40),
  `equipmentid` varchar(40),
  `equipmentname` varchar(100),
  `equipmentfactory` varchar(100),
  PRIMARY KEY (`expequipmentid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
-- ----------------------------
-- Records of t_expequipment
-- ----------------------------
INSERT INTO `t_expequipment` (`expequipmentid`,`expinstructionid`,`equipmentid`,`equipmentname`,`equipmentfactory`) VALUES ('1d56f8ce-6e58-11e5-bc1f-002564e7234d','4028c681494b994701494b99bab62222','ef33683d-6e53-11e5-bc1f-002564e7234d','三目体式显微镜','no');
INSERT INTO `t_expequipment` (`expequipmentid`,`expinstructionid`,`equipmentid`,`equipmentname`,`equipmentfactory`) VALUES ('208d40ea-6e58-11e5-bc1f-002564e7234d','4028c681494b994701494b99bab62222','ef373556-6e53-11e5-bc1f-002564e7234d','小型玻片离心机','no');
INSERT INTO `t_expequipment` (`expequipmentid`,`expinstructionid`,`equipmentid`,`equipmentname`,`equipmentfactory`) VALUES ('4028c791564b994701494b99aba50000','4028c681494b994701494b99bab60000','ef1ec255-6e53-11e5-bc1f-002564e7234d','漩涡混合器','海尔');
INSERT INTO `t_expequipment` (`expequipmentid`,`expinstructionid`,`equipmentid`,`equipmentname`,`equipmentfactory`) VALUES ('4028c791564b994701494b99aba50001','4028c681494b994701494b99bab60000','ef257ac2-6e53-11e5-bc1f-002564e7234d','推拉模式注射泵','上海一恒');
INSERT INTO `t_expequipment` (`expequipmentid`,`expinstructionid`,`equipmentid`,`equipmentname`,`equipmentfactory`) VALUES ('4028c791564b994701494b99aba50002','4028c681494b994701494b99bab60000','ef2f778f-6e53-11e5-bc1f-002564e7234d','进口冻干机用真空泵','Tecan 200');
INSERT INTO `t_expequipment` (`expequipmentid`,`expinstructionid`,`equipmentid`,`equipmentname`,`equipmentfactory`) VALUES ('f9d436f6-6e57-11e5-bc1f-002564e7234d','4028c681494b994701494b99bab61111','ef2903d6-6e53-11e5-bc1f-002564e7234d','AH-2010新型药剂型高压均质机','no');
INSERT INTO `t_expequipment` (`expequipmentid`,`expinstructionid`,`equipmentid`,`equipmentname`,`equipmentfactory`) VALUES ('fd6ce422-6e57-11e5-bc1f-002564e7234d','4028c681494b994701494b99bab61111','ef373556-6e53-11e5-bc1f-002564e7234d','小型玻片离心机','no');


-- -----------------------------------------------
-- Table structure for `t_expreview` 实验评论表
-- -----------------------------------------------
DROP TABLE IF EXISTS `t_expreview`;
CREATE TABLE `t_expreview` (
  `expreviewid` varchar(40) NOT NULL,
  `expinstructionid` varchar(40) NOT NULL,
  `reviewerid` varchar(40) NOT NULL,
  `reviewdate` date,
  `reviewyear` int,
  `reviewmonth` int,
  `expscore` int,
  `reviewinfo` varchar(500),
  `agreecount` int DEFAULT 0,
  `disagreecount` int DEFAULT 0,
  PRIMARY KEY (`expreviewid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
-- ----------------------------
-- Records of t_expreview
-- ----------------------------
INSERT INTO t_expreview VALUES ('4045c791564b994701494b99aba50000','4028c681494b994701494b99bab60000', '4028c681494b994701494b99aba50000', '2015-09-22', 2015, 09, 5, '这是什么鬼', 5, 0);
INSERT INTO t_expreview VALUES ('4045c791564b994701494b99aba50001','4028c681494b994701494b99bab60000', '4028c681494b994701494b99aba50000', '2015-09-22', 2015, 09, 5, '这到底是什么鬼', 5, 0);


-- -----------------------------------------------
-- Table structure for `t_myexpinstruction` 我的说明书表
-- -----------------------------------------------
DROP TABLE IF EXISTS `t_myexpinstruction`;
CREATE TABLE `t_myexpinstruction` (
  `myexpinstructionid` varchar(40) NOT NULL,
  `expinstructionid` varchar(40) NOT NULL,
  `userid` varchar(40),
  `downloadtime` datetime,
  PRIMARY KEY (`myexpinstructionid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- -----------------------------------------------
-- Table structure for `t_myexp` 我的实验主表
-- -----------------------------------------------
DROP TABLE IF EXISTS `t_myexp`;
CREATE TABLE `t_myexp` (
  `myexpid` varchar(40) NOT NULL,
  `expinstructionid` varchar(40) NOT NULL,
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
-- Records of t_myexp
-- ----------------------------
INSERT INTO t_myexp VALUES ('4039c681494b994701494b99aba51236', '4028c681494b994701494b99bab60000','4028c681494b994701494b99aba50000', '2015-09-16', 2015, 09, '2015-09-16', 1, 0, 0, 0, '', '', '', 0, '');
INSERT INTO t_myexp VALUES ('4039c681494b994701494b99aba51237', '4028c681494b994701494b99bab61111','4028c681494b994701494b99aba50000', '2015-09-16', 2015, 09, '2015-09-16', 1, 0, 0, 0, '', '', '', 2, '');
INSERT INTO t_myexp VALUES ('4039c681494b994701494b99aba51238', '4028c681494b994701494b99bab62222','4028c681494b994701494b99aba50000', '2015-09-16', 2015, 09, '2015-09-16', 1, 0, 0, 0, '', '', '', 2, '');


-- -----------------------------------------------
-- Table structure for `t_myexpprocess` 我的实验步骤表
-- -----------------------------------------------
DROP TABLE IF EXISTS `t_myexpprocess`;
CREATE TABLE `t_myexpprocess` (
  `myexpprocessid` varchar(40) NOT NULL,
  `myexpid` varchar(40) NOT NULL,
  `expinstructionid` varchar(40) NOT NULL,
  `expstepid` varchar(40) NOT NULL,
  `stepnum` int,
  `expstepdesc` text,
  `expsteptime` decimal(5,2) DEFAULT 0,
  `isusetimer` int,
  `processmemo` varchar(500),
  PRIMARY KEY (`myexpprocessid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
-- ----------------------------
-- Records of t_myexpprocess
-- ----------------------------
INSERT INTO t_myexpprocess VALUES ('4039c681494b994701494b99aba50000', '4039c681494b994701494b99aba51236', '4028c681494b994701494b99bab60000', '4028c681494b994701494b00bab60000', 0, '', 5, 1, '');
INSERT INTO t_myexpprocess VALUES ('4039c681494b994701494b99aba50010', '4039c681494b994701494b99aba51237', '4028c681494b994701494b99bab61111', '4028c681494b994701494b00bab60123', 0, '', 5, 1, '');


-- -----------------------------------------------
-- Table structure for `t_myexpreagent` 我的实验试剂表
-- -----------------------------------------------
DROP TABLE IF EXISTS `t_myexpreagent`;
CREATE TABLE `t_myexpreagent` (
  `myexpreagentid` varchar(40) NOT NULL,
  `myexpid` varchar(40) NOT NULL,
  `expinstructionid` varchar(40) NOT NULL,
  `reagentid` varchar(40) NOT NULL,
  `supplierid` varchar(40) NOT NULL,
  PRIMARY KEY (`myexpreagentid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
-- ----------------------------
-- Records of t_myexpreagent
-- ----------------------------
INSERT INTO t_myexpreagent VALUES ('5039c681494b994701494b99aba50000', '4039c681494b994701494b99aba51236', '4028c681494b994701494b99bab60000', '111', '4037d681494b994701494b99aba50000');
INSERT INTO t_myexpreagent VALUES ('5039c681494b994701494b99aba50010', '4039c681494b994701494b99aba51237', '4028c681494b994701494b99bab61111', '112', '4037d681494b994701494b99aba50000');


-- -----------------------------------------------
-- Table structure for `t_myexpconsumable` 我的实验耗材表
-- -----------------------------------------------
DROP TABLE IF EXISTS `t_myexpconsumable`;
CREATE TABLE `t_myexpconsumable` (
  `myexpconsumableid` varchar(40) NOT NULL,
  `myexpid` varchar(40) NOT NULL,
  `expinstructionid` varchar(40) NOT NULL,
  `consumableid` varchar(40) NOT NULL,
  `supplierid` varchar(40) NOT NULL,
  PRIMARY KEY (`myexpconsumableid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
-- ----------------------------
-- Records of t_myexpconsumable
-- ----------------------------
INSERT INTO t_myexpconsumable VALUES ('6039c681494b994701494b99aba50000', '4039c681494b994701494b99aba51236', '4028c681494b994701494b99bab60000', '4028c791494b994701494b99aba50000', '4037d681494b994701494b99aba50000');
INSERT INTO t_myexpconsumable VALUES ('6039c681494b994701494b99aba50010', '4039c681494b994701494b99aba51237', '4028c681494b994701494b99bab61111', '4028c791494b994701494b99aba50000', '4037d681494b994701494b99aba50000');


-- -----------------------------------------------
-- Table structure for `t_myexpequipment` 我的实验设备表
-- -----------------------------------------------
DROP TABLE IF EXISTS `t_myexpequipment`;
CREATE TABLE `t_myexpequipment` (
  `myexpequipmentid` varchar(40) NOT NULL,
  `myexpid` varchar(40) NOT NULL,
  `expinstructionid` varchar(40) NOT NULL,
  `equipmentid` varchar(40) NOT NULL,
  `supplierid` varchar(40) NOT NULL,
  PRIMARY KEY (`myexpequipmentid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
-- ----------------------------
-- Records of t_myexpequipment
-- ----------------------------
INSERT INTO t_myexpequipment VALUES ('7039c681494b994701494b99aba50000', '4039c681494b994701494b99aba51236', '4028c681494b994701494b99bab60000', '4028c791564b994701494b99aba50000', '4037d681494b994701494b99aba50000');
INSERT INTO t_myexpequipment VALUES ('7039c681494b994701494b99aba50010', '4039c681494b994701494b99aba51237', '4028c681494b994701494b99bab61111', '4028c791564b994701494b99aba50000', '4037d681494b994701494b99aba50000');


-- -----------------------------------------------
-- Table structure for `t_myexpplan` 我的实验计划表
-- -----------------------------------------------
DROP TABLE IF EXISTS `t_myexpplan`;
CREATE TABLE `t_myexpplan` (
  `myexpplanid` varchar(40) NOT NULL,
  `userid` varchar(40) NOT NULL,
  `expinstructionid` varchar(40),
  `experimentname` varchar(200),
  `plandate` date,
  `planofyear` int,
  `planofmonth` int,
  PRIMARY KEY (`myexpplanid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
-- ----------------------------
-- Records of t_myexpplan
-- ----------------------------
INSERT INTO t_myexpplan VALUES ('8039c681494b994701494b99aba50000', '4028c681494b994701494b99aba50000', '4028c681494b994701494b99bab60000', '我也不知道', '2015-09-21', 2015, 09);


-- -----------------------------------------------
-- Table structure for `t_myexpprocessattch` 我的实验步骤附件表
-- -----------------------------------------------
DROP TABLE IF EXISTS `t_myexpprocessattch`;
CREATE TABLE `t_myexpprocessattch` (
  `myexpprocessattchid` varchar(40) NOT NULL,
  `myexpid` varchar(40) NOT NULL,
  `expinstructionid` varchar(40) NOT NULL,
  `expstepid` varchar(40) NOT NULL,
  `attchmentname` varchar(200),
  `attchmentlocation` varchar(500),
  `attchmentserverpath` varchar(500),
  `isupload` int,
  PRIMARY KEY (`myexpprocessattchid`)
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
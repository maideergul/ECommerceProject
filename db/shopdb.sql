/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 100414
 Source Host           : localhost:3306
 Source Schema         : shopdb

 Target Server Type    : MySQL
 Target Server Version : 100414
 File Encoding         : 65001

 Date: 12/02/2021 17:17:58
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `aid` int NOT NULL,
  `mail` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `pass` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `remember` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`aid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES (0, 'asli@mail.com', 'Aslı Asıl', '58b1216b06850385d9a4eadbedc806c4', NULL);
INSERT INTO `admin` VALUES (1, 'ali@mail.com', 'Ali Tunç', '58b1216b06850385d9a4eadbedc806c4', NULL);
INSERT INTO `admin` VALUES (2, 'ayse@mail.com', 'Ayse Ay', '58b1216b06850385d9a4eadbedc806c4', NULL);

-- ----------------------------
-- Table structure for cart_proc
-- ----------------------------
DROP TABLE IF EXISTS `cart_proc`;
CREATE TABLE `cart_proc`  (
  `pid` int NOT NULL,
  `cid` int NOT NULL,
  `detail` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `price` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `pstatu` int NOT NULL,
  `quantity` int NOT NULL,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`pid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cart_proc
-- ----------------------------

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category`  (
  `cid` int NOT NULL AUTO_INCREMENT,
  `icon_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `categoryname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`cid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES (1, 'fas fa-mobile-alt', 'Phones & Accessories');
INSERT INTO `category` VALUES (3, 'fas fa-headphones', 'Headphones');
INSERT INTO `category` VALUES (4, 'fas fa-laptop', 'Computers & Accessories');
INSERT INTO `category` VALUES (5, 'fas fa-camera', 'Camera & Photo');
INSERT INTO `category` VALUES (6, 'fas fa-tv', 'Television & Video');
INSERT INTO `category` VALUES (7, 'fas fa-gamepad', 'Video Game Consoles');

-- ----------------------------
-- Table structure for hibernate_sequence
-- ----------------------------
DROP TABLE IF EXISTS `hibernate_sequence`;
CREATE TABLE `hibernate_sequence`  (
  `next_val` bigint NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of hibernate_sequence
-- ----------------------------
INSERT INTO `hibernate_sequence` VALUES (1);

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product`  (
  `pid` int NOT NULL AUTO_INCREMENT,
  `cid` int NOT NULL,
  `detail` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `pstatu` int NOT NULL,
  `quantity` int NOT NULL,
  `price` int NULL DEFAULT NULL,
  PRIMARY KEY (`pid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 30 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES (8, 1, 'iPhone 12 128 gb', 'https://store.storeimages.cdn-apple.com/4668/as-images.apple.com/is/iphone-12-family-select-2020?wid=470&amp;hei=556&amp;fmt=jpeg&amp;qlt=95&amp;op_usm=0.5,0.5&amp;.v=1604343709000', 'iPhone 12', 0, 0, 10000);
INSERT INTO `product` VALUES (9, 3, 'High-Fidelity Audio ', 'https://store.storeimages.cdn-apple.com/4668/as-images.apple.com/is/airpods-max-hero-select-202011?wid=470&hei=556&fmt=jpeg&qlt=95&op_usm=0.5%2C0.5&.v=1604709293000', 'AirPods Max', 1, 1, 1750);
INSERT INTO `product` VALUES (10, 4, 'iPad', 'https://store.storeimages.cdn-apple.com/4668/as-images.apple.com/is/ipad-mini-select-201911_GEO_TR?wid=465&hei=530&fmt=jpeg&qlt=95&op_usm=0.5%2C0.5&.v=1584551242602', 'iPad Mini', 1, 1, 7550);
INSERT INTO `product` VALUES (11, 1, 'Golden Rose', 'https://store.storeimages.cdn-apple.com/4668/as-images.apple.com/is/MTP72_VW_34FR+watch-40-alum-gold-nc-6s_VW_34FR_WF_CO_GEO_TR?wid=700&hei=700&trim=1,0&fmt=p-jpg&qlt=95&op_usm=0.5,0.5&.v=1566449884261,1601920568000', 'Apple Watch', 0, 0, 3799);
INSERT INTO `product` VALUES (12, 4, '15.6 inches Full HD IPS Display, AMD Ryzen 3 3200U', 'https://images-na.ssl-images-amazon.com/images/I/71vvXGmdKWL._AC_SL1500_.jpg', 'Acer Aspire 5 Slim Laptop', 0, 0, 2485);
INSERT INTO `product` VALUES (14, 7, 'Carbon Black', 'https://images-na.ssl-images-amazon.com/images/I/61z3GQgEPZL._SL1200_.jpg', 'Xbox Core Controller', 1, 1, 680);
INSERT INTO `product` VALUES (15, 7, '1TB', 'https://gmedia.playstation.com/is/image/SIEPDC/ps4-slim-image-block-01-en-24jul20?$1600px--t$', 'PlayStation 4 Slim', 0, 0, 2335);
INSERT INTO `product` VALUES (16, 7, 'Comes with charging cable', 'https://images-na.ssl-images-amazon.com/images/I/61drpi3cYUL._SL1258_.jpg', 'Nintendo Switch Pro Controller', 0, 0, 415);
INSERT INTO `product` VALUES (17, 6, 'Flat 32\" QLED 4K 32Q50 Series Smart TV', 'https://images-na.ssl-images-amazon.com/images/I/51NKhnjhpGL._AC_SL1024_.jpg', 'SAMSUNG QN32Q50RAFXZA', 0, 0, 3321);
INSERT INTO `product` VALUES (18, 6, '50Inch 4K Ultra HD Smart LED TV', 'https://images-na.ssl-images-amazon.com/images/I/81RMDc4CWYL._AC_SL1500_.jpg', 'LG 50UN7300PUF', 0, 0, 15000);
INSERT INTO `product` VALUES (19, 5, '18-55mm Lens,24.1 MP CMOS Sensor', 'https://images-na.ssl-images-amazon.com/images/I/71EWRyqzw0L._AC_SL1500_.jpg', 'Canon EOS Rebel T7 DSLR', 0, 0, 3750);
INSERT INTO `product` VALUES (20, 5, 'Interchangeable Lens Mirrorless Camera', 'https://images-na.ssl-images-amazon.com/images/I/81MpiBUkqsL._AC_SL1500_.jpg', 'Sony NEW Alpha 7S III Full-frame', 0, 1, 25898);
INSERT INTO `product` VALUES (21, 3, 'AirPods with Wireless Charging Case', 'https://store.storeimages.cdn-apple.com/4982/as-images.apple.com/is/MRXJ2?wid=572&hei=572&fmt=jpeg&qlt=95&op_usm=0.5,0.5&.v=1551489675083', 'AirPods', 0, 0, 1450);
INSERT INTO `product` VALUES (22, 3, 'Wireless Earbuds', 'https://images-na.ssl-images-amazon.com/images/I/714cbTDjLvL._AC_SL1500_.jpg', 'Samsung Galaxy Buds+ Plus', 0, 0, 760);
INSERT INTO `product` VALUES (25, 4, '3.5GHz 8‑core Intel Xeon W processor, Turbo Boost up to 4.0GHz', 'https://store.storeimages.cdn-apple.com/4982/as-images.apple.com/is/mac-pro-hero-splitter?wid=493&hei=490&fmt=jpeg&qlt=95&op_usm=0.5,0.5&.v=1572645163008', 'Apple Mac Pro', 1, 1, 67449);
INSERT INTO `product` VALUES (26, 4, 'Apple M1 Chip with 8-Core CPU and 8-Core GPU 256GB Storage', 'https://store.storeimages.cdn-apple.com/4982/as-images.apple.com/is/mac-mini-hero-202011?wid=452&hei=420&fmt=jpeg&qlt=95&op_usm=0.5,0.5&.v=1603403462000', 'Apple Mac mini', 0, 0, 7899);
INSERT INTO `product` VALUES (27, 6, '32-inch Retina 6K. Astonishing color accuracy. Superwide viewing angle. And Extreme Dynamic Range.', 'https://store.storeimages.cdn-apple.com/4982/as-images.apple.com/is/pro-display-gallery1-201909?wid=2000&hei=1536&fmt=jpeg&qlt=95&op_usm=0.5,0.5&.v=1574201024213', 'Apple Pro Display XDR', 1, 1, 41319);
INSERT INTO `product` VALUES (28, 7, '24 months of Xbox Game Pass Ultimate', 'https://compass-ssl.xbox.com/assets/c9/82/c9827ca6-83ee-457e-b33b-1c1a350efb57.png?n=0202999-BuyBox01_Image-D.png', 'Xbox Series S console', 0, 0, 8250);
INSERT INTO `product` VALUES (29, 1, '128 GB', 'https://store.storeimages.cdn-apple.com/4982/as-images.apple.com/is/iphone-xr-select-2019-family?wid=441&amp;hei=529&amp;fmt=jpeg&amp;qlt=95&amp;op_usm=0.5,0.5&amp;.v=1550795424612', 'iPhone X🅁', 0, 0, 7549);

-- ----------------------------
-- View structure for productcid_view
-- ----------------------------
DROP VIEW IF EXISTS `productcid_view`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `productcid_view` AS SELECT p.pid,p.cid,p.detail,p.img,p.price,p.title, c.categoryname FROM `product` as p JOIN category as c ON p.cid=c.cid ;

-- ----------------------------
-- Procedure structure for cart_proc
-- ----------------------------
DROP PROCEDURE IF EXISTS `cart_proc`;
delimiter ;;
CREATE PROCEDURE `cart_proc`(IN `p_statu` integer)
BEGIN
	select * from product where pstatu=p_statu;


END
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;

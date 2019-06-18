#
# TABLE STRUCTURE FOR: Computers
#

DROP TABLE IF EXISTS `Computers`;

CREATE TABLE `Computers` (
  `Name` varchar(100) NOT NULL,
  `Description` varchar(500) DEFAULT NULL,
  `Category` varchar(255) NOT NULL,
  `Barcode` int(15) NOT NULL,
  UNIQUE KEY `Barcode` (`Barcode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `Computers` (`Name`, `Description`, `Category`, `Barcode`) VALUES ('HP', 'Personal Laptop', 'LAPTOP', 83647);

INSERT INTO `Computers` (`Name`, `Description`, `Category`, `Barcode`) VALUES ('TOSHIBA', 'Personal Computer', 'PC', 83847);

INSERT INTO `Computers` (`Name`, `Description`, `Category`, `Barcode`) VALUES ('ACER', 'Personal Laptop', 'LAPTOP', 83641);

INSERT INTO `Computers` (`Name`, `Description`, `Category`, `Barcode`) VALUES ('APPLE', 'Personal Laptop', 'LAPTOP', 83617);

INSERT INTO `Computers` (`Name`, `Description`, `Category`, `Barcode`) VALUES ('DELL', 'Personal Laptop', 'LAPTOP', 83640);

INSERT INTO `Computers` (`Name`, `Description`, `Category`, `Barcode`) VALUES ('HP', 'Personal Computer', 'PC', 83697);

INSERT INTO `Computers` (`Name`, `Description`, `Category`, `Barcode`) VALUES ('APPLE', 'Personal Laptop', 'LAPTOP', 83147);

INSERT INTO `Computers` (`Name`, `Description`, `Category`, `Barcode`) VALUES ('TOSHIBA', 'Personal Computer', 'PC', 81641);

INSERT INTO `Computers` (`Name`, `Description`, `Category`, `Barcode`) VALUES ('IBM', 'Personal Computer', 'PC', 82479);

INSERT INTO `Computers` (`Name`, `Description`, `Category`, `Barcode`) VALUES ('LENOVO', 'Personal Laptop', 'LAPTOP', 81697);

#
# TABLE STRUCTURE FOR: Furniture
#

DROP TABLE IF EXISTS `Furniture`;

CREATE TABLE `Furniture` (
  `Name` varchar(100) NOT NULL,
  `Description` varchar(500) DEFAULT NULL,
  `Category` varchar(255) NOT NULL,
  `Barcode` int(15) NOT NULL,
  UNIQUE KEY `Barcode` (`Barcode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `Furniture` (`Name`, `Description`, `Category`, `Barcode`) VALUES ('DESK', 'Desk for work', 'WORK', '76185');
INSERT INTO `Furniture` (`Name`, `Description`, `Category`, `Barcode`) VALUES ('TABLE', 'Table for food and drinks', 'HOME', '76212');
INSERT INTO `Furniture` (`Name`, `Description`, `Category`, `Barcode`) VALUES ('CHAIR', 'Work chair', 'WORK', '76595');
INSERT INTO `Furniture` (`Name`, `Description`, `Category`, `Barcode`) VALUES ('DESK', 'Desk for work', 'WORK', '74521');
INSERT INTO `Furniture` (`Name`, `Description`, `Category`, `Barcode`) VALUES ('POOL TABLE', '9 foot pool table', 'RECREATION', '74002');
INSERT INTO `Furniture` (`Name`, `Description`, `Category`, `Barcode`) VALUES ('TV STAND', 'A table for a TV', 'HOME', '72001');
INSERT INTO `Furniture` (`Name`, `Description`, `Category`, `Barcode`) VALUES ('BOOKCASE', 'Bookcase made from wood', 'HOME', '71512');
INSERT INTO `Furniture` (`Name`, `Description`, `Category`, `Barcode`) VALUES ('CHAIR', 'Leather Office Chair', 'WORK', '79642');
INSERT INTO `Furniture` (`Name`, `Description`, `Category`, `Barcode`) VALUES ('SOFA', '10 foot sofa', 'HOME', '73121');
INSERT INTO `Furniture` (`Name`, `Description`, `Category`, `Barcode`) VALUES ('DESK', 'Desk for work', 'WORK', '70123');


#
# TABLE STRUCTURE FOR: Printers
#

DROP TABLE IF EXISTS `Printers`;

CREATE TABLE `Printers` (
  `Name` varchar(100) NOT NULL,
  `Description` varchar(500) DEFAULT NULL,
  `Category` varchar(255) NOT NULL,
  `Barcode` int(15) NOT NULL
  UNIQUE KEY `Barcode` (`Barcode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `Printers` (`Name`, `Description`, `Category`, `Barcode`) VALUES ('CANON', 'Laser Printer', 'LASER', '56844');
INSERT INTO `Printers` (`Name`, `Description`, `Category`, `Barcode`) VALUES ('EPSON', '3D Printer', '3D', '56841');
INSERT INTO `Printers` (`Name`, `Description`, `Category`, `Barcode`) VALUES ('CANON', 'Laser Printer', 'LASER', '56824');
INSERT INTO `Printers` (`Name`, `Description`, `Category`, `Barcode`) VALUES ('HP', '3D Printer', '3D', '51844');
INSERT INTO `Printers` (`Name`, `Description`, `Category`, `Barcode`) VALUES ('MINOLTA', 'Laser Printer', 'LASER', '56444');


#
# TABLE STRUCTURE FOR: Supplies
#

DROP TABLE IF EXISTS `Supplies`;

CREATE TABLE `Supplies` (
  `Name` varchar(100) NOT NULL,
  `Description` varchar(500) DEFAULT NULL,
  `Category` varchar(255) NOT NULL,
  `Barcode` int(15) NOT NULL,
  UNIQUE KEY `Barcode` (`Barcode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `Supplies` (`Name`, `Description`, `Category`, `Barcode`) VALUES ('PAPER', 'A4 Paper', 'PAPER', '47444');
INSERT INTO `Supplies` (`Name`, `Description`, `Category`, `Barcode`) VALUES ('PENS', 'Ballpoint Pens', 'WRITING', '47123');
INSERT INTO `Supplies` (`Name`, `Description`, `Category`, `Barcode`) VALUES ('PENCILS', 'HB Pencils', 'WRITING', '47102');
INSERT INTO `Supplies` (`Name`, `Description`, `Category`, `Barcode`) VALUES ('ADHESIVE PAPER', 'Name tags and post-it notes', 'PAPER', '47002');
INSERT INTO `Supplies` (`Name`, `Description`, `Category`, `Barcode`) VALUES ('MOPS', 'Regular mops', 'JANITOR', '47237');
INSERT INTO `Supplies` (`Name`, `Description`, `Category`, `Barcode`) VALUES ('WATER BUCKETS', 'Buckets for holding water', 'JANITOR', '47754');
INSERT INTO `Supplies` (`Name`, `Description`, `Category`, `Barcode`) VALUES ('PAPER CLIPS', 'Metal clips for holding paper', 'OFFICE', '47999');
INSERT INTO `Supplies` (`Name`, `Description`, `Category`, `Barcode`) VALUES ('BINDERS', 'Binders for holding paper', 'PAPER', '47099');
INSERT INTO `Supplies` (`Name`, `Description`, `Category`, `Barcode`) VALUES ('STAPLES', 'Staples for holding paper together', 'OFFICE', '47854');



#
# TABLE STRUCTURE FOR: Warehouse
#

DROP TABLE IF EXISTS `Warehouse`;

CREATE TABLE `Warehouse` (
  `Name` varchar(100) NOT NULL,
  `Description` varchar(500) DEFAULT NULL,
  `Category` varchar(255) NOT NULL,
  `Barcode` int(12) NOT NULL,
  UNIQUE KEY `Barcode` (`Barcode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `Warehouse` (`Name`, `Description`, `Category`, `Barcode`) VALUES ('DRUM FAN', 'A drum fan for cooling', 'FANS', '90212');
INSERT INTO `Warehouse` (`Name`, `Description`, `Category`, `Barcode`) VALUES ('INDUSTRIAL FAN', 'Industrial fan for cooling', 'FANS', '91202');
INSERT INTO `Warehouse` (`Name`, `Description`, `Category`, `Barcode`) VALUES ('CEILING FAN', 'Fan mounted to the ceiling', 'FANS', '91230');
INSERT INTO `Warehouse` (`Name`, `Description`, `Category`, `Barcode`) VALUES ('WALK RAMP', 'Metal ramp', 'DOCK AND TRAILER', '98021');
INSERT INTO `Warehouse` (`Name`, `Description`, `Category`, `Barcode`) VALUES ('FOLDING SECURITY GATE', 'Portable and Foldable security gate', 'DOCK AND TRAILER', '94502');
INSERT INTO `Warehouse` (`Name`, `Description`, `Category`, `Barcode`) VALUES ('DRAWERS', 'Metal Drawers', 'OFFICE', '96602');
INSERT INTO `Warehouse` (`Name`, `Description`, `Category`, `Barcode`) VALUES ('STORAGE CABINET', 'Metal cabinet for storage', 'OFFICE', '91464');
INSERT INTO `Warehouse` (`Name`, `Description`, `Category`, `Barcode`) VALUES ('LAMP', 'Metal Lamp', 'OFFICE', '92130');
INSERT INTO `Warehouse` (`Name`, `Description`, `Category`, `Barcode`) VALUES ('PLATFORM SCALE', 'Durable platform scale', 'SCALES', '96000');
INSERT INTO `Warehouse` (`Name`, `Description`, `Category`, `Barcode`) VALUES ('POCKET SCALE', 'Digital pocket scale', 'SCALES', '99090');

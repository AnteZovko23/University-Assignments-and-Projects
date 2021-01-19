-- CREATE TABLE `Lab_1`.`BRANCH` 
-- ( `BranchID` VARCHAR(25) NOT NULL , 
-- `BranchName` VARCHAR(50) NOT NULL , 
-- `BranchAddress` VARCHAR(50) NOT NULL , 
-- PRIMARY KEY (`BranchID`)) ;


-- CREATE TABLE `Lab_1`.`CUSTOMER` 
-- ( `SSN` VARCHAR(25) NOT NULL , 
-- `Name` VARCHAR(50) NOT NULL , 
-- `Phone` VARCHAR(50) NULL ,
-- `Address` VARCHAR(50) NOT NULL ,  
-- PRIMARY KEY (`SSN`));

-- CREATE TABLE `Lab_1`.`ACCOUNT_TYPE` 
-- ( `AccTypeID` VARCHAR(25) NOT NULL , 
-- `AccountType` ENUM('Savings','Checking','Credit_Card', 'Mortgage'), 
-- PRIMARY KEY (`AccTypeID`));



-- CREATE TABLE `Lab_1`.`ACCOUNT` 
-- ( `AccNo` VARCHAR(25) NOT NULL , 
-- `AccTypeID` VARCHAR(50) NOT NULL , 
-- `Branch` VARCHAR(50) NOT NULL ,
-- `SSN` VARCHAR(50) NOT NULL ,   
-- `Balance` DECIMAL(10, 2) NOT NULL ,  
-- PRIMARY KEY (`AccNo`),
-- FOREIGN KEY (`AccTypeID`) REFERENCES ACCOUNT_TYPE(`AccTypeID`),
-- FOREIGN KEY (`Branch`) REFERENCES BRANCH(`BranchID`),
-- FOREIGN KEY (`SSN`) REFERENCES CUSTOMER(`SSN`));



-- CREATE TABLE `Lab_1`.`POSSESS` 
-- ( `SSN` VARCHAR(25) NOT NULL , 
-- `AccNo` VARCHAR(25) NOT NULL, 
-- FOREIGN KEY (`SSN`) REFERENCES CUSTOMER(`SSN`),
-- FOREIGN KEY (`AccNo`) REFERENCES ACCOUNT(`AccNo`));

-- CREATE TABLE `Lab_1`.`LINK` 
-- ( `BranchID` VARCHAR(25) NOT NULL , 
-- `AccNo` VARCHAR(25) NOT NULL, 
-- FOREIGN KEY (`BranchID`) REFERENCES BRANCH(`BranchID`),
-- FOREIGN KEY (`AccNo`) REFERENCES ACCOUNT(`AccNo`));



-- CREATE TABLE `Lab_1`.`HAS` 
-- ( `AccTypeID` VARCHAR(25) NOT NULL , 
-- `AccNo` VARCHAR(25) NOT NULL, 
-- FOREIGN KEY (`AccTypeID`) REFERENCES ACCOUNT_TYPE(`AccTypeID`),
-- FOREIGN KEY (`AccNo`) REFERENCES ACCOUNT(`AccNo`));




INSERT INTO `BRANCH` (`BranchID`, `BranchName`, `BranchAddress`) 
VALUES ('101222', 'New York', 'First Street'),
('101223', 'Los Angeles', 'Second Street'),
('101224', 'Chicago', 'Third Street');

INSERT INTO `CUSTOMER` (`SSN`, `Name`, `Phone`, `Address`) 
VALUES ('11223344', 'Joe', NULL, 'Fifty Second Street'),
('11223345', 'John', '3181112234', 'Twenty First Street'),
('11223348', 'Antonio', NULL, 'Twenty Second Street');


INSERT INTO `ACCOUNT_TYPE` (`AccTypeID`, `AccountType`) 
VALUES ('123321', "Checking"),
('123322', "Savings"),
('123323', "Mortgage"),
('123324', "Credit_Card"),
('123325', "Savings");

INSERT INTO `ACCOUNT` (`AccNo`, `AccTypeID`, `Branch`, `SSN`, `Balance`) VALUES 
('123456', '123321', '101222',  '11223344', '322.67'),
('123457', '123322', '101222',  '11223344', '1234.65'),
('123458', '123323', '101223', '11223345', '23457.87'),
('123450', '123324', '101224', '11223345', '234.65'),
('123459', '123325', '101224', '11223348', '0.00');


INSERT INTO `HAS` (`AccTypeID`, `AccNo`) 
VALUES ('123321', '123456'),
('123322', '123457'),
('123323', '123458'),
('123324', '123450'),
('123325', '123459');


INSERT INTO `LINK` (`BranchID`, `AccNo`) 
VALUES ('101222',  '123456'),
('101222',  '123457'),
('101223', '123458'),
('101224', '123450'),
('101224', '123459');


INSERT INTO `POSSESS` (`SSN`, `AccNo`) 
VALUES ('11223344','123456'),
('11223344','123457'),
('11223345', '123458'),
('11223345', '123450'),
('11223348',' 123459');



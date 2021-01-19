CREATE TABLE `Lab_1`.`STUDENT` 
( `sID` VARCHAR(15) NOT NULL , 
`sName` VARCHAR(25) NOT NULL , 
`GPA` FLOAT(3,2) NOT NULL , 
`sizeHS` INT NULL , 
PRIMARY KEY (`sID`));


CREATE TABLE `Lab_1`.`COLLEGE` 
( `cName` VARCHAR(25) NOT NULL , 
`state` VARCHAR(25) NOT NULL , 
`enrollment` INT NOT NULL , 
PRIMARY KEY (`cName`));



CREATE TABLE `Lab_1`.`APPLY` 
( `sID` VARCHAR(25) NOT NULL , 
`cName` VARCHAR(25) NOT NULL , 
`major` VARCHAR(20) NOT NULL , 
`decision` VARCHAR(20) NOT NULL , 
FOREIGN KEY (`cName`) REFERENCES COLLEGE(`cName`),
FOREIGN KEY (`sID`) REFERENCES STUDENT(`sID`),
CONSTRAINT `apply_const` UNIQUE (`sID`,`cName`,`major`));


INSERT INTO `STUDENT` (`sID`, `sName`, `GPA`, `sizeHS`) 
VALUES ('123', 'Amy', '3.9', '1000'), 
('234', 'Bob', '3.6', '1500'), 
('345', 'Craig', '3.5', '500'),
('456', 'Doris', '3.9', '1000'),
('567', 'Edward', '2.9', '2000'),
('678', 'Fay', '3.8', '200'),
('789', 'Gary', '3.4', '800'),
('987', 'Helen', '3.7', '800'),
('876', 'Irene', '3.9', '400'),
('765', 'Jay', '2.9', '1500'),
('654', 'Amy', '3.9', '1000'),
('543', 'Craig', '3.4', '2000');


INSERT INTO `COLLEGE` (`cName`, `state`, `enrollment`) 
VALUES ('Stanford', 'CA', '15000'),
('Berkeley', 'CA', '36000'),
('MIT', 'MA', '10000'),
('Cornell', 'NY', '21000');

INSERT INTO `APPLY` (`sID`, `cName`, `major`, `decision`) 
VALUES ('123', 'Stanford', 'CS', 'Y'),
('123', 'Stanford', 'EE', 'N'),
('123', 'Berkeley', 'CS', 'Y'),
('123', 'Cornell', 'EE', 'Y'),
('234', 'Berkeley', 'Biology', 'N'),
('345', 'MIT', 'Bioengineering', 'Y'),
('345', 'Cornell', 'Bioengineering', 'N'),
('345', 'Cornell', 'CS', 'Y'),
('345', 'Cornell', 'EE', 'N'),
('678', 'Stanford', 'History', 'Y'),
('987', 'Stanford', 'CS', 'Y'),
('987', 'Berkeley', 'CS', 'Y'),
('876', 'Stanford', 'CS', 'N'),
('876', 'MIT', 'Biology', 'Y'),
('876', 'MIT', 'marine Biology', 'N'),
('765', 'Stanford', 'History', 'Y'),
('765', 'Cornell', 'History', 'N'),
('765', 'Cornell', 'Psychology', 'Y'),
('543', 'MIT', 'CS', 'N');
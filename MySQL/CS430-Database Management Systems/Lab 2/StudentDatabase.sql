/* Database Script */
-- use BettingDatabaseV2;
-- drop database if exists StudentdbW21; /* Replace <Studentdb> with a database name of your choice*/

-- create database StudentdbW21; /* Replace <Studentdb> with the database name of your choice*/

-- use StudentdbW21; /* Replace <Studentdb> with the database name of your choice*/

create table STUDENT(
    SID varchar(10) not null,
    Name varchar(45),
    Address varchar(45),
    primary key(SID)
); 

create table PROFESSOR(
    PID varchar(10) not null,
    Name varchar(45),
    Office varchar(45),
    Age Int,
    DepartmentName varchar(45),
    primary key(`PID`)
);

ALTER TABLE PROFESSOR CHANGE COLUMN Age DateOfBirth DATE NOT NULL;

ALTER TABLE PROFESSOR ADD NewDate DATE;

ALTER TABLE PROFESSOR DROP NewDate;

create table COURSE(
    CourseNum INTEGER not null,
    DeptName varchar(45) not null,
    CourseName varchar(45),
    ClassRoom varchar(45),
    Enrollment INT,
    primary key(`CourseNum`,`DeptName`)
);


create table DEPARTMENT(
    DeptName varchar(45) not null,
    ChairmanID varchar(45),
    primary key(`DeptName`)
);

create table PreReq(
    CourseNum Integer not null,
    DeptName varchar(45),
    PreReqNumber INT,
    PreReqDeptName varchar(45),
    primary key(`CourseNum`,`DeptName`)
);


CREATE TABLE Teach (
  PID VARCHAR(10),
  CourseNum INT,
  DeptName VARCHAR(45),
  PRIMARY KEY(PID,CourseNum,DeptName ),

  CONSTRAINT PIDFKey
    FOREIGN KEY (PID)
    REFERENCES PROFESSOR(PID),
  CONSTRAINT CourseNumFKey
    FOREIGN KEY (CourseNum)
    REFERENCES COURSE(CourseNum),
  CONSTRAINT DeptNameFKey
    FOREIGN KEY (DeptName)
    REFERENCES DEPARTMENT(DeptName)
    );



CREATE  TABLE Take (
  SID VARCHAR(10),
  CourseNum INT,
  DeptName VARCHAR(45),
  Grade Decimal(4,2),
  ProfessorEval Decimal(4,2),
  PRIMARY KEY(SID,CourseNum,DeptName),
  CONSTRAINT SIDFKey2
    FOREIGN KEY (SID)
    REFERENCES STUDENT(SID),
  CONSTRAINT CourseNumFKey2
    FOREIGN KEY (CourseNum)
    REFERENCES COURSE(CourseNum),
  CONSTRAINT DeptNameFKey2
    FOREIGN KEY (DeptName)
    REFERENCES DEPARTMENT(DeptName)
    );


insert into STUDENT(SID,Name,Address) 
values('S001','Amy o`Brian','NY');

insert into STUDENT(SID,Name,Address)
values('S002','Bob Catillo','Texas')
,('S003','Candice DeMello','Louisiana')
,('S004','Darrel West','Michigan');



insert into PROFESSOR
values('P001','Dr. John Smith','NH101','19651231',null);

insert into PROFESSOR
values('P002','Dr. Mary Smith','NH102','19700101',null),
('P003','Dr. Ardella Ayres','NH103','19700501',null),
('P004','Dr. David Dennett','NH104','19750204',null);


insert into DEPARTMENT
values('Engineering and Science','Dr. John Smith'),
('Education','Dr. Ralph Ahner'),
('Business','Dr. Kelley Gade');

insert into COURSE
values(101,'Engineering and Science','Software Programming','NH150',30),
(102,'Engineering and Science','Introduction to Datamining', 'NH150',25),
(103,'Education','Education 101','BH101',30),
(104,'Business','Business 101','BH101',20),
(200,'Business','Introduction to Administration','BH102',15),
(300,'Business','Advanced Administration','BH103',20);

insert into PreReq
values(101,'Engineering and Science',100,'Engineering and Science'),
(103,'Education',102,'Engineering and Science'),
(104,'Business',103,'Education'),
(300,'Business',200,'Business');


insert into Teach
values('P003',101,'Engineering and Science'),
('P003',101,'Education'),
('P003',101,'Business');

insert into Take values('S001',101,'Engineering and Science',3.9,3.9);
insert into Take values('S002',101,'Education',3.5,3.3);
insert into Take values('S003',101,'Business',3.4,3.6);
insert into Take values('S004',101,'Engineering and Science',2.9,2.5);
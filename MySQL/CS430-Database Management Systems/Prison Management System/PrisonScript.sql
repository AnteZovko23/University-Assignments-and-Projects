CREATE DATABASE Prison_System;

USE Prison_System;


create table Guard(
    GID VARCHAR(15) not null,
    Gear VARCHAR(45) not null,
    Cellblock VARCHAR(10) not null,
    primary key(GID)
); 

create table Cellblock(
    Block enum('A', 'B', 'C', 'D') NOT NULL DEFAULT 'A',
    Security_Level int,
    Occupants VARCHAR(200),
    primary key(Block)
); 


CREATE TABLE Guards (
  GID VARCHAR(15) not null,
  Block enum('A', 'B', 'C', 'D') NOT NULL DEFAULT 'A',
  PRIMARY KEY(GID,Block),

  CONSTRAINT GIDKey
    FOREIGN KEY (GID)
    REFERENCES Guard(GID),
  CONSTRAINT BlockKey
    FOREIGN KEY (Block)
    REFERENCES Cellblock(Block)
    );


create table Inmate(
    PID VARCHAR(15) not null,
    Parole BOOLEAN not null,
    Security_Level int not null,
    Sentence VARCHAR(15) not null,
    Sentence_Length int not null,
    Bail DECIMAL(9, 2) not null, 
    primary key(PID)
); 


CREATE TABLE Lives_in (
  PID VARCHAR(15) not null,
  Block enum('A', 'B', 'C', 'D') NOT NULL DEFAULT 'A',
  PRIMARY KEY(PID,Block),

  CONSTRAINT PIDKey
    FOREIGN KEY (PID)
    REFERENCES Inmate(PID),
  CONSTRAINT BlockKey2
    FOREIGN KEY (Block)
    REFERENCES Cellblock(Block)
    );

CREATE TABLE Visit (
  PID VARCHAR(15) not null,
  PRIMARY KEY(PID),

   CONSTRAINT PIDKey2
    FOREIGN KEY (PID)
    REFERENCES Inmate(PID)
    );

create table Clinic(
    Patient VARCHAR(15) not null,
    Perscription VARCHAR(25) NOT NULL
); 


CREATE VIEW studentInfo AS 
SELECT Name as sname, Address as saddress FROM STUDENT;

SELECT * FROM studentInfo;


DELIMITER $$

CREATE TRIGGER professor_DateOfBirth
BEFORE INSERT on PROFESSOR
FOR EACH ROW
BEGIN
    DECLARE msg VARCHAR(255);
    if (DateDiff(Now(), NEW.DateOfBirth)/365 < 18) THEN
    set msg = 'Invalid date of birth';
    SIGNAL SQLSTATE '45000' set MESSAGE_TEXT = msg;
    ENd IF;

END
$$

DELIMITER ;


CREATE VIEW studentDetails AS
SELECT s.*, tk.CourseNum, tk.DeptName, tk.Grade, tk.ProfessorEval , c.CourseName, c.ClassRoom
FROM STUDENT s, Take tk, COURSE c
WHERE s.SID = tk.SID AND tk.CourseNum = c.CourseNum;

SELECT * FROM studentDetails;


CREATE TABLE TAKE_BACKUP (SELECT * FROM Take WHERE 1 = 2);

DELIMITER $$

CREATE TRIGGER TakeBackup
AFTER DELETE on Take
FOR EACH ROW
BEGIN 
    INSERT INTO TAKE_BACKUP VALUES (OLD.SID, OLD.CourseNum, OLD.DeptName, OLD.Grade, OLD.ProfessorEval);
END

$$

DELIMITER ;



CREATE TABLE GRADE_CHANGED 
(SID VARCHAR(45) NOT NULL ,
OLDGrade VARCHAR(45) NULL ,  
NEWGrade VARCHAR(45) NULL );


DELIMITER $$

CREATE TRIGGER GradeChanges
AFTER UPDATE ON Take
FOR EACH ROW
BEGIN
    IF(New.Grade <> Old.Grade)
    THEN
    INSERT INTO GRADE_CHANGED VALUES(NEW.SID, OLD.Grade, NEW.Grade);
    END IF;

END
$$

DELIMITER ;




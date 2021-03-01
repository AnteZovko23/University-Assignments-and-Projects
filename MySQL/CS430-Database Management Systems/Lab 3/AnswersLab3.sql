
SELECT * FROM STUDENT as s INNER JOIN Take as t on s.SID = t.SID;


SELECT * FROM STUDENT NATURAL JOIN Take;


SELECT * FROM STUDENT s INNER JOIN Take t USING(sid);


SELECT * FROM COURSE as c LEFT OUTER JOIN PreReq as p ON c.CourseNum = p.CourseNum;


SELECT dpt.DeptName ,AVG(Enrollment) FROM DEPARTMENT as dpt, COURSE as c WHERE dpt.DeptName = c.DeptName GROUP BY DeptName;